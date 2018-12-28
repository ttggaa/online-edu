package com.education.framework.baseModule.module.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.module.business.BusinessServices;
import com.education.framework.baseModule.module.sysLog.SysLogServices;
import com.education.framework.session.SessionHelper;

@Controller
@RequestMapping("console")
public class ConsoleController extends BaseController{

	@Autowired
	private ConsoleServices consoleServices;
	
	@Autowired
	private SysLogServices sysLogServices;
	@Autowired
	private BusinessServices businessServices;
	@RequestMapping(value = "")
	public String init(Model model){
		//model.addAttribute("newMessageList", sysLogServices.findNewMessageList());
		
		model.addAttribute("business", businessServices.findObject(SessionHelper.getInstance().getUser().getBusinessId()));
		return renderString("/framework/console/console");
	}
	
}
