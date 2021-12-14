create table PROFESSOR
(
	professor_code varchar(30)                not null,
	room_code      varchar(30)                not null,
	name           varchar(30)                not null,
	gender         varchar(1)                 not null,
	birth          varchar(10)                not null,
	create_date    DATETIME default sysdate() not null,
	update_date    DATETIME default sysdate() not null,
	constraint PROFESSOR_pk
		primary key (professor_code)
);

create unique index PROFESSOR_room_code_uindex
	on PROFESSOR (room_code);
