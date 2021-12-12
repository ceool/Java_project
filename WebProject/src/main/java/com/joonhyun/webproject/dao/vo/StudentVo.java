package com.joonhyun.webproject.dao.vo;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;

@Getter
public class StudentVo {

	private String studentCode;
	private String interviewGroupCode;
	private String name;
	private String gender;
	private String birth;
	private String passYn;
	private String entranceYn;
	private String friendshipYn;
	private String friendshipDescription;
	private Date createDate;
	private Date updateDate;
}
