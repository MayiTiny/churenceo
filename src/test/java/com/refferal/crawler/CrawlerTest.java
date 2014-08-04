package com.refferal.crawler;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.refferal.crawler.impl.AliCrawler;

public class CrawlerTest {

	@Test
	public void crawlerTest() throws Exception {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/springmvc-servlet.xml"); 
		JDCrawler aliCrawler = applicationContext.getBean(AliCrawler.class);
		aliCrawler.startCrawl();
	}
	
}
