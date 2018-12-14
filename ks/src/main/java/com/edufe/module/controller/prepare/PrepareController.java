package com.edufe.module.controller.prepare;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.common.GUID;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.helper.SessionHelper;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.service.PrepareServices;

@Controller
@RequestMapping("prepare")
public class PrepareController extends BaseController{

	@Autowired
	HttpServletRequest request;
	@Autowired
	private PrepareServices prepareServices;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("")
	public String init(Model model) {
		ExamStu examStu = SessionHelper.getInstance().getExamStu();
		//考试开始，倒计时 （秒）
		String diffSecond = CalendarUtil.dataDiffByNow(examStu.getExam().getExamBegintime());
//		//判断考试总体时间是否已经结束 ,diffExamEndTimeSecond 小于等于0,即为考试结束
//		String diffExamEndTimeSecond = CalendarUtil.dataDiffByNow(examStu.getExam().getExamEndtime());
//		boolean examIsEnd = Integer.parseInt(diffExamEndTimeSecond) <=0;
		List<ExamCourse> examCourseList = prepareServices.findExamCourse(examStu.getExamId(), examStu.getId());
		model.addAttribute("examCourseList", examCourseList);
		model.addAttribute("diffSecond", diffSecond);
		model.addAttribute("g", GUID.getGUID());
		return "prepare/prepare";
	}

}
