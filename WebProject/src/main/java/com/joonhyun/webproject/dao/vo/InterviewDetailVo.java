package com.joonhyun.webproject.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인터뷰 세부 Vo
 *
 * @author 20165315 박준현
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDetailVo {

	private String interviewGroupCode;
	private String interviewGroupName;
	private String name;
	private String gender;
	private String professorRoomName;
}
