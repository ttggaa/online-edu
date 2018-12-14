package com.education.framework.application;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.education.domain.Type;
import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.baseModule.domain.SysMenuFunc;
import com.edufe.module.entity.CacheBusiness;

public class ApplicationHelper implements IApplicationData {

	private static ApplicationHelper helper;
	private List<SysDictionary> dictionaryList;
	private Map<Integer,List<SysMenuFunc>> funcMap;
	private Map<String,Type> quesTypeMap ;
	private List<com.edufe.module.entity.Type> quesTypeList;
	private ApplicationHelper(){}
	private CacheBusiness business ;
	public static IApplicationData getInstance(){
		if (helper == null){
			synchronized(ApplicationHelper.class){
				if(helper == null){
					helper = new ApplicationHelper();
				}
			}
		}
		return helper;
	}
	
	private ApplicationContext springContext = null;

	public ApplicationContext getSpringContext() {
		return springContext;
	}

	public void setSpringContext(ApplicationContext sc) {
		this.springContext = sc;
	}
	
	public Object getBean(String config){
		Object result;
		if (springContext == null ){
			result = null;
		}else{
			result = springContext.getBean(config);
		}
		return result;
	}

	public List<SysDictionary> getSysDictionaryList() {
		return dictionaryList;
	}

	public void setSysDictionaryList(List<SysDictionary> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}

	public Map<Integer, List<SysMenuFunc>> getFuncMap() {
		return funcMap;
	}

	public void setFuncMap(Map<Integer, List<SysMenuFunc>> funcMap) {
		this.funcMap = funcMap;
	}

	@Override
	public List<SysMenuFunc> getFuncMap(Integer menuId) {
		if(null == funcMap) return null;
		if(funcMap.containsKey(menuId)){
			return funcMap.get(menuId);
		}
		return null;
	}
	
	public Map<String, Type> getQuesTypeMap() {
		return quesTypeMap;
	}

	public void setQuesTypeMap(Map<String, Type> quesTypeMap) {
		this.quesTypeMap = quesTypeMap;
	}
	public List<com.edufe.module.entity.Type> getQuesTypeList() {
		return quesTypeList;
	}

	public void setQuesTypeList(List<com.edufe.module.entity.Type> quesTypeList) {
		this.quesTypeList = quesTypeList;
	}

	@Override
	public void setBusiness(CacheBusiness business) {
		this.business = business;
	}

	@Override
	public CacheBusiness getBusiness() {
		return this.business;
	}
}