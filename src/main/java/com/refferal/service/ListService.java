package com.refferal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.entity.JobDescription;
import com.refferal.mapper.JobDescriptionMapper;

@Service
public class ListService {

	@Autowired
	private JobDescriptionMapper jobDescriptionMapper;
	
	public List<JobDescription> search(String keyword) {
		return jobDescriptionMapper.getJobDescriptions(keyword);
	}

}
