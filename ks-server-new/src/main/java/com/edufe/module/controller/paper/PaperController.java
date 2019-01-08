package com.edufe.module.controller.paper;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.beans.R;
import com.edufe.framework.common.JwtUtils;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.validator.ValidatorUtils;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.entity.bean.ExCourseBean;
import com.edufe.module.service.LoginServices;
import com.edufe.module.service.PaperServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("paper")
@Api("考试答卷接口")
public class PaperController extends BaseController{
	@Autowired
    private JwtUtils jwtUtils;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private PaperServices paperServices;
	@Autowired
	private LoginServices loginServices;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("getExamPaperData")
	@ApiOperation("查询考试页面数据")
	public R getExamPaperData(@RequestBody ExCourseBean cBean, @RequestHeader("token") String token) {
		//表单校验
        ValidatorUtils.validateEntity(cBean);
        
		String uid = jwtUtils.getUserid(token);
		if(null != uid){
			ExamStu examStu = loginServices.findExamStuObj(uid);
			/** 暂时注释，如时间已到，进入试卷后自动提交
			//验证考试开始及截止时间是否满足开考条件
			boolean cFlag = paperServices.checkExamTime();
			if(!cFlag){
				redirectAttributes.addFlashAttribute("MESSAGE", "不在允许考试时间范围内！");
				return "redirect:/prepare";
			}
			**/
			//判断是否已有成绩，如有成绩直接返回列表
			if(paperServices.isSubmitFlag(examStu.getId(),Integer.valueOf(cBean.getCid()))){
				return R.error("本科目考试已提交，请求数据失败！");
			}
			String overTimeSecond = "";
			try {
				String endTime = paperServices.findAndUpdateExamCourseEndTime(examStu.getExam(), examStu.getExamId(), Integer.valueOf(cBean.getCid()), examStu.getId());
				overTimeSecond = String.valueOf(CalendarUtil.dataDiff(endTime));
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//从缓存读取该科目试卷，如没有，则从DB查询试卷信息
			ExamCourse examCourse = paperServices.findExamCourseByStu(Integer.valueOf(cBean.getCid()),examStu);
			Map<String, Object> map = new HashMap<>();
			map.put("truename",examStu.getTruename());
	        map.put("idcard",examStu.getIdcard());
	        map.put("examname",examStu.getExam().getExamName());
			map.put("overTimeSecond", overTimeSecond);
			map.put("testFlag", examStu.getTestFlag());
			if(null != examCourse){
				map.put("examCourse", examCourse);
				map.put("examCourseLoadingState", "true"); //试卷加载成功
			}else{
				map.put("examCourseLoadingState", "false"); //试卷加载失败
			}
			return R.ok(map);
		}else{
			return R.error("请求数据失败！");
		}
	}
	
	@PostMapping("getOverTimeSecond")
	@ApiOperation("服务器时间倒计时同步接口")
	public R getOverTimeSecond(@RequestBody ExCourseBean cBean, @RequestHeader("token") String token){
		//表单校验
        ValidatorUtils.validateEntity(cBean);
        
		String uid = jwtUtils.getUserid(token);
		if(null != uid){
			ExamStu examStu = loginServices.findExamStu(uid);
			//计算本科考试剩余秒数
			try {
				String endTime = paperServices.findExamCourseEndTime(examStu.getExamId(), Integer.parseInt(cBean.getCid()), examStu.getId());
				String overTimeSecond = String.valueOf(CalendarUtil.dataDiff(endTime));
				Map<String, Object> map = new HashMap<>();
				map.put("overTimeSecond",overTimeSecond);
				return R.ok(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return R.error("请求数据失败！");
	}
}
