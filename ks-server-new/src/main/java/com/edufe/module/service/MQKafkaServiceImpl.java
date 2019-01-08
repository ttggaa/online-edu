package com.edufe.module.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.edufe.framework.common.JsonUtil;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.bean.ExamAccountBean;

@Service
public class MQKafkaServiceImpl{
	private final String prefix = "eks_";
	
	@Autowired
    KafkaTemplate<String,String> kafkaTemplate;

	public void decAccount(ExamAccountBean bean) {
		if(null == bean) ;
		try {
			kafkaTemplate.send(addPrefix("account_change"), JsonUtil.toJson(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ;
	}
	
	
//	/**
//	 * 保存支付请求参数日志
//	 */
//	public boolean saveAccessLog(WebServiceLogs obj){
//		if(null == obj) return false;
//		try {
//			kafkaTemplate.send(addPrefix("log"), JsonUtil.toJson(obj));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
	private String addPrefix(String key) {
		return prefix + key;
	}
//
//	public boolean sendUserExitMsg(String jgRegistrationId) {
//		if(null == jgRegistrationId) return false;
//		try {
//			kafkaTemplate.send(addPrefix("jguser_exit"), jgRegistrationId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
//	}


	public void savePaper(Integer stuId, Integer courseId, ExamCourse ec) {
		
	}

}
