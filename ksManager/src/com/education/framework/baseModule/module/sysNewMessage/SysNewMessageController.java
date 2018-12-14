package com.education.framework.baseModule.module.sysNewMessage;

/**
 * 消息动态
 */
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysLog;
import com.education.framework.baseModule.module.sysLog.SysLogServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
@Controller
@RequestMapping("framework/sysNewMessage")
public class SysNewMessageController extends BaseController{

	@Autowired
	private SysLogServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysLog> list = services.findNewMessageList(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/framework/sysNewMessage/sysNewMessageList";
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model) {
		SysLog sysLog = services.findForObject(id);
		model.addAttribute("sysLogs", sysLog);
		return "/framework/sysLog/sysLogEdit";
	}
	
}
