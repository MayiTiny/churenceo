package com.refferal.quartz.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.cache.IndexJDCache;
import com.refferal.crawler.JDCrawler;

public class DayCircleJob {

	private final Logger log = Logger.getLogger(DayCircleJob.class);
	@Autowired
	private JDCrawler ali;

	@Autowired
	private JDCrawler baidu;

	@Autowired
	private JDCrawler qunar;
	
	@Autowired
	private JDCrawler meituan;

	public void deploy() throws Exception {
		try {
			log.info("阿里爬虫启动");
			ali.startCrawl();
			log.info("阿里爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with ali", e);
		}
		try {
			log.info("百度爬虫启动");
			baidu.startCrawl();
			log.info("百度爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with baidu", e);
		}
		try {
			log.info("去哪爬虫开始");
			qunar.startCrawl();
			log.info("去哪爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with qunar", e);

		}
		try {
			log.info("美团爬虫开始");
			meituan.startCrawl();
			log.info("美团爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with meituan", e);

		}
		IndexJDCache.reload();
	}
}
