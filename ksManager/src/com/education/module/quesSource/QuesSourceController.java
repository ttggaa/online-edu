package com.education.module.quesSource;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.QuesSource;
import com.education.framework.base.BaseController;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Controller
@RequestMapping("quesSource")
public class QuesSourceController extends BaseController{

	@Autowired
	private QuesSourceServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<QuesSource> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/quesSource/quesSourceList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		return "/quesSource/quesSourceEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(QuesSource quesSource, Model model,RedirectAttributes redirectAttributes) {
		quesSource.setBusinessId(1);
		quesSource.setRole("");
		services.save(quesSource);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/quesSource";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		QuesSource quesSource = services.findForObject(id);
		model.addAttribute("quesSource", quesSource);
		model.addAttribute("quesSourceList",services.findQuesSourceAnalysis(quesSource.getId()));
		model.addAttribute("action", "update");
		return "/quesSource/quesSourceEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(QuesSource quesSource, RedirectAttributes redirectAttributes) {
		quesSource.setBusinessId(1);
		quesSource.setRole("");
		services.update(quesSource);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/quesSource";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/quesSource";
	}
	
}
