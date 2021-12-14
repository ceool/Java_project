package com.joonhyun.webproject.dao;

import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 학생 Dao 맵퍼
 *
 * @author 20165315 박준현
 */
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
	 * 학생 등록
	 *
	 * @param studentMap
	 */
	void insertStudent(Map<String, Object> studentMap);

	/**
	 * 학생 테이블 카운트 조회
	 *
	 * @return
	 */
	Integer selectStudentCount();
}
