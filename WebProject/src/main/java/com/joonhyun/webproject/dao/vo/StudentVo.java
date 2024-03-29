package com.joonhyun.webproject.dao.vo;

import java.util.Date;
import lombok.Getter;

/**
 * 학생 Vo
 *
 * @author 20165315 박준현
 */
@Getter
public class StudentVo {

	private String studentCode;
	private String interviewGroupCode;
	private String interviewGroupName;
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
