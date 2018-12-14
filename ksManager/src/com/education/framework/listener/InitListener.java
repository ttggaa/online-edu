package com.education.framework.listener;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.education.framework.application.ApplicationHelper;
import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.baseModule.domain.SysMenuFunc;
import com.education.framework.baseModule.module.sysDictionary.SysDictionaryServices;
import com.education.framework.util.cache.CacheManager;
import com.education.module.type.TypeServices;

public class InitListener {
	
	@Autowired
	private SysDictionaryServices dictServices;
	@Autowired
	private CacheManager cache;
	@Autowired
	private InitServices initServices; 
	@Autowired
	private TypeServices typeServices;
	@PostConstruct
	public void init() {
		List<SysDictionary> dictList = dictServices.find();
		ApplicationHelper.getInstance().setSysDictionaryList(dictList);
		cache.setDictList(dictList);
		
		Map<Integer,List<SysMenuFunc>> funcMap = initServices.findFuncMap();
		ApplicationHelper.getInstance().setFuncMap(funcMap);
		
		ApplicationHelper.getInstance().setQuesTypeMap(typeServices.findByMap());
		ApplicationHelper.getInstance().setQuesTypeList(typeServices.findForEdu());
		//初使化商户对象至缓存
		initServices.initBusinessCache();
	}

}