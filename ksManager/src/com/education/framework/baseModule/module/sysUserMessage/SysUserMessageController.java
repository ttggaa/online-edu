package com.education.framework.baseModule.module.sysUserMessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysUserMessage;

@Controller
@RequestMapping("sysUserMessage")
public class SysUserMessageController extends BaseController{

	@Autowired
	private SysUserMessageServices services;
	
	/////////
	@RequestMapping(value = "findMsg")
	@ResponseBody
	public List<SysUserMessage> findMsg(){
		List<SysUserMessage> list = services.findForSessionUser();
		return list;
	}
	
	@RequestMapping(value = "toUrl/{id}")
	public String toUrl(@PathVariable("id") Integer id,@RequestParam(value="url",required=false) String url){
		services.updateReadFlag(id); 
		return "redirect:/"+url;
	}
}
