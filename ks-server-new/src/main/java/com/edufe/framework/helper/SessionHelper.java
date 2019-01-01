package com.edufe.framework.helper;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edufe.module.entity.ExamStu;

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
	
	public ExamStu getExamStu(){
		Object obj = getSession().getAttribute("ExamStu");
		if(null != obj){
			return (ExamStu)obj;
		}
		return null;
	}
	public void putExamStu(ExamStu ExamStu){
		getSession().setAttribute("ExamStu", ExamStu);
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
