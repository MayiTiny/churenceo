package com.refferal.dao;

import java.util.List;
import java.util.Map;

import com.refferal.entity.JobDescription;

public interface JobDescriptionDao {

	List<JobDescription> getJobDescriptions(Map<String, Object> params);

	void insert(JobDescription jd);

	JobDescription selectById(Integer id);
	
	int getJobDescriptionsCount(Map<String, Object> params);
	
	void deleteAll();

}
