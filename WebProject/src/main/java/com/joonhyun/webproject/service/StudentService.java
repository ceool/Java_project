package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import java.util.Map;

public interface StudentService {

	List<StudentVo> getStudentList(Pagination pagination);

	void addStudent(Map<String, Object> studentMap);

	Integer getStudentCount();
}
