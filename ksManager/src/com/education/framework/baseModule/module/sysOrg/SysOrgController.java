package com.education.framework.baseModule.module.sysOrg;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.SysOrg;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.baseModule.module.sysUser.SysUserServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.calendar.CalendarUtil;

@Controller
@RequestMapping("framework/sysOrg")
public class SysOrgController extends BaseController{

	@Autowired
	private SysOrgServices services;
	@Autowired
	private SysUserServices userServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysOrg> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("parentOrgList",services.findParentOrgList(-1));
		model.addAttribute("searchParams", searchParams);
		return "/framework/sysOrg/sysOrgList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		model.addAttribute("userList",userServices.find());
		model.addAttribute("parentOrgList",services.findParentOrgList(-1));
		return "/framework/sysOrg/sysOrgEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysOrg sysOrg, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysOrg.setCreateUser(sessionUser.getId());
		sysOrg.setCreateTime(CalendarUtil.getCurrentDate());
		services.save(sysOrg);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysOrg";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysOrg sysOrg = services.findForObject(id);
		model.addAttribute("sysOrg", sysOrg);
		model.addAttribute("action", "update");
		model.addAttribute("userList",userServices.find());
		model.addAttribute("parentOrgList",services.findParentOrgList(id));
		return "/framework/sysOrg/sysOrgEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysOrg sysOrg, RedirectAttributes redirectAttributes) {
		services.update(sysOrg);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysOrg";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysOrg";
	}
	
}
