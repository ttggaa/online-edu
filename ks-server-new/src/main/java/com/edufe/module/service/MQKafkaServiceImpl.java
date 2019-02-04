package com.edufe.module.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.edufe.framework.common.JsonUtil;
import com.edufe.module.entity.bean.ExamPracBean;
import com.edufe.module.entity.bean.ExaminationType;
import com.edufe.module.entity.queueBean.ExamLoginMsgBean;
import com.edufe.module.entity.queueBean.PaperSaveBean;

@Service
public class MQKafkaServiceImpl{
	private final String prefix = "eks_";
	
	@Autowired
    KafkaTemplate<String,String> kafkaTemplate;

	public void loginDecAccount(ExamLoginMsgBean bean) {
		if(null == bean) ;
		try {
			kafkaTemplate.send(addPrefix("exam_login"), JsonUtil.toJson(bean));
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


//	public void savePaper(ExamCourse ec) {
//		if(null == ec) return;
//		try {
//			kafkaTemplate.send(addPrefix("exam_savepaper"), JsonUtil.toJson(ec));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ;
//	}


	public void savePaper(Integer stuId, Integer courseId, List<ExaminationType> examinationTypeList, Map<String, ExamPracBean> pracMap, Integer businessId) {
		PaperSaveBean bean = new PaperSaveBean();
		bean.setStuId(stuId);
		bean.setCourseId(courseId);
		bean.setPaperJson(JsonUtil.toJson(examinationTypeList));
		bean.setPracConf(JsonUtil.toJson(pracMap));
		bean.setBusinessId(businessId);
		try {
			kafkaTemplate.send(addPrefix("exam_savepaper"), JsonUtil.toJson(bean));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
