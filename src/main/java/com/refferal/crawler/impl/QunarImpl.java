package com.refferal.crawler.impl;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.refferal.common.AppContext;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.CompanyEnum;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class QunarImpl extends WebCrawler {

	private JobDescriptionDao jobDescriptionDao;

	public boolean shouldVisit(WebURL url) {
		if (url.getURL().contains(
				"http://www.qunar.com/site/zh/zhaopin/prd/jobs.html?type=%E4%BA%A7%E5%93%81%E8%BF%90%E8%90%A5%E5%B2%97")) {
			return true;
		}
		return false;
	}

	public void visit(Page page) {
		Map<String,String> types = new HashMap<String,String>();
		jobDescriptionDao = AppContext.getInstance().getBean(
				JobDescriptionDao.class);
		String url = page.getWebURL().getURL();
		String department = "未知";
		try {
			department = URLDecoder.decode(url.split("=")[1], "utf-8");
		} catch (Exception e) {
		}
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			Pattern p = Pattern.compile("jobTypes\\[\"(.*?)\"\\] = \"(.*?)\"");
			Matcher m = p.matcher(html);
			System.out.println(html);
			while(m.find()){
				types.put(m.group(1), m.group(2));
			}
			Document doc = Jsoup.parse(html);
			Elements jds = doc.getElementsByClass("b_jobitem");
			for (Element jd : jds) {
				JobDescription jobDesc = new JobDescription();
				String title = jd.getAllElements().get(2).text();
				jobDesc.setName(title);
				Elements lines = jd.getElementsByClass("inline");
				int index = 0;
				for (Element line : lines) {
					String text = line.html();
					if (index == 0) {
						String location = text.split("：")[1];
						if (location.length() > 16)
							location = location.substring(0, 15);
						jobDesc.setCityId(location);
					} else if (index == 3) {
						String desciption = text;
						jobDesc.setPostDescription(line.html());
					} else if (index == 5) {
						String require = text;
						jobDesc.setPostRequire(line.html());
					}
					index++;
				}
				jobDesc.setCompany(CompanyEnum.QUNAR.getCompanyId());
				jobDesc.setHeadCount(-1);
				jobDesc.setDegree("本科");
				jobDesc.setYearsLimit("3年");
				if(null != types.get(jobDesc.getName())){
					department = types.get(jobDesc.getName());
				}
				if (department.equals("产品运营岗")) {
					jobDesc.setFunctionType(3);
					jobDesc.setDepartment("产品运营部");
				} else if (department.equals("技术岗")) {
					jobDesc.setFunctionType(2);
					jobDesc.setDepartment("技术部");
				} else if (department.equals("呼叫中心")) {
					jobDesc.setFunctionType(6);
					jobDesc.setDepartment("呼叫中心");
				} else if (department.equals("销售业务岗")) {
					jobDesc.setFunctionType(10);
					jobDesc.setDepartment("销售业务部");
				} else if (department.equals("财务风控岗")) {
					jobDesc.setFunctionType(99);
					jobDesc.setDepartment("财务风控岗");
				}else{
					jobDesc.setFunctionType(99);
					jobDesc.setDepartment("其他");
				}
				jobDescriptionDao.insert(jobDesc);
			}
		}
	}
}
