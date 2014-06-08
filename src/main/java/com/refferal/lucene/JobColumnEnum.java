package com.refferal.lucene;

public enum JobColumnEnum {

	LINK("招聘官网链接","Link"),
	
	JOB_NAME("职位名称", "JobName"),

	HEAD_COUNT("招聘人数", "HeadCount"),

	LOCATION("工作地点", "Location"),

	DEPARTMENT("部门", "Department"),

	HIRE_TYPE("招聘类型", "HireType"),

	MAJOR("职能类别", "Major"),

	PUB_DATE("发布日期", "PubDate"),

	END_DATE("失效日期", "EndDate"),

	YEAR_LIMIT("工作年限", "YearLimit"),

	EDUCATION("学历要求", "Education"),

	JOB_REQUEST("岗位要求", "JobRequest"),

	DESCRIPTION("岗位描述", "Description"),

	SPECIAL_REQUIREMENT("特殊说明", "SpecialRequirement");

	String chName;

	String columnName;

	JobColumnEnum(String chName, String columnName) {
		this.chName = chName;
		this.columnName = columnName;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	static public JobColumnEnum getByName(String name){
		if(null == name){
			return null;
		}
		for(JobColumnEnum column: JobColumnEnum.values()){
			if(name.equals(column.getChName())){
				return column;
			}
		}
		return null;
	}
	
	
}
