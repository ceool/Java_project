package com.joonhyun.webproject.service.impl;

import com.joonhyun.webproject.dao.StudentDao;
import com.joonhyun.webproject.dao.vo.RequestStudentVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.StudentService;
import com.joonhyun.webproject.util.Pagination;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 학생 ServiceImpl
 *
 * @author 20165315 박준현
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	/**
	 * StudentDao
	 */
	private final StudentDao studentDao;

	/**
	 * 학생 조회
	 *
	 * @param studentCode
	 * @return
	 */
	@Override
	public StudentVo getStudent(String studentCode) {
		return studentDao.selectStudent(studentCode);
	}

	/**
	 * 학생 리스트 조회
	 *
	 * @param pagination
	 * @return
	 */
	@Override
	public List<StudentVo> getStudentList(String entranceYn, Pagination pagination) {
		return studentDao.selectStudentList(entranceYn, pagination);
	}

	/**
	 * 청탁 학생 리스트 조회
	 *
	 * @return
	 */
	@Override
	public List<RequestStudentVo> getRequestStudentList() {
		List<RequestStudentVo> requestStudentList = studentDao.selectRequestStudentList();

		// 중복제거
		// left join으로 방문자가 중복일 경우, 청탁학생 -> 청탁의심 -> 청탁주의 -> 대상아님 우선순위로 저장
		List<RequestStudentVo> newStudentList = new ArrayList<>();

		String studentCode = null;
		for(RequestStudentVo requestStudentVo : requestStudentList) {
			if(!requestStudentVo.getStudentCode().equals(studentCode)) {
				newStudentList.add(requestStudentVo);
			}
			studentCode = requestStudentVo.getStudentCode();
		}

		return newStudentList;
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
	 * 학생 수정
	 *
	 * @param studentMap
	 */
	@Override
	public void modifyStudent(Map<String, Object> studentMap) {
		studentDao.updateStudent(studentMap);
	}

	/**
	 * 학생 삭제
	 *
	 * @param studentCode
	 */
	@Override
	public void removeStudent(String studentCode) {
		studentDao.deleteStudent(studentCode);
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
