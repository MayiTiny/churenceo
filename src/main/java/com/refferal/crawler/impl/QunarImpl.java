package com.refferal.crawler.impl;

import java.net.URLDecoder;

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
				"http://www.qunar.com/site/zh/zhaopin/prd/jobs.html?type=")) {
			return true;
		}
		return false;
	}

	public void visit(Page page) {
		
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
			Document doc = Jsoup.parse(html);
			Elements jds = doc.getElementsByClass("b_jobitem");
			for (Element jd : jds) {
				JobDescription jobDesc = new JobDescription();
				String title = jd.getAllElements().get(2).text();
				jobDesc.setName(title);
				Elements lines = jd.getElementsByClass("inline");
				int index = 0;
				for (Element line : lines) {
					String text = line.text();
					if (index == 0) {
						String location = text.split("：")[1];
						if (location.length() > 16)
							location = location.substring(0, 15);
						jobDesc.setCityId(location);
					} else if (index == 3) {
						String desciption = text;
						jobDesc.setPostDescription(desciption);
					} else if (index == 5) {
						String require = text;
						jobDesc.setPostRequire(require);
					}
					index++;
				}
				jobDesc.setCompany(CompanyEnum.QUNAR.getCompanyId());
				jobDesc.setHeadCount(-1);
				jobDesc.setDegree("本科");
				jobDesc.setYearsLimit("3年");
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
				}
				jobDescriptionDao.insert(jobDesc);
			}
		}
	}
}
