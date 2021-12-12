package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.impl.StudentServiceImpl;
import com.joonhyun.webproject.util.Pagination;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

	/**
	 * StudentService
	 */
	private final StudentServiceImpl studentService;


	/**
	 * 학생 리스트 조회
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

		Pagination pagination = new Pagination(studentService.getStudentCount(), curPage);
		List<StudentVo> studentList = studentService.getStudentList(pagination);
		for(StudentVo student : studentList) {
			System.out.println(student.getName());

		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("pagination", pagination);

		return "/student/list";
	}

	@GetMapping("/add")
	public String StudentAdd() {

		return "/student/add";
	}

	@PostMapping("/add-exec")
	public String StudentAddExec(@RequestParam Map<String, Object> studentMap, Model model) {

		try {
			studentService.addStudent(studentMap);
		} catch (Exception e) {
			log.error("[카테고리 리스트 등록 오류]", e);
			model.addAttribute("status", "error");
			model.addAttribute("message", "등록에 실패하였습니다. 학생코드가 중복됩니다.");
			return "/student/add";
		}

		return "redirect:/student/list";
	}
}
