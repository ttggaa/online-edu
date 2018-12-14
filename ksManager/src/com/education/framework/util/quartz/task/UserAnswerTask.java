package com.education.framework.util.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;


public class UserAnswerTask {
	
	@Autowired
	//private AnswerRecordServices services;
	
	public void task()
    {
//		 System.out.println("Quartz的任务调度！！！");
//		 System.out.println("解析用户作答记录...");
		 
		 //1. 删除state=1 状态为已解析的作答记录
		 
		 //2. 分析待解析state=0 状态的作答记录
		 //services.analysisAnswer();
    }
	
}
