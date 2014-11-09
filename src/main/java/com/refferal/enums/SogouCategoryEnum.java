package com.refferal.enums;

public enum SogouCategoryEnum {


	TECH(2, "工程"),
	
	RESEARCH(2, "研究"),

	PROD(3, "产品"),

	OPER(4, "运营"),

	DESIGN(5, "设计"),

	SERVICE(6, "客服"),

	MARKET(7, "市场"),

	SALE(10, "销售"),

	OTHER(99, "其他");

	private int code;

	private String name;

	SogouCategoryEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static int getCodeByName(String name){
		for(SogouCategoryEnum category : SogouCategoryEnum.values()){
			if(name.contains(category.getName())){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}
	
	public static String getNameByCode(int code){
		for(SogouCategoryEnum category : SogouCategoryEnum.values()){
			if(category.getCode() == code){
				return category.getName();
			}
		}
		return OTHER.getName();
	}
}
