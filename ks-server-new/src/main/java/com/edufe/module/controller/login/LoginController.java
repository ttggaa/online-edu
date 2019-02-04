package com.edufe.module.controller.login;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.beans.R;
import com.edufe.framework.common.JwtUtils;
import com.edufe.framework.common.Util;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.validator.ValidatorUtils;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.entity.bean.ExBean;
import com.edufe.module.entity.bean.ExamStuBean;
import com.edufe.module.entity.bean.LoginConfBean;
import com.edufe.module.entity.queueBean.ExamLoginMsgBean;
import com.edufe.module.service.LoginServices;
import com.edufe.module.service.MQKafkaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("login")
@Api("考生登录接口")
public class LoginController extends BaseController{

	@Autowired
    private JwtUtils jwtUtils;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private LoginServices loginServices;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CacheUtil cache;
	@Autowired
	private MQKafkaServiceImpl mq;
//	@Autowired
//	private FaceMatchConfigByBaidu faceMatchConfig;
	
	@PostMapping("reqLoginInfo")
	@ApiOperation("获取登录信息")
	public R login(@RequestBody ExBean bean) {
		//表单校验
        ValidatorUtils.validateEntity(bean);
        LoginConfBean lcb = loginServices.findLoginConf(bean.getReqUrl());
        
		if(null != lcb){
	        Map<String, Object> map = new HashMap<>();
	        map.put("lcb", lcb);
			return R.ok(map);
		}else{
			return R.error("连接服务器异常，请稍候刷新本页面重试！");
		}
	}
	
	@PostMapping("login")
	@ApiOperation("登录")
	public R login(@RequestBody ExamStuBean examStuParam) {
		//表单校验
        ValidatorUtils.validateEntity(examStuParam);
		if(loginServices.getAccountOverdraftFlag(examStuParam.getReqUrl())) {
			return R.error("考生登录失败，账户余额不足！"); 
		}
		ExamStu examStu = loginServices.findExamStu(examStuParam.getIdcard() , examStuParam.getTruename(), examStuParam.getReqUrl());
		if(null != examStu){
			
			//登录成功,扣费,test_flag=1 && 会员版本未到期 除外
			CacheBusiness cb = loginServices.getBusiness(examStuParam.getReqUrl());
			if(!"1".contentEquals(examStu.getTestFlag()) && !"1".contentEquals(examStu.getCostFlag()) && !"1".equals(cb.getAuthFlag())){
				mq.loginDecAccount(new ExamLoginMsgBean(examStu.getId(), examStu.getExam().getBusinessId(), Util.getClientIP(request),CalendarUtil.getCurrentDateAll(),Util.getUrlPrefix(examStuParam.getReqUrl())));
				
				//验证当前时间是否在考试时间段内，判断年月日，不含时分秒，不在时间断内，禁止登录考试平台。
				
			}
			
			//生成token
	        String token = jwtUtils.generateToken(examStu.getId());
	        Map<String, Object> map = new HashMap<>();
	        map.put("token", token);
	        map.put("expire", jwtUtils.getExpire());
			return R.ok(map);
		}else{
			//登录失败
			return R.error("登录失败，准考证号或姓名输入有误！");
		}
	}
}

