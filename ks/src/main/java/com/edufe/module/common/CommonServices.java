package com.edufe.module.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.bean.ExaminationType;
@Service
@Transactional
public class CommonServices {

	@Autowired
	protected JdbcTemplate jdbc;
	@Autowired
	private CacheUtil cache;

	public boolean saveQues(Integer stuId, Integer courseId, String param) {
		if(null == stuId) return false;
		if(null == courseId) return false;
		if(null == param) return false;
		
		ExamCourse ec = cache.getExamPaper(stuId, courseId);
		if(null == ec) return false;
		//是否有改变标记
		boolean changeFlag = false;
		Map<String,String> paramMap = paramConvertMap(param);
		for(ExaminationType eType : ec.getExaminationTypeList()){
			for(PaperExamination ques : eType.getPaperExaminationList()){
				if(paramMap.containsKey(String.valueOf(ques.getId()))){
					//替换考生答案
					String userAnswer = paramMap.get(String.valueOf(ques.getId()));
					ques.setUserAnswer(userAnswer);
					ques.fillSel(userAnswer);
					changeFlag = true;
				}
			}
		}
		
		//重置cache
		if(changeFlag) cache.setExamPaper(stuId, courseId, ec);
		return true;
	}
	
	private Map<String,String> paramConvertMap(String param) {
		Map<String,String> map = new HashMap<String,String>();
		//试题参数解析 ,题ID#题型代码#答案,题ID#题型代码#答案
		String[] quesArr = param.split(",");
		if(null == quesArr) return map;
		for(String ques : quesArr){
			String[] quesInfo = ques.split("#");
			if(null != quesInfo){
				//将考生提交的答案与试卷试题逐一比较，替换最新答案
				String quesId = quesInfo[0];
				String answer = "";
				if(quesInfo.length == 3){
					answer = quesInfo[2];
				}
				map.put(quesId, answer);
			}
		}
		return map;
	}

	
	public List<String> paramConvertQuesIdList(String param) {
		List<String> list = new ArrayList<String>();
		//试题参数解析 ,题ID#题型代码#答案,题ID#题型代码#答案
		String[] quesArr = param.split(",");
		if(null == quesArr) return list;
		
		for(String ques : quesArr){
			String[] quesInfo = ques.split("#");
			if(null != quesInfo){
				//将考生提交的答案与试卷试题逐一比较，替换最新答案
				String quesId = quesInfo[0];
				String answer = "";
				if(quesInfo.length == 3){
					answer = quesInfo[2];
				}
				if(!"".equals(answer)) list.add(quesId);
			}
		}
		return list;
	}

	public boolean submitPaper(Integer stuId, Integer courseId, String param,String authCode) {
		if(null == stuId) return false;
		if(null == courseId) return false;
		if(null == authCode) return false;
		if(null != param && !"".equals(param)) saveQues(stuId, courseId, param); //判分前先保存答题记录
		ExamCourse ec = cache.getExamPaper(stuId, courseId);
		if(null == ec) return false;
		//验证认证码是否一致,防止外部代提交
		if(!authCode.equalsIgnoreCase(ec.getAuthCode())) return false;
		
		float score = 0f;
		int rightCount = 0;
		int wrongCount = 0;
		//
		for(ExaminationType eType : ec.getExaminationTypeList()){
			for(PaperExamination ques : eType.getPaperExaminationList()){
				if(ques.getAnswer().equalsIgnoreCase(ques.getUserAnswer())){
					//正确
					rightCount ++;
					score += parseFloat(ques.getDefaultPoint());
				}else{
					wrongCount ++;
				}
			}
		}
		//清除REDIS中的试卷
		cache.removeExamPaper(stuId, courseId);
		Object[] args = {score,rightCount, wrongCount, ec.getExamId(), stuId, courseId};
		//注：成绩只允许提交一次，如有成绩，不允许提交
		int r = jdbc.update("update exam_course set submit_time=now(),submit_flag='1',score=?,right_count=?,wrong_count=? where exam_id=? and stu_id=? and course_id=? and ifnull(score,0)=0", args);
		return r == 1;
	}

	private float parseFloat(String point) {
		float r = 0f;
		try{
			r = Float.parseFloat(point);
		}catch(Exception e){
		}
		return r;
	}
	
	public boolean isSubmitFlag(int stuId, Integer cid) {
		String sql = "select count(1) from exam_course where stu_id=? and course_id=? and submit_flag='1'";
		return jdbc.queryForObject(sql, new Object[]{stuId, cid}, Integer.class) > 0;
	}
}
