package com.refferal.enums;

public enum MeituanCategoryEnum {

	GENERAL(1, "综合类"),

	TECH(2, "技术工程部"),
	
	PLATFORM(2, "平台业务部"),

	PROD(3, "产品部"),

	OPER(4, "运营部"),

	DESIGN(5, "设计部"),

	SERVICE(6, "客服部"),

	MARKET(7, "市场部"),

	SALE(10, "销售部"),

	OTHER(99, "其他");

	private int code;

	private String name;

	MeituanCategoryEnum(int code, String name) {
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
		for(MeituanCategoryEnum category : MeituanCategoryEnum.values()){
			if(category.getName().equals(name)){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}
	
	public static String getNameByCode(int code){
		for(MeituanCategoryEnum category : MeituanCategoryEnum.values()){
			if(category.getCode() == code){
				return category.getName();
			}
		}
		return OTHER.getName();
	}
}
