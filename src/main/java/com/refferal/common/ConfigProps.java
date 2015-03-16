package com.refferal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigProps {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigProps.class);
	
	private static final Properties PROPS = new Properties();
	static {
		InputStream is = ConfigProps.class.getClassLoader().getResourceAsStream("config.properties");  
		try {  
			PROPS.load(is);  
			is.close();  
		} catch (IOException e) {  
			LOGGER.error("读取配置文件失败！", e);
		}  
	}
	
     public static String getProp(String key) {
    	 return PROPS.getProperty(key);
     }
     
}
