package com.refferal.quartz.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.crawler.JDCrawler;

public class DayCircleJob {

	private final Logger log = Logger.getLogger(DayCircleJob.class);
	@Autowired
	private JDCrawler ali;

	@Autowired
	private JDCrawler baidu;

	@Autowired
	private JDCrawler qunar;

	public void deploy() throws Exception {
		try {
			ali.startCrawl();
		} catch (Exception e) {
			log.error("oh my god! something was wrong with ali", e);
		}
		try {
			baidu.startCrawl();
		} catch (Exception e) {
			log.error("oh my god! something was wrong with baidu", e);
		}
		try {
			qunar.startCrawl();
		} catch (Exception e) {
			log.error("oh my god! something was wrong with qunar", e);

		}
		log.info("oh my god! the crawler of meituan is missing!");
	}
}
