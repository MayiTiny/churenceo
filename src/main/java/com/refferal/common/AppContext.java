package com.refferal.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class AppContext implements ApplicationContextAware {

	private static ApplicationContext appContext;
	
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext = arg0; 
	}

	public static ApplicationContext getInstance() {
		return appContext;
	}
	
}
