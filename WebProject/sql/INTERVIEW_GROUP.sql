create table INTERVIEW_GROUP
(
	interview_group_code varchar(30) not null,
	interview_group_name varchar(60) not null,
	create_date DATETIME default sysdate() not null,
	update_date DATETIME default sysdate() not null,
	constraint INTERVIEW_GROUP_pk
		primary key (interview_group_code)
);