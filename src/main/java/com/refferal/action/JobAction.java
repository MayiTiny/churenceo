package com.refferal.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.refferal.quartz.job.DayCircleJob;

@Controller
public class JobAction {

	private static final Log LOGGER = LogFactory.getLog(JobAction.class);
	
	@Autowired
	private DayCircleJob dayCircleJob;
	
	private static long now = 0;
	
	@RequestMapping("/crawlnow")
	public String excute() {
		if (System.currentTimeMillis() - now < 60 * 60) {
			return "太短了，没狗长。";
		}
		now = System.currentTimeMillis();
		try {
			dayCircleJob.deploy();
		} catch (Exception e) {
			LOGGER.error("调用爬虫job失败。", e);
			return "调用爬虫job失败。";
		}
		return "开爬。";
	}
	
}
