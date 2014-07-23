apply2ali
=========
create table job_description (
	id int not null AUTO_INCREMENT comment '自增ID',
	company smallint not null default 0 comment '公司',
	name varchar(255) not null default '' comment '职位名称',
	head_count smallint not null default 0 comment '招聘人数',
	department int not null default 0 comment '部门',
	city_id int not null default 0 comment '工作地点',
	begin_date timestamp comment '发布日期',
	recruit_type tinyint not null default 0 comment '招聘类型',
	function_type int not null default 0 comment '职能类别',
	end_date timestamp comment '失效日期',
	years_limit tinyint not null default 0 comment '工作年限',
	degree tinyint not null default 0 comment '学历要求',
	post_require text comment '岗位要求',
	post_description text comment '岗位描述',
	special_description text comment '特殊说明',
	primary key(id)
);
