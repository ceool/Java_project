package com.joonhyun.webproject.service.impl;

import com.joonhyun.webproject.dao.ProfessorDao;
import com.joonhyun.webproject.dao.vo.ProfessorVo;
import com.joonhyun.webproject.service.ProfessorService;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 교수 ServiceImpl
 *
 * @author 20165315 박준현
 */
@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

	/**
	 * ProfessorDao
	 */
	private final ProfessorDao professorDao;

	/**
	 * 교수 리스트 조회
	 *
	 * @param pagination
	 * @return
	 */
	@Override
	public List<ProfessorVo> getProfessorList(Pagination pagination) {
		return professorDao.selectProfessorList(pagination);
	}

	/**
	 * 교수 카운트 조회
	 *
	 * @return
	 */
	@Override
	public Integer getProfessorCount() {
		return professorDao.selectProfessorCount();
	}
}
