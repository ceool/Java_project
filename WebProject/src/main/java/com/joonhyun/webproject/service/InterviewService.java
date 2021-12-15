package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import com.joonhyun.webproject.model.InterviewDetailModel;
import java.util.List;

/**
 * 인터뷰 Service
 *
 * @author 20165315 박준현
 */
public interface InterviewService {

	List<InterviewGroupVo> getInterviewGroupList();

	List<InterviewDetailModel> getInterviewDetailList();
}
