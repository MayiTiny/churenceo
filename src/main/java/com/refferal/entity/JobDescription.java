package com.refferal.entity;

import java.util.Date;

/**
 * @author tiny
 *
 */
/**
 * @author tiny
 *
 */
public class JobDescription {

	private int id;
	
	/**
	 * 职位名称
	 */
	private String name;
	
	/**
	 * 招聘人数
	 */
	private int headCount;
	
	/**
	 * 部门
	 */
	private int department;
	
	/**
	 * 工作地点
	 */
	private int cityId;
	
	/**
	 * 发布日期
	 */
	private Date beginDate;
	
	/**
	 * 招聘类型
	 */
	private int recruitType;
	
	/**
	 * 职能类别
	 */
	private int functionType;
	
	/**
	 * 失效日期
	 */
	private Date endDate;
	
	/**
	 * 工作年限
	 */
	private int yearsLimit;
	
	/**
	 * 学历要求
	 */
	private int degree;
	
	/**
	 * 岗位要求
	 */
	private String postRequire;
	
	/**
	 * 岗位描述
	 */
	private String postDescription;
	
	/**
	 * 特殊说明
	 */
	private String specialDescription;
	
	/**
	 * 公司
	 */
	private int company;

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
		return headCount;
	}

	public void setAmount(int amount) {
		this.headCount = amount;
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

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
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
