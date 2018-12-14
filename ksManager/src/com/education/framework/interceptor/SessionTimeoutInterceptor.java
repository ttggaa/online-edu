package com.education.framework.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.education.framework.constants.ConstantsSession;
import com.education.framework.session.SessionHelper;
@Repository
public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {

	private List<String> allowUrls;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();
		if(!"".equals(request.getContextPath())){
			requestUrl = requestUrl.replaceFirst(request.getContextPath(), "");
		}
		for(String url : allowUrls) {
			if(requestUrl.indexOf(url) == 0) {
				return true;
			}
		}
		
		Object session = WebUtils.getSessionAttribute(request, ConstantsSession.LOGIN_USER);
		Object sessionQt = WebUtils.getSessionAttribute(request, ConstantsSession.LOGIN_STU_TEA);
		if(session != null) {
			if(null != SessionHelper.getInstance().getUser()){
				request.setAttribute("requestUrl", requestUrl);
				
				String menuRootId = request.getParameter("menuRootId");
				if(null != menuRootId && !"".equals(menuRootId)){
					String menuId = request.getParameter("menuId");
					String subMenuId = request.getParameter("subMenuId");
					SessionHelper.getInstance().getSession().setAttribute("menuRootId", menuRootId);
					SessionHelper.getInstance().getSession().setAttribute("menuId", menuId);
					SessionHelper.getInstance().getSession().setAttribute("subMenuId", subMenuId);
				}
				return true;
			}
		}else if(null != sessionQt){
			return true;
		}
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		response.sendRedirect(basePath);
		return false;
//			throw new SessionTimeoutException();
		//return super.preHandle(request, response, handler);
	}
	
	public List<String> getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}
	 
	 
}
