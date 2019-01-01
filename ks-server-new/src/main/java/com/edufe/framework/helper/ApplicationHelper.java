package com.edufe.framework.helper;

import java.util.List;
import java.util.Map;

import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.Type;


public class ApplicationHelper {
	
	private static ApplicationHelper helper = null;
	private Map<String,Type> quesTypeMap ;
	private List<Type> quesTypeList;
	private CacheBusiness business ;
	private ApplicationHelper(){
	}
	
	public static ApplicationHelper getInstance(){
		if(null == helper){
			helper = new ApplicationHelper();
		}
		return helper;
	}

	public Map<String, Type> getQuesTypeMap() {
		return quesTypeMap;
	}

	public void setQuesTypeMap(Map<String, Type> quesTypeMap) {
		this.quesTypeMap = quesTypeMap;
	}

	public List<Type> getQuesTypeList() {
		return quesTypeList;
	}

	public void setQuesTypeList(List<Type> quesTypeList) {
		this.quesTypeList = quesTypeList;
	}
	
	public void setBusiness(CacheBusiness business) {
		this.business = business;
	}

	public CacheBusiness getBusiness() {
		return this.business;
	}
}
