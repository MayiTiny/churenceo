package com.refferal.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.common.PageList;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;

@Service
public class JobDescriptionService {

	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	@SuppressWarnings("unchecked")
	public PageList<JobDescription> search(String keyword, int start, int pageSize) {
		PageList<JobDescription> pageList = new PageList<JobDescription>();
		keyword = '%' + keyword + '%';
		int count = jobDescriptionDao.getJobDescriptionsCount(keyword);
		if (count > 0) {
			pageList.setCount(count);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("start", start);
			params.put("pageSize", pageSize);
			List<JobDescription> jds = jobDescriptionDao.getJobDescriptions(params);
			pageList.setList(jds);
		} else {
			pageList.setList(Collections.EMPTY_LIST);
		}
		return pageList;
	}

	public void insert(JobDescription jd) {
		jobDescriptionDao.insert(jd);
	}

	public JobDescription selectById(Integer id) {
		return jobDescriptionDao.selectById(id);
	}
	
}
