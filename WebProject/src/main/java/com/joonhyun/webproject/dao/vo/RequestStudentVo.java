package com.joonhyun.webproject.dao.vo;

import java.util.Date;
import lombok.Getter;

/**
 * 청탁학생 Vo
 *
 * @author 20165315 박준현
 */
@Getter
public class RequestStudentVo {

	private String studentCode;
	private String name;
	private String interviewGroupName;
	private String gender;
	private String friendshipYn;
	private String friendshipDescription;
	private String request;
	private Date createDate;
}
