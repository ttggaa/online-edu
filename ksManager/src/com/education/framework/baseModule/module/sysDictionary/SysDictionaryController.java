package com.education.framework.baseModule.module.sysDictionary;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.cache.CacheManager;

@Controller
@RequestMapping("framework/sysDictionary")
public class SysDictionaryController extends BaseController{

	@Autowired
	private SysDictionaryServices services;
	
	@Autowired
	private CacheManager cache;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysDictionary> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("fieldsList", services.findByFieldsList());
		return "/framework/sysDictionary/sysDictionaryList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		return "/framework/sysDictionary/sysDictionaryEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysDictionary sysDictionary, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysDictionary.setCreateUser(sessionUser.getId());
		sysDictionary.setUpdateUser(sessionUser.getId());
		services.save(sysDictionary);
		ApplicationHelper.getInstance().setSysDictionaryList(services.find());
		cache.setDictList(ApplicationHelper.getInstance().getSysDictionaryList());  //更新至REDIS
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysDictionary";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysDictionary sysDictionary = services.findForObject(id);
		model.addAttribute("sysDictionary", sysDictionary);
		model.addAttribute("action", "update");
		return "/framework/sysDictionary/sysDictionaryEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysDictionary sysDictionary, RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysDictionary.setUpdateUser(sessionUser.getId());
		services.update(sysDictionary);
		ApplicationHelper.getInstance().setSysDictionaryList(services.find());
		cache.setDictList(ApplicationHelper.getInstance().getSysDictionaryList());  //更新至REDIS
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysDictionary";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		ApplicationHelper.getInstance().setSysDictionaryList(services.find());
		cache.setDictList(ApplicationHelper.getInstance().getSysDictionaryList());  //更新至REDIS
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysDictionary";
	}
	
}
