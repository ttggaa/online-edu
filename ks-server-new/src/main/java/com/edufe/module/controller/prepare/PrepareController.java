package com.edufe.module.controller.prepare;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.beans.R;
import com.edufe.framework.common.JwtUtils;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.service.LoginServices;
import com.edufe.module.service.PrepareServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("prepare")
@Api("考试信息准备接口")
public class PrepareController extends BaseController{
	@Autowired
    private JwtUtils jwtUtils;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private PrepareServices prepareServices;
	@Autowired
	private LoginServices loginServices;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("getExamPrepareData")
	@ApiOperation("查询考试准备页面数据")
	public R getExamPrepareData(@RequestHeader("token") String token) {
		String uid = jwtUtils.getUserid(token);
		if(null != uid){
			
			ExamStu examStu = loginServices.findExamStu(uid);
			//考试开始，倒计时 （秒）
			String diffSecond = CalendarUtil.dataDiffByNow(examStu.getExam().getExamBegintime());
			List<ExamCourse> examCourseList = prepareServices.findExamCourse(examStu.getExamId(), examStu.getId());
			
			Map<String, Object> map = new HashMap<>();
			map.put("truename",examStu.getTruename());
	        map.put("idcard",examStu.getIdcard());
	        map.put("examname",examStu.getExam().getExamName());
			map.put("diffSecond", Long.parseLong(diffSecond) < 0 ? 0 : diffSecond);
			map.put("examIntroduce", examStu.getExam().getIntroduce());
			map.put("examCourseList", examCourseList);
			map.put("testFlag", examStu.getTestFlag());
			return R.ok(map);
		}else{
			return R.error("请求数据失败！");
		}
	}

}
