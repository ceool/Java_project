package com.joonhyun.webproject.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InterviewDetailModel {

	private String interviewGroupCode;
	private String interviewGroupName;
	List<ProfessorInfo> professorList;

	@Getter
	@Builder
	public static class ProfessorInfo {

		private String name;
		private String gender;
		private String professorRoomName;
	}
}
