create table APPLY_STUDENT
(
	student_code varchar(30) not null,
	interview_group_code varchar(30) not null,
	name varchar(30) not null,
	gender varchar(1) not null,
	birth varchar(10) not null,
	pass_yn varchar(1) null,
	entrance_yn varchar(1) null,
	friendship_yn varchar(1) default 'N' not null,
	friendship_description mediumtext null,
	create_date DATETIME default SYSDATE() not null,
	update_date DATETIME default SYSDATE() not null,
	constraint APPLY_STUDENT_pk
		primary key (student_code)
);

