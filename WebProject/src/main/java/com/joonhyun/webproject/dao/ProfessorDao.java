package com.joonhyun.webproject.dao;

import com.joonhyun.webproject.dao.vo.ProfessorVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 교수 Dao 맵퍼
 *
 * @author 20165315 박준현
 */
@Mapper
public interface ProfessorDao {

	/**
	 * 교수 리스트 조회
	 *
	 * @param pagination
	 * @return
	 */
	List<ProfessorVo> selectProfessorList(@Param("pagination") Pagination pagination);

	/**
	 * 교수 카운트 조회
	 *
	 * @return
	 */
	Integer selectProfessorCount();
}
