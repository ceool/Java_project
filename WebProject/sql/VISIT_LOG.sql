create table VISIT_LOG
(
	seq int auto_increment,
	student_code varchar(30) null,
	professor_code varchar(30) null,
	name varchar(30) not null,
	birth varchar(10) not null,
	room_code varchar(30) null,
	visit_date DATETIME default SYSDATE() not null,
	exit_date DATETIME null,
	constraint VISIT_LOG_pk
		primary key (seq)
);

