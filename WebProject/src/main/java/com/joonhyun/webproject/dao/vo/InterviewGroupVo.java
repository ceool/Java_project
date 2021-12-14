package com.joonhyun.webproject.dao.vo;

import java.util.Date;
import lombok.Getter;

/**
 * 인터뷰그룹 Vo
 *
 * @author 20165315 박준현
 */
@Getter
public class InterviewGroupVo {

	private String interviewGroupCode;
	private String interviewGroupName;
	private Date createDate;
	private Date updateDate;
}
