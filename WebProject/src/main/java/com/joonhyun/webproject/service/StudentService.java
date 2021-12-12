package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;

public interface StudentService {

	List<StudentVo> getStudentList(Pagination pagination);

	Integer getStudentCount();
}
