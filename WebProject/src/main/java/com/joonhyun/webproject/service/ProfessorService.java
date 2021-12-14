package com.joonhyun.webproject.service;

import com.joonhyun.webproject.dao.vo.ProfessorVo;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;

/**
 * 교수 Service
 *
 * @author 20165315 박준현
 */
public interface ProfessorService {

	List<ProfessorVo> getProfessorList(Pagination pagination);

	Integer getProfessorCount();
}
