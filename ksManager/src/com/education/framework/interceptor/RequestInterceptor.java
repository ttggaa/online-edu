package com.education.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.education.framework.application.ApplicationHelper;
import com.education.framework.util.CommonTools;
import com.education.framework.util.cache.CacheManager;
import com.edufe.module.entity.CacheBusiness;
@Repository
public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private CacheManager cache;
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//String serverName = request.getServerName();
		
		if(!"localhost".equalsIgnoreCase(request.getServerName())){
			//获取域名对应的商户
			CacheBusiness business = cache.getBusiness(CommonTools.getUrlPrefix(request.getServerName().toString()));
			if(null == business) {
				return false;
			}else{
				request.setAttribute("reqBusiness", business);
				ApplicationHelper.getInstance().setBusiness(business);
			}
		}else{
			//本地调试
			//获取域名对应的商户
			CacheBusiness business = cache.getBusiness("system");
			request.setAttribute("reqBusiness", business);
			ApplicationHelper.getInstance().setBusiness(business);
		}
//		Map<String, Business> bMap = ApplicationHelper.getInstance().getBusinessMap();
//		if(bMap.containsKey(serverName)){
//			ApplicationHelper.getInstance().setBusiness(bMap.get(serverName));
//		}else{
//			if(bMap.containsKey("www.kjkfonline.cn")){
//				ApplicationHelper.getInstance().setBusiness(bMap.get("www.kjkfonline.cn"));
//			}else{
//				for (Business value : bMap.values()) {  
//					ApplicationHelper.getInstance().setBusiness(value); 
//				}
//			}
//		}
//		request.setAttribute("reqInterceptorBusiness", ApplicationHelper.getInstance().getBusiness());
		return true;
	}
	 
}
