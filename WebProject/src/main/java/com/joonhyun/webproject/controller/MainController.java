package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.dao.vo.ProfessorVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.impl.InterviewServiceImpl;
import com.joonhyun.webproject.service.impl.ProfessorServiceImpl;
import com.joonhyun.webproject.service.impl.StudentServiceImpl;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 메인 Controller
 *
 * @author 20165315 박준현
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

	/**
	 * professorService
	 */
	private final ProfessorServiceImpl professorService;

	/**
	 * StudentService
	 */
	private final StudentServiceImpl studentService;

	/**
	 * InterviewGroupService
	 */
	private final InterviewServiceImpl interviewGroupService;


	@GetMapping("/")
	public String main(@ModelAttribute("StudentVo") StudentVo studentVo,
		@RequestParam(defaultValue = "1") int curPage, Model model) throws Exception {

		Pagination pagination = new Pagination(professorService.getProfessorCount(), curPage);

		List<ProfessorVo> professorList = professorService.getProfessorList(pagination);

		model.addAttribute("professorList", professorList);
		model.addAttribute("pagination", pagination);

		return "/index";
	}
}
