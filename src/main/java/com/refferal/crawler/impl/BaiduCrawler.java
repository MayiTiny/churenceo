package com.refferal.crawler.impl;

import com.refferal.crawler.JDCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BaiduCrawler implements JDCrawler {

	/**
	 * @param args
	 * @throws Exception
	 */
	public void startCrawl() throws Exception {

		String crawlStorageFolder = "data/crawl/root";

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		int pageIndex = 1;
		for (int i = 0; i < 10; i++) {
			controller
					.addSeed("http://talent.baidu.com/baidu/web/templet1000/index/corpwebPosition1000baidu!getPostListByConditionBaidu?pc.currentPage="
							+ pageIndex++
							+ "&pc.rowSize=10&releaseTime=&keyWord=&positionType=0&trademark=1&workPlaceCode=&positionName=&recruitType=2&brandCode=1&searchType=1&workPlaceNameV=&positionTypeV=0&keyWordV=");
		}
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(JobCrawler.class, 10);
	}

}
