package com.edufe.model.consumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.edufe.framework.common.JsonUtil;
import com.edufe.model.entity.ExamAccountBean;
import com.edufe.model.service.ExamBusinessAccountServices;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 考试客户账户变动 消费者处理程序
 * @author yangchao 
 * 2018/12/26 14:36
 */
@Component
public class ExamBusinessAccountConsumer {
    
	@Autowired
	private ExamBusinessAccountServices service ;
	
	@KafkaListener(topics = {"eks_account_change"})
    public void process(String content) {
        ExamAccountBean bean = null;
		try {
			bean = (ExamAccountBean)JsonUtil.toObject(content, ExamAccountBean.class);
			service.processMsg(bean);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//        logService.insert(log);
    }


}
