package com.joonhyun.webproject.service.impl;

import com.joonhyun.webproject.dao.InterviewDao;
import com.joonhyun.webproject.dao.vo.InterviewDetailVo;
import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import com.joonhyun.webproject.model.InterviewDetailModel;
import com.joonhyun.webproject.model.InterviewDetailModel.ProfessorInfo;
import com.joonhyun.webproject.service.InterviewService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 인터뷰 ServiceImpl
 *
 * @author 20165315 박준현
 */
@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

	/**
	 * InterviewDao
	 */
	private final InterviewDao interviewDao;

	/**
	 * 인터뷰그룹 리스트 조회
	 *
	 * @return
	 */
	@Override
	public List<InterviewGroupVo> getInterviewGroupList() {
		return interviewDao.selectInterviewGroupList();
	}

	/**
	 * 인터뷰 세부 리스트 조회
	 *
	 * @return
	 */
	@Override
	public List<InterviewDetailModel> getInterviewDetailList() {
		List<InterviewDetailVo> interviewDetailList = interviewDao.selectInterviewDetailList();
		List<InterviewDetailModel> InterviewDetailModelList = new ArrayList<>();
		List<ProfessorInfo> professorList = new ArrayList<>();

		ProfessorInfo professorInfo;
		InterviewDetailModel interviewDetailModel;

		String interviewGroupCode = null;
		String interviewGroupName = null;

		for (InterviewDetailVo interviewDetail : interviewDetailList) {
			if (interviewGroupCode != null && !interviewDetail.getInterviewGroupCode()
				.equals(interviewGroupCode)) {
				interviewDetailModel = InterviewDetailModel.builder()
					.interviewGroupCode(interviewGroupCode)
					.interviewGroupName(interviewGroupName)
					.professorList(professorList)
					.build();
				InterviewDetailModelList.add(interviewDetailModel);
				professorList = new ArrayList<>();
			}

			professorInfo = ProfessorInfo.builder()
				.name(interviewDetail.getName())
				.gender(interviewDetail.getGender())
				.professorRoomName(interviewDetail.getProfessorRoomName())
				.build();
			professorList.add(professorInfo);

			interviewGroupCode = interviewDetail.getInterviewGroupCode();
			interviewGroupName = interviewDetail.getInterviewGroupName();
		}

		interviewDetailModel = InterviewDetailModel.builder()
			.interviewGroupCode(interviewGroupCode)
			.interviewGroupName(interviewGroupName)
			.professorList(professorList)
			.build();
		InterviewDetailModelList.add(interviewDetailModel);

		return InterviewDetailModelList;
	}
}
