package com.refferal.enums;

public enum CompanyEnum {
	
	ALIBABA("阿里巴巴",1),
	
	BAIDU("百度",2),
	
	MEITUAN("美团网",3),
	
	QUNAR("去哪网",4);
	
	private String companyName;

	private int companyId;

	CompanyEnum(String companyName, int companyId){
		this.companyName = companyName;
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
}
