package com.refferal.quartz.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.cache.IndexJDCache;
import com.refferal.common.AppContext;
import com.refferal.crawler.JDCrawler;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.enums.CompanyEnum;

public class DayCircleJob {

	private final Logger log = Logger.getLogger(DayCircleJob.class);
	
	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	@Autowired
	private JDCrawler ali;

	@Autowired
	private JDCrawler baidu;

	@Autowired
	private JDCrawler sogou;
	
	@Autowired
	private JDCrawler meituan;
	
	@Autowired
	private JDCrawler tencent;
	
	@Autowired
	private JDCrawler vip;

	public void deploy() throws Exception {
		try {
			log.info("阿里爬虫启动");
			ali.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.ALIBABA.getCompanyId());
			log.info("阿里爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with ali", e);
		}
		try {
			log.info("百度爬虫启动");
			baidu.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.BAIDU.getCompanyId());
			log.info("百度爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with baidu", e);
		}
		try {
			log.info("搜狗爬虫开始");
			sogou.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.SOGOU.getCompanyId());
			log.info("搜狗爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with sogou", e);

		}
		try {
			log.info("腾讯爬虫开始");
			tencent.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.TENCENT.getCompanyId());
			log.info("腾讯爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with tencent", e);

		}
		try {
			log.info("美团爬虫开始");
			meituan.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.MEITUAN.getCompanyId());
			log.info("美团爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with meituan", e);

		}
		try {
			log.info("唯品会爬虫开始");
			vip.startCrawl();
			jobDescriptionDao.updateByCompany(CompanyEnum.VIP.getCompanyId());
			log.info("唯品会爬虫结束");
		} catch (Exception e) {
			log.error("oh my god! something was wrong with vip", e);

		}
		IndexJDCache.reload();
	}
}
