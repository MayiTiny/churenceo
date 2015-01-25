package com.refferal.crawler.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.refferal.common.AppContext;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.CompanyEnum;
import com.refferal.enums.TencentCategoryEnum;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class TencentImpl extends WebCrawler {

	
	private JobDescriptionDao jobDescriptionDao;

	public boolean shouldVisit(WebURL url) {
		if (url.getURL().contains(
				"http://hr.tencent.com/position.php?start=")
				|| url.getURL().contains(
						"http://hr.tencent.com/position_detail.php")) {
			return true;
		}
		return false;
	}

	public void visit(Page page) {
		jobDescriptionDao = AppContext.getInstance().getBean(
				JobDescriptionDao.class);
		JobDescription jobDescroption = new JobDescription();
		String url = page.getWebURL().getURL();
		if (url.contains("http://hr.tencent.com/position_detail.php")
				&& page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			Document doc = Jsoup.parse(htmlParseData.getHtml());
			Element title = doc.getElementsByClass("h").get(0);
			String name =  title.text();
			jobDescroption.setName(name);
			jobDescroption.setDepartment("");
			jobDescroption.setCompany(CompanyEnum.TENCENT.getCompanyId());
			Element tr = title.nextElementSibling();
			Elements tds = tr.getElementsByTag("td");
			String location = tds.get(0).childNode(1).toString();
			jobDescroption.setCityId(location);
			String type = tds.get(1).childNode(1).toString();
			String headCount = tds.get(2).childNode(1).toString().split("人")[0];
			jobDescroption.setHeadCount(Integer.valueOf(headCount));
			Elements jobDesc = doc.getElementsByClass("squareli");
			String desc = jobDesc.get(0).html();
			jobDescroption.setPostDescription(desc);
			String require = jobDesc.get(1).html();
			jobDescroption.setPostRequire(require);
			jobDescroption.setDegree("本科");
			jobDescroption.setFunctionType(TencentCategoryEnum.getCodeByName(type));
			jobDescroption.setYearsLimit("3年");
			int isExsit = jobDescriptionDao.selectExsit(jobDescroption);
			if(isExsit == 0){
				jobDescriptionDao.insert(jobDescroption);
			}else{
				jobDescriptionDao.updateById(isExsit);
			}
		}
	}

}
