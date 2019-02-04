package com.education.framework.baseModule.module.sysUser;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.SysOrg;
import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.baseModule.module.sysLog.SysLogServices;
import com.education.framework.baseModule.module.sysMenu.SysMenuServices;
import com.education.framework.baseModule.module.sysOrg.SysOrgServices;
import com.education.framework.baseModule.module.sysRole.SysRoleServices;
import com.education.framework.constants.LogConstants;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.Md5Util;
import com.education.framework.util.calendar.CalendarUtil;
import com.education.framework.util.security.RSACodeFactory;
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController{

	@Autowired
	private SysUserServices services;
	@Autowired
	private SysMenuServices sysMenuServices;
	@Autowired
	private SysLogServices sysLogServices;
	@Autowired
	private SysRoleServices sysRoleServices;
	@Autowired
	private SysOrgServices orgServices;
	
	@RequestMapping(value = "initLogin")
	public String initLogin(Model model,HttpServletRequest request,HttpServletResponse response){
		String tCookieCode = "";
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组 
		
		if(null != cookies){
			for(Cookie cookie2 : cookies){
				if("t_cookie_code".equals(cookie2.getName())){
					tCookieCode = cookie2.getValue();
					break;
				}
			}
		}
		if(null != tCookieCode && !"".equals(tCookieCode)){
			try {
				String resCode = RSACodeFactory.getInstance().decryptDef(tCookieCode);
				String[] resCodeArr = resCode.split("\\^");
				model.addAttribute("userName", resCodeArr[0]);
				model.addAttribute("passwd", resCodeArr[1]);
				model.addAttribute("rememberFlag", "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			model.addAttribute("rememberFlag", "");
		}
		return "/framework/login/login";
	}
	
	@RequestMapping(value = "logout")
	public String logout(Model model,HttpServletRequest request){
		SessionHelper.getInstance().putUser(null);
		String tCookieCode = "";
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组 
		//test
		if(null != cookies){
			for(Cookie cookie2 : cookies){
				if("t_cookie_code".equals(cookie2.getName())){
					tCookieCode = cookie2.getValue();
					break;
				}
			}
		}
		if(null != tCookieCode && !"".equals(tCookieCode)){
			try {
				String resCode = RSACodeFactory.getInstance().decryptDef(tCookieCode);
				String[] resCodeArr = resCode.split("\\^");
				model.addAttribute("userName", resCodeArr[0]);
				model.addAttribute("passwd", resCodeArr[1]);
				model.addAttribute("rememberFlag", "1");
				ApplicationHelper.getInstance().setBusiness(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			model.addAttribute("rememberFlag", "");
		}
		return "/framework/login/login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(@ModelAttribute("sysUser") SysUser sysUser,ModelMap model,HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value="remember",required=false) String remember){
		SessionHelper.getInstance().putUser(null);
		model.remove(MESSAGE);
		if(null == sysUser){
			model.put(MESSAGE, "登录失败，账号或密码不正确！");
		}else if(null == sysUser.getLoginname() || "".equals(sysUser.getLoginname())){
			model.put(MESSAGE, "登录账号不能为空！");
		}else if(null == sysUser.getPasswd() || "".equals(sysUser.getPasswd())){
			model.put(MESSAGE, "登录密码不能为空！");
			model.addAttribute("userName", sysUser.getLoginname());
			model.addAttribute("passwd", "");
		}
		
		if(model.containsKey(MESSAGE)){
			return "/framework/login/login";
		}
		SysUser loginUser = services.findUserByLoginName(sysUser.getLoginname()) ;
		if(null != loginUser && Md5Util.MD5Encode(sysUser.getPasswd()).equals(loginUser.getPasswd())){
			if(loginUser.getOrgId() > 0){
				//读取父部门ID
				SysOrg org = orgServices.findForObject(loginUser.getOrgId());
				int orgPid = org.getPid();
				loginUser.setOrgPid(orgPid);
			}
			//读取账户角色信息
			List<SysRole> roleList = sysRoleServices.findForUserId(loginUser.getId());
			loginUser.setRoleList(roleList);
			//读取用户权限菜单
			List<SysMenu> menuList = sysMenuServices.find(loginUser.getRoleList());
			loginUser.setMenuList(menuList);
			loginUser.setMenuListStr(sysMenuServices.findUserMenuLink(loginUser.getRoleList()));
			
			Map<Integer,Integer> funcMap = sysRoleServices.findFuncMap(loginUser.getRoleList());
			loginUser.setFuncMap(funcMap);
			
			SessionHelper.getInstance().putUser(loginUser);
			sysLogServices.insertLog(LogConstants.LOGIN_TYPE, "后台登录成功，" + loginUser.getLoginname(),request);
			
			if(null != remember && "1".equals(remember)){
				String source = loginUser.getLoginname() + "^" + sysUser.getPasswd();
				
				try {
					String rsaCode = RSACodeFactory.getInstance().encryptDef(source);
					// new一个Cookie对象,键值对为参数  
					Cookie cookie = new Cookie("t_cookie_code", rsaCode);
					// 设置Cookie最大生存时间,以秒为单位,负数的话为浏览器进程,关闭浏览器Cookie消失  
					cookie.setMaxAge(60*60*24*7);  // 7天  
					cookie.setPath(request.getContextPath() + "/");
					// 将Cookie添加到Response中,使之生效  
					response.addCookie(cookie); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				Cookie cookie = new Cookie("t_cookie_code", "");
				// 设置Cookie最大生存时间,以秒为单位,负数的话为浏览器进程,关闭浏览器Cookie消失  
				cookie.setMaxAge(0);  // 7天  
				cookie.setPath(request.getContextPath() + "/");
				// 将Cookie添加到Response中,使之生效  
				response.addCookie(cookie); 
			}
			
			return "redirect:/console";
		}else{
			model.put(MESSAGE, "账号或密码有误！");
			model.addAttribute("userName", sysUser.getLoginname());
			model.addAttribute("passwd", "");
		}
		return "/framework/login/login";
	}
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysUser> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("listRole",sysRoleServices.find());
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("orgList",orgServices.find());
		return "/framework/user/userList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		SysUser sysUser = new SysUser();
		sysUser.setState("1");
		model.addAttribute("user", sysUser);
//		List<SysRole> listRole = sysRoleServices.find();
		model.addAttribute("action","create");
//		model.addAttribute("listRole",listRole);
		model.addAttribute("orgList",orgServices.findForRecursive());
		return "/framework/user/userEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysUser sysUser,String[] roleIds, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysUser.setCreateUser(sessionUser.getId());
		sysUser.setUpdateUser(sessionUser.getId());
		sysUser.setCreateTime(CalendarUtil.getCurrentDate());
		if(!services.findIsExist(sysUser.getLoginname())){
			int uid = services.save(sysUser);
			services.updateUserRole(uid, roleIds);
			redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
			return "redirect:/sysUser";
		}else{
			model.addAttribute("action","create");
			model.addAttribute("user", sysUser);
			model.addAttribute("orgList",orgServices.findForRecursive());
			model.addAttribute(MESSAGE, "创建失败，账号已存在，请更换其它账号！");
			model.addAttribute(MESSAGE_STATE,"alert-fail");
			return "/framework/user/userEdit";
		}
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysUser sysUser = services.findForObject(id);
		List<SysRole> listRole = sysRoleServices.findByUser(sysUser.getId());
		model.addAttribute("user", sysUser);
		model.addAttribute("action", "update");
		model.addAttribute("listRole",listRole);
		model.addAttribute("orgList",orgServices.findForRecursive());
		return "/framework/user/userEdit";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysUser sysUser,String[] roleIds, Model model, RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		if(!services.findIsExist(sysUser.getId(), sysUser.getLoginname())){
			sysUser.setUpdateUser(sessionUser.getId());
			services.update(sysUser);
			services.updateUserRole(sysUser.getId(), roleIds);
			redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
			return "redirect:/sysUser";
		}else{
			model.addAttribute("action","update");
			model.addAttribute("user", sysUser);
			model.addAttribute("orgList",orgServices.findForRecursive());
			model.addAttribute(MESSAGE,"修改失败，账号已存在，请更换其它账号！");
			model.addAttribute(MESSAGE_STATE,"alert-fail");
			return "/framework/user/userEdit";
		}
	}
	
	@RequestMapping(value = "editUserPasswd", method = RequestMethod.GET)
	public String editUserPasswd(Model model) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		SysUser sysUser = services.findForObject(sessionUser.getId());
		List<SysRole> listRole = sysRoleServices.findByUser(sysUser.getId());
		model.addAttribute("user", sysUser);
		model.addAttribute("action", "update");
		model.addAttribute("listRole",listRole);
		return "/framework/user/editUserPasswd";
	}

	@RequestMapping(value = "saveUserPasswd", method = RequestMethod.POST)
	public String saveUserPasswd(SysUser sysUser, RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		if(services.checkOldPasswd(sessionUser.getId(), sysUser.getOldpasswd())){
			services.updatePasswd(sessionUser.getId(), sysUser.getPasswd());
			redirectAttributes.addFlashAttribute("MESSAGE", "密码修改成功!");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		}else{
			redirectAttributes.addFlashAttribute("MESSAGE", "原密码有误，请重新输入!");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-error");
		}
		
		return "redirect:/sysUser/editUserPasswd";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		if(id.intValue() == SessionHelper.getInstance().getUser().getId().intValue()){
			//与登录用户相同，不允许删除
			redirectAttributes.addFlashAttribute("MESSAGE", "删除失败，禁止删除当前登录用户账号!");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-error");
		}else{
			services.delete(id);
			redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		}
		return "redirect:/sysUser";
	}
	
	@RequestMapping(value = "resetPwd/{id}")
	public String resetPwd(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.resetPwd(id);
		redirectAttributes.addFlashAttribute("MESSAGE", "密码重置成功,密码：123456");
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/sysUser";
	}
	
}
