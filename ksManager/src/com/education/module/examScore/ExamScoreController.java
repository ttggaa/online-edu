package com.education.module.examScore;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.ExamCourse;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.base.BaseController;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.module.examScore.ExamScoreServices;
import com.education.framework.util.calendar.CalendarUtil;

@Controller
@RequestMapping("examScore")
public class ExamScoreController extends BaseController{

	@Autowired
	private ExamScoreServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<ExamCourse> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/examScore/examScoreList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		return "/examScore/examScoreEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ExamCourse examScore, Model model,RedirectAttributes redirectAttributes) {
		services.save(examScore);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examScore";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		ExamCourse examScore = services.findForObject(id);
		model.addAttribute("examScore", examScore);
		model.addAttribute("action", "update");
		return "/examScore/examScoreEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ExamCourse examScore, RedirectAttributes redirectAttributes) {
		services.update(examScore);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examScore";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examScore";
	}
	
}
