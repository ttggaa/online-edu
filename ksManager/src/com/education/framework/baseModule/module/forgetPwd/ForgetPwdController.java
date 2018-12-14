package com.education.framework.baseModule.module.forgetPwd;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.baseModule.module.mail.MailServices;
import com.education.framework.baseModule.module.sysUser.SysUserServices;
import com.education.framework.util.GUID;
import com.education.framework.util.cache.redis.JedisClient;
@Controller
@RequestMapping("forgetPwd")
public class ForgetPwdController extends BaseController{

	@Autowired
	private ForgetPwdServices services;
	@Autowired
	private SysUserServices userServices;
	@Autowired
	private JedisClient jedis;
	@Autowired
	private MailServices mailServices;
	/**
	 * 进入找回密码页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "")
	public String init(Model model,HttpServletRequest request,HttpServletResponse response){
		
		return "/framework/forgetPwd/forgetPwd";
	}
	
	/**
	 * 找回密码
	 * @param model
	 * @param loginname 账号
	 * @param randCode 验证码
	 * @return
	 */
	@RequestMapping(value = "submitForgetPwd")
	public String submitForgetPwd(Model model,
			@RequestParam(value="loginname",required=true) String loginname,
			@RequestParam(value="randCode",required=true) String randCode, HttpSession session,HttpServletRequest request){
		String strCode = (String)session.getAttribute("strCode");
		if(null == strCode || !randCode.equals(strCode)){
			model.addAttribute(MESSAGE, "验证码录入有误，请刷新后重试！");
			return "/framework/forgetPwd/forgetPwd";
		}
		//判断用户名是否存在
		SysUser user = userServices.findUserByLoginName(loginname);
		if(null == user){
			model.addAttribute(MESSAGE, "账户名不存在！");
			return "/framework/forgetPwd/forgetPwd";
		}
		
		if(null == user.getEmail() || "".equals(user.getEmail())){
			model.addAttribute(MESSAGE, "账户邮箱地址有误，无法找回密码！");
			return "/framework/forgetPwd/forgetPwd";
		}
		//生成用户重置密码口令
		String authCode = GUID.getGUID();
		String redisKey = jedis.addPrefix("RP_" + loginname);
		jedis.set(redisKey, authCode, 86400);
		
		String url = buildRestPwdReqUrl(authCode,loginname,request);
		//发送邮件
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");//设置日期格式
		   
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("尊敬的用户：");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(loginname).append(",你好!");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你于").append(df.format(new Date())).append("提交了找回密码请求，请点击");
        sb.append("<a href=\""+url+"\">这里</a>");
        sb.append(",完成重置密码操作！");
        sb.append("<br>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您误收到此电子邮件，则可能是其他用户在尝试帐号设置时的误操作，如果您并未发起该请求，则无需再进行任何操作，并可以放心地忽略此电子邮件。");
        sb.append("<br>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;若您担心帐号安全，建议您立即登录平台进行密码修改。");
        sb.append("<br>");
        sb.append("<br>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;东北财经大学培训与继续教育学院");
        sb.append("</html>");
		try {
			mailServices.sendMail(user.getEmail(), "培训系统-账户密码重置", sb.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "/framework/forgetPwd/forgetPwdResult";
	}
	
	/**
	 * 找回密码
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "userResetPwd")
	public String userResetPwd(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="loginname",required=true) String loginname,
			@RequestParam(value="authCode",required=true) String authCode){
		String redisKey = jedis.addPrefix("RP_" + loginname);
		if(jedis.exists(redisKey)){
			String authCode1 = jedis.get(redisKey);
			if(authCode.equals(authCode1)){
				model.addAttribute("loginname", loginname);
				model.addAttribute("authCode", authCode);
			}else{
				model.addAttribute(MESSAGE, "您访问的连接网址已失效！");
			}
		}else{
			model.addAttribute(MESSAGE, "您访问的连接网址已失效！");
		}
		return "/framework/forgetPwd/userResetPwd";
	}
	
	@RequestMapping(value = "userResetPwdSave")
	public String userResetPwdSave(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="loginname",required=true) String loginname,
			@RequestParam(value="passwd",required=true) String passwd,
			@RequestParam(value="authCode",required=true) String authCode){
		String redisKey = jedis.addPrefix("RP_" + loginname);
		if(jedis.exists(redisKey)){
			String authCode1 = jedis.get(redisKey);
			if(authCode.equals(authCode1)){
				userServices.updatePwd(loginname, passwd);
				jedis.del(redisKey);
				return "redirect:/forgetPwd/userResetPwdOver";
			}else{
				model.addAttribute(MESSAGE, "您访问的连接网址已失效！");
			}
		}else{
			model.addAttribute(MESSAGE, "您访问的连接网址已失效！");
		}
		return "/framework/forgetPwd/userResetPwd";
	}
	
	/**
	 * 密码重置完成
	 * @param model
	 * @param loginname
	 * @return
	 */
	@RequestMapping(value = "userResetPwdOver")
	public String userResetPwdSave(Model model,HttpServletRequest request,HttpServletResponse response){
		
		return "/framework/forgetPwd/userResetPwdOver";
	}
	
	private String buildRestPwdReqUrl(String authCode,String loginname,HttpServletRequest request){
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		
		return basePath+"forgetPwd/userResetPwd?authCode="+authCode+"&loginname="+loginname;
	}
	
}
