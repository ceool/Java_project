package com.joonhyun.webproject.dao;

import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentDao {

	/**
	 * 학생 리스트 조회
	 *
	 * @param pagination
	 * @return
	 */
	List<StudentVo> selectStudentList(@Param("pagination") Pagination pagination);

	/**
	 * 학생 테이블 카운트 조회
	 *
	 * @return
	 */
	Integer selectStudentCount();
}
