package com.edufe.module.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.framework.common.JsonUtil;
import com.edufe.framework.common.Util;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.helper.ApplicationHelper;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.bean.ExaminationType;
import com.edufe.module.entity.bean.QuesPoint;
import com.edufe.module.service.MQKafkaServiceImpl;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;
@Service
@Transactional
public class CommonServices {

	@Autowired
	protected JdbcTemplate jdbc;
	@Autowired
	private CacheUtil cache;
	@Autowired
	private MQKafkaServiceImpl mq;

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
					if("tiank".equals(ques.getTypeCode())){
						String[] userAnswerArr = userAnswer.split("@");
						ques.setUserAnswerArr(userAnswerArr);
					}else if("jiand".equals(ques.getTypeCode())){
						ques.setUserAnswer(userAnswer);
					}else{
						String[] userAnswerArr = new String[userAnswer.length()];
						for(int i=0;i<userAnswer.length();i++){
							userAnswerArr[i] = String.valueOf(userAnswer.charAt(i));
						}
						ques.setUserAnswerArr(userAnswerArr);
					}
					if(!"".equals(userAnswer)){
						ques.setAnswerState("1");
					}else{
						ques.setAnswerState("0");
					}
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
				if(quesInfo.length == 5){
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
				if(quesInfo.length == 5){
					answer = quesInfo[2];
				}
				if(!"".equals(answer)) list.add(quesId);
			}
		}
		return list;
	}

	public List<PaperExamination> findPaperExaminationList(int paperId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,answer,default_point,paper_id,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
//		sql.append("where paper_id=? limit 1");
		sql.append("where paper_id=? ");
		List<PaperExamination> list = jdbc.query(sql.toString(),new Object[]{paperId} ,new RowMapper<PaperExamination>(){
			@Override
			public PaperExamination mapRow(ResultSet rs, int rowNum) throws SQLException {
				PaperExamination obj = new PaperExamination();
				obj.setAnswer(rs.getString("answer")); 
//				obj.setDifficulty(rs.getString("difficulty")); 
				obj.setPaperId(rs.getInt("paper_id")); 
				obj.setTypeCode(rs.getString("type_code")); 
				obj.setId(rs.getInt("id")); 
				obj.setDefaultPoint(rs.getString("default_point")); 
				obj.setExaminationContent(rs.getString("examination_content")); 
			    obj.setQuesTypeName(ApplicationHelper.getInstance().getQuesTypeMap().get(obj.getTypeCode()).getTypename());
//			    obj.setExaminationContentHtml(rs.getString("examination_content_html"));
			    
			    obj.setOptionA(rs.getString("option_a"));
				obj.setOptionB(rs.getString("option_b"));
				obj.setOptionC(rs.getString("option_c"));
				obj.setOptionD(rs.getString("option_d"));
				obj.setOptionE(rs.getString("option_e"));
				obj.setOptionF(rs.getString("option_f"));
				return obj;
			}
			
		});
		return list;
	}
	
	public boolean submitPaper(Integer stuId, Integer courseId, String param, CacheBusiness business) {
		if(null == stuId) return false;
		if(null == courseId) return false;
		if(null != param && !"".equals(param)) saveQues(stuId, courseId, param); //判分前先保存答题记录
		ExamCourse ec = cache.getExamPaper(stuId, courseId);
		if(null == ec) return false;
		
		float score = 0f;
		int rightCount = 0;
		int wrongCount = 0;
		//
		for(ExaminationType eType : ec.getExaminationTypeList()){
			for(PaperExamination ques : eType.getPaperExaminationList()){
				QuesPoint qp = null;
				if("tiank".equals(ques.getTypeCode())){
					qp = getQuesPointByTiank(ques,parseFloat(ec.getPracMap().get(ques.getTypeCode()).getScore()));
				}else if("jiand".equals(ques.getTypeCode())){
					qp = getQuesPointByJiand(ques,parseFloat(ec.getPracMap().get(ques.getTypeCode()).getScore()));
				}else{
					qp = new QuesPoint();
					if(ques.getAnswer().equalsIgnoreCase(ques.getUserAnswer())){
						qp.setRightFlag(true);
						qp.setPoint(parseFloat(ec.getPracMap().get(ques.getTypeCode()).getScore()));
					}
				}
				
				if(qp.isRightFlag()){
					//正确
					rightCount ++;
					score += qp.getPoint();
					ques.setRightFlag(true);
				}else{
					wrongCount ++;
					ques.setRightFlag(false);
				}
			}
		}
		ec.setScore(String.valueOf(score));
		ec.setStuId(stuId);
		ec.setCourseId(courseId);
		
		mq.savePaper(stuId, courseId, ec.getExaminationTypeList(), ec.getPracMap(), business.getId());
		//清除REDIS中的试卷
//		cache.removeExamPaper(stuId, courseId);
		Object[] args = {score,rightCount, wrongCount, ec.getExamId(), stuId, courseId};
		//注：成绩只允许提交一次，如有成绩，不允许提交
		int r = jdbc.update("update exam_course set submit_time=now(),submit_flag='1',score=?,right_count=?,wrong_count=? where exam_id=? and stu_id=? and course_id=? and submit_flag='0'", args);
		return r == 1;
	}
	
	//简答
	private QuesPoint getQuesPointByJiand(PaperExamination ques, float point) {
		QuesPoint qp = new QuesPoint();
		if(null == ques.getAnswer()) return qp;
		String[] arr = ques.getAnswer().split(",");
		if(null == arr) return qp;
		int rightCount = 0;
		for(String keyword : arr){
			if(ques.getUserAnswer().indexOf(keyword) >=0){
				rightCount ++ ;
			}
		}
		
		if(rightCount == arr.length){
			//所有关键点都答对
			qp.setPoint(point);
			qp.setRightFlag(true);
		}else if(rightCount == 0){
		}else{
			int onePoint = (int)(point / arr.length);
			qp.setPoint(onePoint * rightCount);
			qp.setRightFlag(true);
		}
		return qp;
	}

	//填空题
	private QuesPoint getQuesPointByTiank(PaperExamination ques, float point) {
		QuesPoint qp = new QuesPoint();
		if(null == ques.getUserAnswerArr()) return qp;
		String[] answerArr = {ques.getOptionA(),ques.getOptionB(),ques.getOptionC(),ques.getOptionD(),ques.getOptionE(),ques.getOptionF()};
		int optionNum = getOptionNum(answerArr);
		int rightCount = 0;
		int index = 0;
		for(String userAnswer : ques.getUserAnswerArr()){
			userAnswer = Util.trim(userAnswer);
			if(null != userAnswer && !"".equalsIgnoreCase(userAnswer) && userAnswer.equalsIgnoreCase(Util.trim(answerArr[index]))){
				rightCount ++ ;
			}
			
			index ++;
		}
		
		if(rightCount == optionNum){
			//所有关键点都答对
			qp.setPoint(point);
			qp.setRightFlag(true);
		}else if(rightCount == 0){
		}else{
			int onePoint = (int)(point / optionNum);
			qp.setPoint(onePoint * rightCount);
			qp.setRightFlag(true);
		}
		return qp;
	}

	private int getOptionNum(String[] answerArr) {
		if(null == answerArr) return 0;
		int optionNum = 0;
		for(String s : answerArr){
			if(null == s || "".equals(s)){
				return optionNum;
			}
			optionNum ++;
		}
		return 0;
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

	/**
	 * 切屏次数保存
	 * @param stuId
	 * @param courseId
	 * @return
	 */
	public int savePageChange(int stuId, Integer cid) {
		// TODO Auto-generated method stub
		jdbc.update("update exam_course set page_change=ifnull(page_change,0)+1 where stu_id=? and course_id=? and submit_flag='0'", new Object[]{stuId, cid});
		
		int pageChangeCount = jdbc.queryForObject("select ifnull(page_change,0) page_change from exam_course where stu_id=? and course_id=?", new Object[]{stuId, cid}, Integer.class);
		return pageChangeCount;
	}
}
