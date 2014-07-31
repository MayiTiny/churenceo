package com.refferal.enums;


public enum AliCategoryEnum {

	GENERAL(1, "综合类"),

	TECH(2, "技术类"),

	PROD(3, "产品类"),

	OPER(4, "运营类"),

	DESIGN(5, "设计类"),

	SERVICE(6, "客服类"),

	MARKET(7, "市场拓展"),

	DATA(8, "数据类"),

	FINANCE(9, "金融类"),

	SALE(10, "销售类"),

	OTHER(99, "其他");

	private int code;

	private String name;

	AliCategoryEnum(int code, String name) {
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
		for(AliCategoryEnum category :AliCategoryEnum.values()){
			if(category.getName().equals(name)){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}

}
