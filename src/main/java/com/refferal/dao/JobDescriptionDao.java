package com.refferal.dao;

import java.util.List;
import java.util.Map;

import com.refferal.entity.JobDescription;

public interface JobDescriptionDao {

	List<JobDescription> getJobDescriptions(Map<String, Object> params);

	int insert(JobDescription jd);

	JobDescription selectById(Integer id);
	
	int getJobDescriptionsCount(Map<String, Object> params);
	
	void deleteAll();

	JobDescription getLatest(Map<String, Object> params);
	
	void updateByCompany(Integer company);
	
	void updateById(Integer id);
	
	int selectExsit(JobDescription jd);
}
