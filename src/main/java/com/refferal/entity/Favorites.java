package com.refferal.entity;

import java.util.Date;

import com.refferal.enums.CompanyEnum;

public class Favorites {

	private int id;
	
	private int userId;
	
	private int jdId;
	
	private Date createTime;
	
	private int companyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJdId() {
		return jdId;
	}

	public void setJdId(int jdId) {
		this.jdId = jdId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	private String jdName;

	private String city;
	
	private String department;
	
	private boolean status;
	
	public String getJdName() {
		return jdName;
	}

	public void setJdName(String jdName) {
		this.jdName = jdName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getCompanyName() {
		return CompanyEnum.getNameById(companyId);
	}
	
}
