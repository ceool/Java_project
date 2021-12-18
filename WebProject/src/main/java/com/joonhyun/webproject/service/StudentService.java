package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.RequestStudentVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import java.util.Map;

/**
 * 학생 Service
 *
 * @author 20165315 박준현
 */
public interface StudentService {

	StudentVo getStudent(String studentCode);

	List<StudentVo> getStudentList(String entranceYn, Pagination pagination);

	List<RequestStudentVo> getRequestStudentList();

	void addStudent(Map<String, Object> studentMap);

	void modifyStudent(Map<String, Object> studentMap);

	void removeStudent(String studentCode);

	Integer getStudentCount();
}
