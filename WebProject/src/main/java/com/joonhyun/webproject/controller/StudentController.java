package com.joonhyun.webproject.controller;

import com.joonhyun.webproject.dao.vo.InterviewGroupVo;
import com.joonhyun.webproject.dao.vo.StudentVo;
import com.joonhyun.webproject.service.impl.InterviewGroupServiceImpl;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 학생 Controller
 *
 * @author 20165315 박준현
 */
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
	 * InterviewGroupService
	 */
	private final InterviewGroupServiceImpl interviewGroupService;

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

		model.addAttribute("studentList", studentList);
		model.addAttribute("pagination", pagination);

		return "/student/list";
	}

	/**
	 * 학생 등록 페이지
	 *
	 * @return
	 */
	@GetMapping("/add")
	public String StudentAdd(Model model) {
		try {
			List<InterviewGroupVo> interviewGroupList = interviewGroupService.getInterviewGroupList();
			model.addAttribute("interviewGroupList", interviewGroupList);
		} catch (Exception e) {
			log.error("[/student/add GET 오류]", e);
		}
		return "/student/add";
	}

	/**
	 * 학생 수정 페이지
	 *
	 * @param model
	 * @param studentCode
	 * @return
	 */
	@GetMapping("/modify/{studentCode}")
	public String StudentModify(Model model, @PathVariable String studentCode) {
		try {
			StudentVo studentVo = studentService.getStudent(studentCode);
			List<InterviewGroupVo> interviewGroupList = interviewGroupService.getInterviewGroupList();

			model.addAttribute("studentVo", studentVo);
			model.addAttribute("interviewGroupList", interviewGroupList);

		} catch (Exception e) {
			log.error("[/student/modify GET 오류]", e);
		}
		return "/student/modify";
	}

	/**
	 * 학생 등록 Exec
	 *
	 * @param studentMap
	 * @param model
	 * @return
	 */
	@PostMapping("/add-exec")
	public String StudentAddExec(@RequestParam Map<String, Object> studentMap, Model model) {

		try {
			studentService.addStudent(studentMap);
		} catch (Exception e) {
			log.error("[학생 등록 오류]", e);
			model.addAttribute("status", "error");
			model.addAttribute("message", "등록에 실패하였습니다. 학생코드가 중복됩니다.");
			return "/student/add";
		}

		return "redirect:/student/list";
	}

	/**
	 * 학생 등록 Exec
	 *
	 * @param studentMap
	 * @param model
	 * @return
	 */
	@PostMapping("/modify-exec")
	public String StudentModifyExec(@RequestParam Map<String, Object> studentMap, Model model) {

		try {
			studentService.modifyStudent(studentMap);
		} catch (Exception e) {
			log.error("[학생 수정 오류]", e);
			model.addAttribute("status", "error");
			model.addAttribute("message", "수정에 실패하였습니다. 다시 시도해주세요.");
			return "/student/modify";
		}

		return "redirect:/student/list";
	}

	/**
	 * 학생 삭제 Exec
	 *
	 * @param studentCode
	 * @param model
	 * @return
	 */
	@PostMapping("/remove-exec")
	public String StudentRemoveExec(@RequestParam String studentCode, Model model) {

		try {
			studentService.removeStudent(studentCode);
		} catch (Exception e) {
			log.error("[학생 삭제 오류]", e);
			model.addAttribute("status", "error");
			model.addAttribute("message", "삭제에 실패하였습니다. 다시 시도해주세요.");
			return "/student/modify";
		}

		model.addAttribute("status", "success");
		model.addAttribute("message", "삭제에 성공하였습니다. 리스트 화면으로 돌아갑니다.");

		return "/student/modify";
	}
}
