package com.joonhyun.webproject.dao;

import com.joonhyun.webproject.dao.vo.InterviewDetailVo;
import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 인터뷰 Dao 맵퍼
 *
 * @author 20165315 박준현
 */
@Mapper
public interface InterviewDao {

	/**
	 * 인터뷰 그룹 조회
	 *
	 * @return
	 */
	List<InterviewGroupVo> selectInterviewGroupList();

	/**
	 * 인터뷰 세부 조회
	 *
	 * @return
	 */
	List<InterviewDetailVo> selectInterviewDetailList();
}
