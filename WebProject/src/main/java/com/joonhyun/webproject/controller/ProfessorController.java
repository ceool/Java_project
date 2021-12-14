package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.dao.vo.ProfessorVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.impl.ProfessorServiceImpl;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 교수 Controller
 *
 * @author 20165315 박준현
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/professor")
public class ProfessorController {

	/**
	 * professorService
	 */
	private final ProfessorServiceImpl professorService;

	/**
	 * 교수 리스트 조회
	 *
	 * @param studentVo
	 * @param curPage
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public String StudentList(@ModelAttribute("StudentVo") StudentVo studentVo,
		@RequestParam(defaultValue = "1") int curPage, Model model) throws Exception {

		Pagination pagination = new Pagination(professorService.getProfessorCount(), curPage);

		List<ProfessorVo> professorList = professorService.getProfessorList(pagination);

		model.addAttribute("professorList", professorList);
		model.addAttribute("pagination", pagination);

		return "/professor/list";
	}
}
