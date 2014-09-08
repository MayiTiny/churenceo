package com.refferal.quartz.job;

import com.refferal.crawler.JDCrawler;
import com.refferal.crawler.impl.AliCrawler;
import com.refferal.crawler.impl.BaiduCrawler;

public class DayCircleJob {

	public void deploy() throws Exception{
		JDCrawler ali = new AliCrawler();
		JDCrawler baidu = new BaiduCrawler();
		ali.startCrawl();
		baidu.startCrawl();
	}
}
