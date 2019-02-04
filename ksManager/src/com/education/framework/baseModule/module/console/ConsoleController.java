package com.education.framework.baseModule.module.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.beans.ApiResult;
import com.education.domain.extend.Examing;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.bean.SysInfoBean;
import com.education.framework.baseModule.module.business.BusinessServices;
import com.education.framework.baseModule.module.sysLog.SysLogServices;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.cache.CacheManager;
import com.education.module.exam.ExamServices;

@Controller
@RequestMapping("console")
public class ConsoleController extends BaseController{

	@Autowired
	private ConsoleServices consoleServices;
	
	@Autowired
	private SysLogServices sysLogServices;
	@Autowired
	private BusinessServices businessServices;
	@Autowired
	private ExamServices examServices;
	@Autowired
	private CacheManager cache;
	
	@RequestMapping(value = "")
	public String init(Model model){
		//model.addAttribute("newMessageList", sysLogServices.findNewMessageList());
		List<Examing> examingList = examServices.findExamingList();//查未结束的考试
		
		//同时在线人数
		String wsKey = "ws_" + SessionHelper.getInstance().getUser().getBusinessId();
		int uCount = cache.getSessionUserCount(wsKey);
		
		model.addAttribute("uCount", uCount);
		model.addAttribute("examingList", examingList);
		model.addAttribute("business", businessServices.findObject(SessionHelper.getInstance().getUser().getBusinessId()));
		return renderString("/framework/console/console");
	}
	
	@RequestMapping(value = "saveSysInfo")
	@ResponseBody
	public ApiResult saveSysInfo(Model model, SysInfoBean sysinfo){
		ApiResult result = new ApiResult();
		businessServices.updateSysInfo(sysinfo);
		cache.setBusiness(businessServices.findObjectCache());
		return result;
	}
	
	@RequestMapping(value = "saveBackground")
	@ResponseBody
	public ApiResult saveBackground(Model model, SysInfoBean sysinfo){
		ApiResult result = new ApiResult();
		businessServices.updateBackground(sysinfo.getBackground());
		cache.setBusiness(businessServices.findObjectCache());
		return result;
	}
}
