package com.refferal.crawler;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.refferal.crawler.impl.AliCrawler;
import com.refferal.crawler.impl.BaiduCrawler;
import com.refferal.crawler.impl.QunarCrawler;

public class CrawlerTest {

	@Test
	public void crawlerTest() throws Exception {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/springmvc-servlet.xml");
		QunarCrawler aliCrawler = (QunarCrawler)applicationContext.getBean("qunar");
		aliCrawler.startCrawl();
	}

}
