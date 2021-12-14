create table ATTEND_INTERVIEW
(
	interview_group_code varchar(30) not null,
	professor_code varchar(30) not null,
	create_date DATETIME default sysdate() not null,
	update_date DATETIME default sysdate() not null,

    	constraint attend_interview_interview_group_code_uindex
	        unique (interview_group_code),
	    constraint attend_interview_professor_code_uindex
	        unique (professor_code)
);