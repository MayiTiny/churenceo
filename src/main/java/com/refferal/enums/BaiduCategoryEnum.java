package com.refferal.enums;


public enum BaiduCategoryEnum {


	TECH(2, "技术"),

	PROD(3, "产品"),

	SERVICE(6, "客服服务"),

	MARKET(7, "市场及销售"),

	SALE(10, "市场及销售"),

	MANAGE(11,"管理支持"),
	
	OTHER(99, "其他");

	private int code;

	private String name;

	BaiduCategoryEnum(int code, String name) {
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
		for(BaiduCategoryEnum category :BaiduCategoryEnum.values()){
			if(category.getName().equals(name)){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}

}
