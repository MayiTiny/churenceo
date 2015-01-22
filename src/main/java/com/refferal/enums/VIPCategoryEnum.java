package com.refferal.enums;


public enum VIPCategoryEnum {

	GENERAL(1, "综合类"),

	TECH(2, "技术类"),

	PROD(3, "产品类"),

	OPER(4, "运营类"),

	DESIGN(5, "设计类"),

	SERVICE(6, "客服类"),

	MARKET(7, "市场类"),

	DATA(8, "数据类"),

	FINANCE(9, "金融类"),

	SALE(10, "商务类"),

	OTHER(99, "其他");

	private int code;

	private String name;

	VIPCategoryEnum(int code, String name) {
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
		for(VIPCategoryEnum category :VIPCategoryEnum.values()){
			if(category.getName().equals(name)){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}
	
	public static String getNameByCode(int code){
		for(VIPCategoryEnum category :VIPCategoryEnum.values()){
			if(category.getCode() == code){
				return category.getName();
			}
		}
		return OTHER.getName();
	}

}
