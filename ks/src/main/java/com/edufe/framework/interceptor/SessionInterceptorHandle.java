package com.edufe.framework.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edufe.framework.helper.SessionHelper;

public class SessionInterceptorHandle extends HandlerInterceptorAdapter {
	
	private List<String> allowUrls;
	public SessionInterceptorHandle(){
		allowUrls = new ArrayList<String>();
		allowUrls.add("/login");
		allowUrls.add("/login/login");
		allowUrls.add("/common/..*");
	}
    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if(null != SessionHelper.getInstance().getExamStu()) return true;
    	String requestUrl = request.getRequestURI();
		if(!"".equals(request.getContextPath())){
			requestUrl = requestUrl.replaceFirst(request.getContextPath(), "");
		}
		for(String url : allowUrls) {
//			if(requestUrl.indexOf(url) == 0) {
//				return true;
//			}
			if (requestUrl != null
					&& requestUrl.matches(url)) {
				return true;
			}
		}
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/login";
		response.sendRedirect(basePath);
		return false;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
//        System.out.println("postHandle方法");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("afterCompletion方法");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterConcurrentHandlingStarted(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("afterConcurrentHandlingStarted方法");

    }

}