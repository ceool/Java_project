create table ATTEND_INTERVIEW
(
	interview_group_code varchar(30)                not null,
	professor_code       varchar(30)                not null,
	create_date          DATETIME default sysdate() not null,
	update_date          DATETIME default sysdate() not null,

	constraint attend_interview_pk
		unique (interview_group_code, professor_code)
);
