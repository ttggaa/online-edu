package com.education.framework.application;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.education.domain.Type;
import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.baseModule.domain.SysMenuFunc;
import com.edufe.module.entity.CacheBusiness;

public interface IApplicationData {
	public ApplicationContext getSpringContext();
	public void setSpringContext(ApplicationContext sc);
	public Object getBean(String config);
	
	public List<SysDictionary> getSysDictionaryList();
	public void setSysDictionaryList(List<SysDictionary> dictList);
	
	public Map<Integer, List<SysMenuFunc>> getFuncMap();
	public void setFuncMap(Map<Integer, List<SysMenuFunc>> funcMap);
	public List<SysMenuFunc> getFuncMap(Integer menuId);
	public Map<String, Type> getQuesTypeMap();
	public void setQuesTypeMap(Map<String, Type> quesTypeMap);
	public List<com.edufe.module.entity.Type> getQuesTypeList();

	public void setQuesTypeList(List<com.edufe.module.entity.Type> quesTypeList);
	public void setBusiness(CacheBusiness business);
	public CacheBusiness getBusiness();
}