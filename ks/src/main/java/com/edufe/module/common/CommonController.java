package com.edufe.module.common;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edufe.framework.base.BaseController;
import com.edufe.module.entity.ApiResult;

@Controller
@RequestMapping("common")
public class CommonController extends BaseController{

	@Autowired
	private CommonServices commonServices;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 保存答题记录
	 * @param param
	 *   题ID#题型代码#答案,题ID#题型代码#答案
	 * @return
	 */
	@RequestMapping("saveQues/{stuId}/{courseId}")
	@ResponseBody
	public String saveQues(
			@RequestParam String callback,
			@PathVariable("stuId") Integer stuId,
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="param",required=true) String param) {
		ApiResult result = new ApiResult();
		boolean f = commonServices.saveQues(stuId,courseId,param);
		if(f){
			result.setCode(1);
			result.setData(commonServices.paramConvertQuesIdList(param));
		}else{
			result.setCode(0);
		}
		return callback+"("+JSONObject.fromObject(result).toString()+")";
	}
	
	/**
	 * 提交试卷
	 * @param callback
	 * @param stuId
	 * @param courseId
	 * @param param 之前未正常保存试题，一同随着提交
	 * @param authCode 认证码与redis对比
	 * @return
	 */
	@RequestMapping("submitPaper/{stuId}/{courseId}")
	@ResponseBody
	public String submitPaper(
			@RequestParam String callback,
			@PathVariable("stuId") Integer stuId,
			@PathVariable("courseId") Integer courseId,
			@RequestParam(value="authCode",required=false) String authCode,
			@RequestParam(value="param",required=false) String param) {
		ApiResult result = new ApiResult();
		if(commonServices.isSubmitFlag(stuId, courseId)){
			result.setCode(1);
			return callback+"("+JSONObject.fromObject(result).toString()+")";
		}
		//未提交情况，继续提交
		boolean f = commonServices.submitPaper(stuId,courseId,param,authCode);
		if(f){
			result.setCode(1);
		}else{
			result.setCode(0);
		}
		return callback+"("+JSONObject.fromObject(result).toString()+")";
	}
	
}
