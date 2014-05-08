package com.refferal.entity;

import java.util.Date;

public class JobDescription {

	private int id;
	
	private String name;
	
	private int cityId;
	
	private int department;
	
	private Date beginDate;
	
	private Date endDate;
	
	private int amount;
	
	private int degree;
	
	private int yearsLimit;
	
	/**
	 * 职能类别
	 */
	private int functionType;
	
	/**
	 * 招聘类型
	 */
	private int recruitType;
	
	/**
	 * 工作性质
	 */
	private int jobNature;

	private String postDescription;
	
	private String postRequire;
	
	private String specialDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getYearsLimit() {
		return yearsLimit;
	}

	public void setYearsLimit(int yearsLimit) {
		this.yearsLimit = yearsLimit;
	}

	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	public int getRecruitType() {
		return recruitType;
	}

	public void setRecruitType(int recruitType) {
		this.recruitType = recruitType;
	}

	public int getJobNature() {
		return jobNature;
	}

	public void setJobNature(int jobNature) {
		this.jobNature = jobNature;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getPostRequire() {
		return postRequire;
	}

	public void setPostRequire(String postRequire) {
		this.postRequire = postRequire;
	}

	public String getSpecialDescription() {
		return specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}
	
}
