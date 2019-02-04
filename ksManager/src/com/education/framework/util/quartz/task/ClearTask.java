package com.education.framework.util.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.education.framework.listener.InitServices;

public class ClearTask {
	
	@Autowired
	private InitServices initServices; 
	
	public void task()
    {
		System.out.println("Quartz的任务调度！！！");
		//初使化商户对象至缓存
		initServices.initBusinessCache();
    }
	
}
