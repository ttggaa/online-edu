package com.education.framework.baseModule.module.sysMenu;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.calendar.CalendarUtil;

@Controller
@RequestMapping("framework/sysMenu")
public class SysMenuController extends BaseController{

	@Autowired
	private SysMenuServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysMenu> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/framework/sysMenu/sysMenuList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		model.addAttribute("menuList",services.find());
		return "/framework/sysMenu/sysMenuEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysMenu sysMenu, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysMenu.setCreateUser(sessionUser.getId());
		sysMenu.setCreateTime(CalendarUtil.getCurrentDate());
		services.save(sysMenu);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysMenu";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysMenu sysMenu = services.findForObject(id);
		model.addAttribute("sysMenu", sysMenu);
		model.addAttribute("action", "update");
		model.addAttribute("menuList",services.find());
		return "/framework/sysMenu/sysMenuEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysMenu sysMenu, RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysMenu.setUpdateUser(sessionUser.getId());
		services.update(sysMenu);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysMenu";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysMenu";
	}
	
}
