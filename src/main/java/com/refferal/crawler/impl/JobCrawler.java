package com.refferal.crawler.impl;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.refferal.common.AppContext;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.BaiduCategoryEnum;
import com.refferal.enums.CompanyEnum;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class JobCrawler extends WebCrawler {

	private JobDescriptionDao jobDescriptionDao;

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	public boolean shouldVisit(WebURL url) {
		if (url.getURL().contains("corpwebPosition1000baidu!getOnePosition")) {
			return true;
		}
		return false;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	public void visit(Page page) {
		jobDescriptionDao = AppContext.getInstance().getBean(JobDescriptionDao.class);
		String url = page.getWebURL().getURL();
		if (page.getParseData() instanceof HtmlParseData) {
			if (url.contains("getOnePosition")) {
				HtmlParseData htmlParseData = (HtmlParseData) page
						.getParseData();
				String html = htmlParseData.getHtml();

				Document doc = Jsoup.parse(html);
				JobDescription jobDesc = new JobDescription();
				Elements titles = doc.getElementsByClass("hrs_grayBorderTitle");
				if (null != titles && null != titles.get(0)) {
					String title = titles.get(0).childNode(0).toString();
					String[] deptAndTitle = title.split("_");
					if(deptAndTitle.length > 1){
						jobDesc.setName(deptAndTitle[1]);
					}else{
						jobDesc.setName(title);
					}
					jobDesc.setDepartment(deptAndTitle[0]);
				}
				Elements jobInfos = doc.getElementsByClass("hrs_jobInfo");
				if (null != jobInfos && null != jobInfos.get(0)) {
					Element jobInfo = jobInfos.get(0);
					jobDesc.setCompany(CompanyEnum.BAIDU.getCompanyId());
					jobDesc.setCityId(jobInfo.child(3).childNode(0)
							.childNode(0).toString());
					String headCount = jobInfo.child(5).childNode(0).toString()
							.trim();
					try {
						jobDesc.setHeadCount(Integer.parseInt(headCount));
					} catch (Exception e) {
						jobDesc.setHeadCount(-1);
					}
					String type = jobInfo.child(7).childNode(0).childNode(0)
							.toString();
					jobDesc.setFunctionType(BaiduCategoryEnum
							.getCodeByName(type));
				}
				Elements hrs_jobDuties = doc.getElementsByClass("hrs_jobDuty");
				if (null != hrs_jobDuties && null != hrs_jobDuties.get(0)) {
					Element hrs_jobDuty = hrs_jobDuties.get(0);
					List<Node> nodes = hrs_jobDuty.childNode(3).childNodes();
					StringBuffer sb = new StringBuffer("");
					for (Node node : nodes) {
						sb.append(node.toString().trim());
					}
					jobDesc.setPostDescription(sb.toString());
				}
				Elements hrs_jobRequires = doc
						.getElementsByClass("hrs_jobRequire");
				if (null != hrs_jobRequires && null != hrs_jobRequires.get(0)) {
					Element hrs_jobRequire = hrs_jobRequires.get(0);
					List<Node> nodes = hrs_jobRequire.childNode(3).childNodes();
					StringBuffer sb = new StringBuffer("");
					for (Node node : nodes) {
						sb.append(node.toString().trim());
					}
					jobDesc.setPostRequire(sb.toString());
					jobDesc.setYearsLimit("0");
					jobDesc.setDegree("本科");
				}
				int isExsit = jobDescriptionDao.selectExsit(jobDesc);
				if(isExsit == 0){
					jobDescriptionDao.insert(jobDesc);
				}else{
					jobDescriptionDao.updateById(isExsit);
				}
			}
		}
	}
}
