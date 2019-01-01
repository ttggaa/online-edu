package com.edufe.module.common;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.beans.R;
import com.edufe.framework.common.JwtUtils;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.helper.ApplicationHelper;
import com.edufe.module.entity.ApiResult;
import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.Type;
import com.edufe.module.entity.bean.ExaminationType;
import com.edufe.module.entity.bean.SaveQuesBean;
import com.edufe.module.entity.bean.SubmitBean;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("common")
public class CommonController extends BaseController{
	@Autowired
    private JwtUtils jwtUtils;
	@Autowired
	private CommonServices commonServices;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 保存答题记录
	 * @param param
	 *   题ID#题型代码#答案,题ID#题型代码#答案
	 * @return
	 */
	@RequestMapping("saveQues")
	public R saveQues(@RequestBody SaveQuesBean bean, @RequestHeader("token") String token) {
		String uid = jwtUtils.getUserid(token);
		if(null != uid){
			boolean f = commonServices.saveQues(Integer.parseInt(uid),bean.getCourseId(), bean.getParam());
			if(f){
				Map<String, Object> map = new HashMap<>();
				map.put("retData", commonServices.paramConvertQuesIdList(bean.getParam()));
				return R.ok(map);
			}else{
				return R.error("保存失败！");
			}
		}else{
			return R.error("请求参数异常！");
		}
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
	@RequestMapping("submitPaper")
	public R submitPaper(
			@RequestBody SubmitBean bean, @RequestHeader("token") String token) {
		String uid = jwtUtils.getUserid(token);
		if(null != uid){
			int stuId = Integer.parseInt(uid);
			if(commonServices.isSubmitFlag(stuId, bean.getCourseId())){
				return R.error("请勿重复提交！");
			}
			//未提交情况，继续提交
			boolean f = commonServices.submitPaper(stuId,bean.getCourseId(), bean.getParam());
			if(f){
				Map<String, Object> map = new HashMap<>();
				map.put("retData", "success");
				return R.ok(map);
			}else{
				return R.error("试卷提交异常，请稍候再试！");
			}
		}else{
			return R.error("请求参数异常！");
		}
	}
	
	@RequestMapping("findPaperQues/{paperId}")
	@ResponseBody
	public String findPaperQues(
			@RequestParam String callback,
			@PathVariable("paperId") Integer paperId) {
		ApiResult result = new ApiResult();
		List<ExaminationType> examinationTypeList = new ArrayList<ExaminationType>();
		
		List<PaperExamination> paperExaminationList = commonServices.findPaperExaminationList(paperId);
		List<Type> typeList = ApplicationHelper.getInstance().getQuesTypeList();
		//按题型分类存放试题
		for(Type type : typeList){
			ExaminationType et = new ExaminationType();
			et.setType(type);
			List<PaperExamination> peTempList = new ArrayList<PaperExamination>();
			for(PaperExamination e : paperExaminationList){
				if(type.getTypeCode().equals(e.getTypeCode())){
					peTempList.add(e);
				}
			}
			
			//判断peTempList 是否有该题型的试题，如果有，则放入examinationTypeList中
			if(peTempList.size()>0){
				et.setPaperExaminationList(peTempList);
				et.setQuesCount(peTempList.size());
				et.setScore(peTempList.get(0).getDefaultPoint());
				examinationTypeList.add(et);
			}
		}
		
		result.setData(examinationTypeList);
		return callback+"("+JSONObject.fromObject(result).toString()+")";
	}
	
}
