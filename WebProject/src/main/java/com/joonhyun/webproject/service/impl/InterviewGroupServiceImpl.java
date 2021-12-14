package com.joonhyun.webproject.service.impl;

import com.joonhyun.webproject.dao.InterviewGroupDao;
import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import com.joonhyun.webproject.service.InterviewGroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 인터뷰그룹 ServiceImpl
 *
 * @author 20165315 박준현
 */
@Service
@RequiredArgsConstructor
public class InterviewGroupServiceImpl implements InterviewGroupService {

	/**
	 * InterviewGroupDao
	 */
	private final InterviewGroupDao interviewGroupDao;

	/**
	 * 인터뷰그룹 리스트 조회
	 *
	 * @return
	 */
	@Override
	public List<InterviewGroupVo> getInterviewGroupList() {
		return interviewGroupDao.selectInterviewGroupList();
	}
}
