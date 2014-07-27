package com.refferal.crawler;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class JobCrawler extends WebCrawler {

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
		String url = page.getWebURL().getURL();
		if (page.getParseData() instanceof HtmlParseData) {
			if (url.contains("getOnePosition")) {
				HtmlParseData htmlParseData = (HtmlParseData) page
						.getParseData();
				String html = htmlParseData.getHtml();

				Document doc = Jsoup.parse(html);
				System.out.println("---------------------------------------");
				Elements titles = doc.getElementsByClass("hrs_grayBorderTitle");
				if (null != titles && null != titles.get(0)) {
					System.out.println(titles.get(0).childNode(0));
				}
				Elements jobInfos = doc.getElementsByClass("hrs_jobInfo");
				if (null != jobInfos && null != jobInfos.get(0)) {
					Element jobInfo = jobInfos.get(0);
					System.out.println(jobInfo.child(3).childNode(0)
							.childNode(0));
					System.out.println(jobInfo.child(5).childNode(0).toString()
							.trim());
					System.out.println(jobInfo.child(7).childNode(0)
							.childNode(0));
				}
				Elements hrs_jobDuties = doc.getElementsByClass("hrs_jobDuty");
				System.out.println("---------------------------------------");
				if (null != hrs_jobDuties && null != hrs_jobDuties.get(0)) {
					Element hrs_jobDuty = hrs_jobDuties.get(0);
					List<Node> nodes = hrs_jobDuty.childNode(3).childNodes();
					for (Node node : nodes) {
						if (!node.toString().equals("<br />")) {
							System.out.println(node.toString().trim());
						}
					}
				}
				Elements hrs_jobRequires = doc
						.getElementsByClass("hrs_jobRequire");
				System.out.println("---------------------------------------");
				if (null != hrs_jobRequires && null != hrs_jobRequires.get(0)) {
					Element hrs_jobRequire = hrs_jobRequires.get(0);
					List<Node> nodes = hrs_jobRequire.childNode(3).childNodes();
					for (Node node : nodes) {
						if (!node.toString().equals("<br />")) {
							System.out.println(node.toString().trim());
						}
					}
				}
			}
		}
	}
}
