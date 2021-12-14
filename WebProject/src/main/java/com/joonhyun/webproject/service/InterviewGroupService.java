package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import java.util.List;

/**
 * 인터뷰그룹 Service
 *
 * @author 20165315 박준현
 */
public interface InterviewGroupService {

	List<InterviewGroupVo> getInterviewGroupList();
}
