package com.education.module.exam;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.beans.ApiResult;
import com.education.domain.Exam;
import com.education.domain.ResCourse;
import com.education.framework.base.BaseController;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.util.PinyinConv;
import com.education.module.resCourse.ResCourseServices;

@Controller
@RequestMapping("exam")
public class ExamController extends BaseController{

	@Autowired
	private ExamServices services;
	@Autowired
	private ResCourseServices resCourseServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<Exam> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/exam/examList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("action","create");
		return "/exam/examEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Exam exam, Model model,RedirectAttributes redirectAttributes) {
		services.save(exam);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/exam";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Exam exam = services.findForObject(id);
		model.addAttribute("exam", exam);
		model.addAttribute("courseList",resCourseServices.fillSelCourse(resCourseServices.find(), exam.getSelCourseArr()));
		model.addAttribute("action", "update");
		return "/exam/examEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Exam exam, RedirectAttributes redirectAttributes) {
		services.update(exam);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/exam";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/exam";
	}
	
	@RequestMapping(value = "findExamListAjax", method = RequestMethod.GET)
	@ResponseBody
	public List<String> findExamListAjax(@RequestParam(value="term",required=false) String name) {
		List<String> t = services.findExamNameList(name);
		return t;
	}
	
	@RequestMapping(value = "reqCachePaper", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult reqCachePaper(@RequestParam(value="examId",required=true) String examId) {
		boolean r = services.batchCachePaper(examId);
		ApiResult result = new ApiResult();
		return result;
	}
	
	@RequestMapping(value = "reqRemoveCachePaper", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult reqRemoveCachePaper(@RequestParam(value="examId",required=true) String examId) {
		boolean r = services.batchRemoveCachePaper(examId);
		ApiResult result = new ApiResult();
		return result;
	}
}
