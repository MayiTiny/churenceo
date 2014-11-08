package com.refferal.crawler.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.crawler.JDCrawler;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.enums.CompanyEnum;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class QunarCrawler implements JDCrawler {

	
	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	/**
	 * @param args
	 * @throws Exception
	 */
	public void startCrawl() throws Exception {
		String crawlStorageFolder = "data/crawl/root" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
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
			controller
					.addSeed("http://www.qunar.com/site/zh/zhaopin/prd/jobs.html?type=%E4%BA%A7%E5%93%81%E8%BF%90%E8%90%A5%E5%B2%97");
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(QunarImpl.class, 1);
	}

}
