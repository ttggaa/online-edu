package com.education.framework.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.constants.ConstantsSession;
import com.education.framework.domain.SearchParams;

public class BaseController implements ConstantsSession{

	@Autowired
	protected ResourceBundleMessageSource resourceBundleMessageSource;
	
	protected SysUser getSessionUser(HttpServletRequest request){
		return (SysUser)request.getSession().getAttribute(LOGIN_USER);
	}
	
	protected String renderString(String url){
		return url + UI_SUFFIX;
	}
	
	protected String renderRedirectString(String url){
		return "redirect:/" + url + UI_SUFFIX;
	}
	
	protected String renderRedirectString(String url,SearchParams searchParams){
		return "redirect:/" + url + UI_SUFFIX + getParam(searchParams);
	}
	
	private String getParam(SearchParams searchParams) {
		StringBuffer sp = new StringBuffer();
		String lp = "?";
		for(Map.Entry<String, Object> entry : searchParams.getMap().entrySet()){    
		     if(null != entry.getValue() && !"".equals(entry.getValue())){
		    	 sp.append(lp).append("map['").append(entry.getKey()).append("']=").append(entry.getValue());
		    	 lp = "&";
		     }
		}   
		return sp.toString();
	}
}
