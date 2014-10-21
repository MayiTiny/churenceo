apply2ali
=========
create table job_description (
	id int not null AUTO_INCREMENT comment '自增ID',
	company smallint not null default 0 comment '公司',
	name varchar(255) not null default '' comment '职位名称',
	head_count smallint not null default 0 comment '招聘人数',
	department varchar(255) not null default '' comment '部门',
	city_id varchar(63) not null default 0 comment '工作地点',
	begin_date timestamp comment '发布日期',
	recruit_type tinyint not null default 0 comment '招聘类型',
	function_type int not null default 0 comment '职能类别',
	end_date timestamp comment '失效日期',
	years_limit varchar(127) not null default '' comment '工作年限',
	degree varchar(127) not null default '' comment '学历要求',
	post_require text comment '岗位要求',
	post_description text comment '岗位描述',
	special_description text comment '特殊说明',
	primary key(id)
);
