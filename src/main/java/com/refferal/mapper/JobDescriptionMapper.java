package com.refferal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.refferal.entity.JobDescription;

@Repository
public interface JobDescriptionMapper {
	
//    @Results(value = { @Result(id = true, property = "id", column = "id"),  
//            @Result(property = "parentId", column = "c_parent_id"),  
//            @Result(property = "url", column = "c_url"),  
//            @Result(property = "isShowLeft", column = "c_is_show_left"),  
//            @Result(property = "name", column = "c_name") })  
	@Select("SELECT * FROM job_description WHERE name like #{keyword}")  
    List<JobDescription> getJobDescriptions(@Param("keyword") String keyword); 

}
