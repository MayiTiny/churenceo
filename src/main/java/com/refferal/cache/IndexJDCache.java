package com.refferal.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.refferal.common.AppContext;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;

public class IndexJDCache {

	private static final List<JobDescription> LATESTS = new ArrayList<JobDescription>();
	
	private static final List<JobDescription> RECOMMENDS = new ArrayList<JobDescription>();
	
	static {
		reload();
	}
	
	public static List<JobDescription> getLatests() {
		return LATESTS;
	}
	
	public static List<JobDescription> getRecommends() {
		return RECOMMENDS;
	}
	
	public static void reload() {
		JobDescriptionDao dao = AppContext.getInstance().getBean(JobDescriptionDao.class);
		Map<String, Object> params = new HashMap<String, Object>();
		
		LATESTS.clear();
		params.put("company", 3);
		LATESTS.add(dao.getLatest(params));
		params.put("company", 4);
		LATESTS.add(dao.getLatest(params));
		
		RECOMMENDS.clear();
		params.put("company", 1);
		params.put("keyword", "java");
		RECOMMENDS.add(dao.getLatest(params));
		params.put("company", 2);
		params.put("keyword", "产品");
		RECOMMENDS.add(dao.getLatest(params));
		
	}
	
}
