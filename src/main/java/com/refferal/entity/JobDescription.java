package com.refferal.entity;

import java.util.Date;

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
	private String department;
	
	/**
	 * 工作地点
	 */
	private String cityId;
	
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
	private String yearsLimit;
	
	/**
	 * 学历要求
	 */
	private String degree;
	
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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

	public int getHeadCount() {
		return headCount;
	}

	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getYearsLimit() {
		return yearsLimit;
	}

	public void setYearsLimit(String yearsLimit) {
		this.yearsLimit = yearsLimit;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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
	
	public void print(){
		System.out.println(name);
		System.out.println(cityId);
		System.out.println(degree);
		System.out.println(department);
		System.out.println(headCount);
		System.out.println(postDescription);
		System.out.println(postRequire);
		
	}
	
}
