package com.education.framework.session;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.constants.ConstantsSession;

public class SessionHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	private static SessionHelper sessionHelper = null;
	
	private HttpSession session;
	
	private SessionHelper(){
	}
	
	public synchronized static SessionHelper getInstance(){
		if(null == sessionHelper){
			sessionHelper = new SessionHelper();
		}
		return sessionHelper;
	}
	
	public SysUser getUser(){
		Object obj = getSession().getAttribute(ConstantsSession.LOGIN_USER);
		if(null != obj){
			return (SysUser)obj;
		}
		return null;
	}
	public void putUser(SysUser user){
		getSession().setAttribute(ConstantsSession.LOGIN_USER, user);
	}

	/**
	 * 是否具有系统管理员权限
	 * @return
	 */
	public boolean isAdminRole(){
		SysUser user = getUser();
		if(null == user) return false;
		if(null == user.getRoleList()) return false;
		for(SysRole role : user.getRoleList()){
			if("1".equals(role.getRoleCode())){
				return true;//具备权限
			}
		}
		return false;
	}
	
	/**
	 * 是否具有 功能权限
	 * @return
	 */
	public boolean isFunc(String funcCode){
		SysUser user = getUser();
		if(null == user) return false;
		if(null == user.getRoleList()) return false;
		if(null == funcCode) return false;
		String[] funcCodeArr = funcCode.split(",");
		for(String code : funcCodeArr){
			if(null != user.getFuncMap() && user.getFuncMap().containsKey(Integer.valueOf(code))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否具有 菜单权限
	 * @return
	 */
	public boolean isMenu(String requestMapping){
		SysUser user = getUser();
		if(null == user) return false;
		if(null == user.getMenuListStr()) return false;
		
		List<String> menuLinkList = user.getMenuListStr();
		for(String menuLink : menuLinkList){
			if(null != menuLink && menuLink.indexOf(requestMapping) > -1){
				return true;
			}
		}
		return false;
	}
	
	public HttpSession getSession() {
		HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		session = request.getSession(true);
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
}
