create table PROFESSOR_ROOM
(
	professor_room_code varchar(30)                not null,
	professor_room_name varchar(60)                not null,
	create_date         DATETIME default sysdate() not null,
	update_date         DATETIME default sysdate() not null,
	constraint PROFESSOR_ROOM_pk
		primary key (professor_room_code)
);
