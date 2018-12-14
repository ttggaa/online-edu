package com.edufe.module.controller.paper;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.helper.SessionHelper;
import com.edufe.module.entity.ApiResult;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.service.PaperServices;

@Controller
@RequestMapping("paper")
public class PaperController extends BaseController{

	@Autowired
	HttpServletRequest request;
	@Autowired
	private PaperServices paperServices;
	@Value("${ques.save.server}")
	private String quesSaveServer;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("{cid}")
	public String init(@PathVariable("cid") Integer cid, Model model,RedirectAttributes redirectAttributes) {
		ExamStu examStu = SessionHelper.getInstance().getExamStu();
		/** 暂时注释，如时间已到，进入试卷后自动提交
		//验证考试开始及截止时间是否满足开考条件
		boolean cFlag = paperServices.checkExamTime();
		if(!cFlag){
			redirectAttributes.addFlashAttribute("MESSAGE", "不在允许考试时间范围内！");
			return "redirect:/prepare";
		}
		**/
		//判断是否已有成绩，如有成绩直接返回列表
		if(paperServices.isSubmitFlag(examStu.getId(),cid)){
			return "redirect:/prepare";
		}
		//从缓存读取该科目试卷，如没有，则从DB查询试卷信息
		ExamCourse examCourse = paperServices.findExamCourseByStu(cid,examStu.getId());
		//计算本科考试剩余秒数
//		String overTimeSecond = paperServices.getExamCourseOverTimeSecond(examCourse.getEndTime());
		model.addAttribute("examCourse", examCourse);
//		model.addAttribute("overTimeSecond", overTimeSecond);
		model.addAttribute("quesSaveServer", quesSaveServer);
		return "paper/paper";
	}
	
	@RequestMapping("getOverTimeSecond")
	@ResponseBody
	public ApiResult getOverTimeSecond(
			@RequestParam(value="cid",required=true) Integer cid,
			@RequestParam(value="examId",required=true) Integer examId){
		ApiResult result = new ApiResult();
		ExamStu examStu = SessionHelper.getInstance().getExamStu();
		//计算本科考试剩余秒数
		try {
			String endTime = paperServices.findExamCourseEndTime(examId, cid, examStu.getId());
			String overTimeSecond = String.valueOf(CalendarUtil.dataDiff(endTime));
			result.setData(overTimeSecond);
			result.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
		}
		return result;
	}
}
