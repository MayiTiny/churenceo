package com.refferal.enums;

import org.apache.commons.lang3.StringUtils;

public enum CompanyEnum {
	
	ALIBABA("阿里巴巴",1),
	
	TENCENT("腾讯",8),
	
	BAIDU("百度",2),
	
	MEITUAN("美团",3),
	
	QUNAR("去哪儿",4),
	
	SOGOU("搜狗",5),
	
	SOHU_TV("搜狐视频",6),
	
	QIHU_360("360",7);
	
	private String companyName;

	private int companyId;

	CompanyEnum(String companyName, int companyId){
		this.companyName = companyName;
		this.companyId = companyId;
	}

	public static String getNameById(int id) {
		for (CompanyEnum company : CompanyEnum.values()) {
			if (company.getCompanyId() == id) {
				return company.getCompanyName();
			}
		}
		return StringUtils.EMPTY;
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
