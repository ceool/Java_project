package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.dao.vo.RequestStudentVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.impl.StudentServiceImpl;
import com.joonhyun.webproject.util.Pagination;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	 * StudentService
	 */
	private final StudentServiceImpl studentService;


	/**
	 * 메인페이지 - 청탁 학생 리스트
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/")
	public String main(Model model) throws Exception {
		List<StudentVo> studentList = studentService.getStudentList("Y", new Pagination(0, 0));

		// request = 0 -> 정상적인 입학 학생
		// request = 1 -> 청탁일 수 있는 학생
		// request = 2 -> 청탁으로 의심되는 학생
		// request = 3 -> 청탁 학생
		List<RequestStudentVo> requestStudentList = studentService.getRequestStudentList();

		List<RequestStudentVo> request0List = new ArrayList<>();
		List<RequestStudentVo> request1List = new ArrayList<>();
		List<RequestStudentVo> request2List = new ArrayList<>();
		List<RequestStudentVo> request3List = new ArrayList<>();

		for (RequestStudentVo requestStudentVo : requestStudentList) {
			if (requestStudentVo.getRequest().equals("0")) {
				request0List.add(requestStudentVo);
			} else if (requestStudentVo.getRequest().equals("1")) {
				request1List.add(requestStudentVo);
			} else if (requestStudentVo.getRequest().equals("2")) {
				request2List.add(requestStudentVo);
			} else {
				request3List.add(requestStudentVo);
			}
		}

		model.addAttribute("studentList", studentList);
		model.addAttribute("request0List", request0List);
		model.addAttribute("request1List", request1List);
		model.addAttribute("request2List", request2List);
		model.addAttribute("request3List", request3List);

		return "/index";
	}
}
