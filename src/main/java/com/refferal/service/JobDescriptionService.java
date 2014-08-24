package com.refferal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.refferal.common.PageList;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.enums.AliCategoryEnum;

@Service
public class JobDescriptionService {

	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	@SuppressWarnings("unchecked")
	public PageList<JobDescriptionDTO> search(String keyword, int start, int pageSize) {
		PageList<JobDescriptionDTO> pageList = new PageList<JobDescriptionDTO>();
		keyword = '%' + keyword + '%';
		int count = jobDescriptionDao.getJobDescriptionsCount(keyword);
		if (count > 0) {
			pageList.setCount(count);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("start", start);
			params.put("pageSize", pageSize);
			List<JobDescription> jds = jobDescriptionDao.getJobDescriptions(params);
			List<JobDescriptionDTO> jobs = new ArrayList<JobDescriptionDTO>();
			for(JobDescription jd : jds){
				JobDescriptionDTO job = convert2DTO(jd);
				jobs.add(job);
			}
			pageList.setList(jobs);
		} else {
			pageList.setList(Collections.EMPTY_LIST);
		}
		return pageList;
	}

	public void insert(JobDescription jd) {
		jobDescriptionDao.insert(jd);
	}

	public JobDescriptionDTO selectById(Integer id) {
		return convert2DTO(jobDescriptionDao.selectById(id));
	}
	
	private JobDescriptionDTO convert2DTO(JobDescription job){
		JobDescriptionDTO jobDesc = new JobDescriptionDTO();
		if(null != job){
			BeanUtils.copyProperties(job, jobDesc);
			jobDesc.setFunctionType(AliCategoryEnum.getNameByCode(job.getFunctionType()));
		}
		return jobDesc;
	}
	
}
