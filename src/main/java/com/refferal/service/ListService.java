package com.refferal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;

@Service
public class ListService {

	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	public List<JobDescription> search(String keyword) {
		keyword = '%' + keyword + '%';
		return jobDescriptionDao.getJobDescriptions(keyword);
	}

	public void insert(JobDescription jd) {
		jobDescriptionDao.insert(jd);
	}
	
}
