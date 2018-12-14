package com.edufe.module.controller.login;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.edufe.framework.base.BaseController;
import com.edufe.framework.common.Util;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.helper.ApplicationHelper;
import com.edufe.framework.helper.SessionHelper;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.service.LoginServices;
@Controller
@RequestMapping("login")
public class LoginController extends BaseController{

	@Autowired
	HttpServletRequest request;
	@Autowired
	private LoginServices loginServices;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CacheUtil cache;
	
	@RequestMapping("")
	public String index(HttpServletRequest request, Model model) {
		//获取域名对应的商户
		CacheBusiness business = cache.getBusiness(Util.getUrlPrefix(request.getServerName().toString()));
		if(null != business) {
			request.setAttribute("reqBusiness", business);
			ApplicationHelper.getInstance().setBusiness(business);
		}
		return "login/login";
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, Model model, ExamStu examStuParam) {
		//获取域名对应的商户
		CacheBusiness business = cache.getBusiness(Util.getUrlPrefix(request.getServerName().toString()));
		if(null != business) {
			request.setAttribute("reqBusiness", business);
			ApplicationHelper.getInstance().setBusiness(business);
		}
		
		ExamStu examStu = loginServices.findExamStu(examStuParam.getIdcard() , examStuParam.getTruename());
		if(null != examStu){
			//登录成功
			SessionHelper.getInstance().putExamStu(examStu);
			return "redirect:/prepare";
		}else{
			//登录失败
			model.addAttribute("MESSAGE", "登录失败，请仔细检查录入的考生信息！");
			model.addAttribute("examStu", examStuParam);
			return "login/login";
		}
	}
	
	@RequestMapping("logout")
	public String logout() {
		SessionHelper.getInstance().putExamStu(null);
		return "redirect:/login";
	}
}
