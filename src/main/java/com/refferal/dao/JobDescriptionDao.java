package com.refferal.dao;

import java.util.List;

import com.refferal.entity.JobDescription;

public interface JobDescriptionDao {

	List<JobDescription> getJobDescriptions(String keyword);

	void insert(JobDescription jd);

	JobDescription selectById(Integer id);
	
}
