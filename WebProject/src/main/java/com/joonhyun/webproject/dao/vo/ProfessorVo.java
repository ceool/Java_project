package com.joonhyun.webproject.dao.vo;

import java.util.Date;
import lombok.Getter;

/**
 * 교수 Vo
 *
 * @author 20165315 박준현
 */
@Getter
public class ProfessorVo {

	private String professorCode;
	private String roomCode;
	private String professorRoomName;
	private String name;
	private String gender;
	private String birth;
	private Date createDate;
	private Date updateDate;
}
