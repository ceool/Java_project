package com.joonhyun.webproject.service.impl;

import com.joonhyun.webproject.dao.StudentDao;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.StudentService;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	/**
	 * StudentDao
	 */
	private final StudentDao studentDao;

	/**
	 * 학생 리스트 조회
	 *
	 * @param pagination
	 * @return
	 */
	@Override
	public List<StudentVo> getStudentList(Pagination pagination) {
		return studentDao.selectStudentList(pagination);
	}

	/**
	 * 학생 등록
	 *
	 * @param studentMap
	 */
	@Override
	public void addStudent(Map<String, Object> studentMap) {
		studentDao.insertStudent(studentMap);
	}

	/**
	 * 학생 테이블 카운트 조회
	 *
	 * @return
	 */
	@Override
	public Integer getStudentCount() {
		return studentDao.selectStudentCount();
	}
}
