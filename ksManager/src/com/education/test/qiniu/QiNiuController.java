package com.education.test.qiniu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.education.framework.base.BaseController;

@Controller
@RequestMapping("test/qiniu")
public class QiNiuController extends BaseController{
	
	@RequestMapping(value = "testUpload")
	public String testUpload(Model model){
		//model.addAttribute("newMessageList", sysLogServices.findNewMessageList());
		return renderString("/test/qiniu/testUpload");
	}
	
}
