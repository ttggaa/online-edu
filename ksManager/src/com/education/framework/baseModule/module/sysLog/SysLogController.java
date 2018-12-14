package com.education.framework.baseModule.module.sysLog;

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
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.calendar.CalendarUtil;
import com.education.framework.baseModule.domain.SysLog;
import com.education.framework.baseModule.domain.SysUser;
@Controller
@RequestMapping("framework/sysLog")
public class SysLogController extends BaseController{

	@Autowired
	private SysLogServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysLog> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/framework/sysLog/sysLogList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		return "/framework/sysLog/sysLogEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysLog sysLog, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysLog.setCreateUser(sessionUser.getId());
		sysLog.setCreateTime(CalendarUtil.getCurrentDate());
		services.save(sysLog);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysLog";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysLog sysLog = services.findForObject(id);
		model.addAttribute("sysLogs", sysLog);
		model.addAttribute("action", "update");
		return "/framework/sysLog/sysLogEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysLog sysLog, RedirectAttributes redirectAttributes) {
		services.update(sysLog);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysLog";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysLog";
	}
	
}
