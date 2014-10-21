package com.refferal.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.crawler.JDCrawler;

public class DayCircleJob {

	@Autowired
	private JDCrawler ali;
	
	@Autowired
	private JDCrawler baidu;
	
	@Autowired
	private JDCrawler qunar;
	public void deploy() throws Exception{
		try{
			ali.startCrawl();
		}catch(Exception e){
		}
		baidu.startCrawl();
		qunar.startCrawl();
	}
}
