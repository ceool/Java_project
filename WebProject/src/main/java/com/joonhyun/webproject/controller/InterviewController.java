package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.model.InterviewDetailModel;
import com.joonhyun.webproject.service.impl.InterviewServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/interview")
public class InterviewController {

	/**
	 * InterviewServiceImpl
	 */
	private final InterviewServiceImpl interviewService;

	/**
	 * 인터뷰 리스트
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public String interviewList(Model model) throws Exception {

		List<InterviewDetailModel> interviewDetailList = interviewService.getInterviewDetailList();

		model.addAttribute("interviewDetailList", interviewDetailList);

		return "/interview/list";
	}
}
