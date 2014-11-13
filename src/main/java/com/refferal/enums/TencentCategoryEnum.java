package com.refferal.enums;

public enum TencentCategoryEnum {

	GENERAL(1, "职能类"),
	
	TECH(2, "技术类"),

	PROD(3, "产品/项目类 "),

	DESIGN(5, "设计类"),

	SERVICE(6, "客户服务类"),
	
	MARKET(7, "市场类"),

	OTHER(99, "其他");

	private int code;

	private String name;

	TencentCategoryEnum(int code, String name) {
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
		for(TencentCategoryEnum category : TencentCategoryEnum.values()){
			if(name.contains(category.getName())){
				return category.getCode();
			}
		}
		return OTHER.getCode();
	}
	
	public static String getNameByCode(int code){
		for(TencentCategoryEnum category : TencentCategoryEnum.values()){
			if(category.getCode() == code){
				return category.getName();
			}
		}
		return OTHER.getName();
	}
}
