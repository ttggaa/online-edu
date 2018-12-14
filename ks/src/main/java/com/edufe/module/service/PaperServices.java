package com.edufe.module.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.framework.common.GUID;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.common.calendar.CalendarUtil;
import com.edufe.framework.helper.ApplicationHelper;
import com.edufe.framework.helper.SessionHelper;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.ExamStu;
import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.Type;
import com.edufe.module.entity.bean.ExaminationType;
@Service
@Transactional
public class PaperServices {
	@Autowired
	private CacheUtil cache;
	@Autowired
	protected JdbcTemplate jdbc;

	public boolean checkExamTime() {
		ExamStu examStu = SessionHelper.getInstance().getExamStu();
		//考试开始，倒计时 （秒）
		String diffSecond = CalendarUtil.dataDiffByNow(examStu.getExam().getExamBegintime());
		//判断考试总体时间是否已经结束 ,diffExamEndTimeSecond 小于等于0,即为考试结束
		String diffExamEndTimeSecond = CalendarUtil.dataDiffByNow(examStu.getExam().getExamEndtime());
		
		if(Integer.parseInt(diffSecond) <=0 && Integer.parseInt(diffExamEndTimeSecond) <=0) return true;
		return false;
	}

	public ExamCourse findExamCourseByStu(Integer cid, int stuId) {
		ExamCourse ec = cache.getExamPaper(stuId,cid);
		ExamStu examStu = SessionHelper.getInstance().getExamStu();
		if(null == ec){
			//随机获取试卷ID
			int paperId = getRoundPaperId(cid);
			
			ec = findExamCourseObject(examStu.getExamId(), cid, stuId);
			//查询考试试题
			if(paperId > 0){
				List<ExaminationType> examinationTypeList = new ArrayList<ExaminationType>();
				
				List<PaperExamination> paperExaminationList = findPaperExaminationList(paperId);
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
				ec.setExaminationTypeList(examinationTypeList);
				
				//获取计算科目考试的结束时间点
				if(null == ec.getEndTime() || "".equals(ec.getEndTime())){
					String endTime = getExamCourseEndTime(examStu.getExam().getExamEndtime(), ec.getEndTime(),ec.getExamSumTime());
					ec.setEndTime(endTime);
				}
				//更新科目结束时间到DB
				jdbc.update("update exam_course set end_time=?,cache_examcourse_flag='1' where id=?" , new Object[]{ec.getEndTime(), ec.getId()});
				//每次缓存试卷前生成认证码
				ec.setAuthCode(GUID.getGUID());
				//保存到缓存
				cache.setExamPaper(stuId, cid.intValue(), ec);
			}
		}else{
			if(null == ec.getEndTime() || "".equals(ec.getEndTime())){
				String endTime = getExamCourseEndTime(examStu.getExam().getExamEndtime(), ec.getEndTime(),ec.getExamSumTime());
				//更新科目结束时间到DB
				jdbc.update("update exam_course set end_time=? where id=?" , new Object[]{endTime, ec.getId()});
				ec.setEndTime(endTime);
			}
		}
		return ec;
	}

	/**
	 * 获取计算科目考试的结束时间点
	 * @param examEndtime 考试整体结束时间
	 * @param endTime 本科结束时间
	 * @param examSumTime 科目总体时间（分钟）
	 * @return
	 */
	private String getExamCourseEndTime(String examEndtime, String endTime, String examSumTime) {
		if(null != endTime && !"".equals(endTime)) return endTime;
		if(null == examSumTime || "".equals(examSumTime)) {
			//未设置科目时长时，直接返回本场考试结束时间
			return examEndtime;
		}
		//首先用当前时间+科目总体时间
		String endTimeTemp = CalendarUtil.dateTimeAddMins(Integer.parseInt(examSumTime));
		//判断科目结束时间是否超出 整体考试结束时间，如果超出，则设置为整体考试结束时间
		long t = CalendarUtil.dataDiff(endTimeTemp, examEndtime);
		if(t > 0){
			return endTimeTemp; //超出整体时间
		}
		return examEndtime;
	}

	/**
	 * 根据考生ID 及科目ID查询科目的考试结束时间
	 * @param cid
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public String findExamCourseEndTime(Integer examId, Integer cid , Integer stuId) throws Exception{
		String sql = "select end_time from exam_course where exam_id=? and stu_id=? and course_id=?";
		return jdbc.queryForObject(sql, new Object[]{examId, stuId, cid} , String.class);
	}
	private int getRoundPaperId(Integer cid) {
//		int paperSumCount = jdbc.queryForObject("select count(1) from paper where course_id=?", new Object[]{cid},Integer.class);
//		if(paperSumCount <= 0) return 0;
//		Random rand = new Random();
//		int roundNum = rand.nextInt(paperSumCount);
//		
//		int paperId = jdbc.queryForObject("select p.id from paper p where p.course_id=? LIMIT ?,1", new Object[]{cid,roundNum}, Integer.class);
		return jdbc.queryForObject("select p.id from paper p where p.course_id=? ORDER BY RAND() LIMIT 1", new Object[]{cid}, Integer.class);
	}

	public ExamCourse findExamCourseObject(int examId,int cid, int stuId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ec.id,ec.stu_id,ec.course_id,ec.end_time,ec.score,ec.submit_time,ec.submit_flag,ec.exam_id, rc.course_name,rc.exam_sum_time ");
		sql.append("FROM exam_course ec inner join res_course rc on ec.course_id=rc.id ");
		sql.append("where ec.exam_id=? and ec.course_id=? and ec.stu_id=?");
		ExamCourse obj = jdbc.queryForObject(sql.toString(), new Object[]{examId,cid, stuId}, new RowMapper<ExamCourse>(){
			@Override
			public ExamCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
				ExamCourse c = new ExamCourse();
				c.setId(rs.getInt("id"));
				c.setStuId(rs.getInt("stu_id"));
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setExamSumTime(rs.getString("exam_sum_time"));
				c.setEndTime(rs.getString("end_time"));
				c.setScore(rs.getString("score"));
				c.setSubmitTime(rs.getString("submit_time"));
				c.setSubmitFlag(rs.getString("submit_flag"));
				c.setExamId(rs.getInt("exam_id"));
				return c;
			}
		});
		return obj;
	}
	
	public List<PaperExamination> findPaperExaminationList(int paperId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_content_html,answer,default_point,difficulty,paper_id,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
		sql.append("where paper_id=? ");
		List<PaperExamination> list = jdbc.query(sql.toString(),new Object[]{paperId} ,new RowMapper<PaperExamination>(){
			@Override
			public PaperExamination mapRow(ResultSet rs, int rowNum) throws SQLException {
				PaperExamination obj = new PaperExamination();
				obj.setAnswer(rs.getString("answer")); 
				obj.setDifficulty(rs.getString("difficulty")); 
				obj.setPaperId(rs.getInt("paper_id")); 
				obj.setTypeCode(rs.getString("type_code")); 
				obj.setId(rs.getInt("id")); 
				obj.setDefaultPoint(rs.getString("default_point")); 
				obj.setExaminationContent(rs.getString("examination_content")); 
			    obj.setQuesTypeName(ApplicationHelper.getInstance().getQuesTypeMap().get(obj.getTypeCode()).getTypename());
			    obj.setExaminationContentHtml(rs.getString("examination_content_html"));
			    
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

	public String getExamCourseOverTimeSecond(String endTime) {
		String beginDt = CalendarUtil.getCurrentDateAll();
		long t = CalendarUtil.dataDiff(beginDt, endTime);
		return String.valueOf(t);
	}

	public boolean isSubmitFlag(int stuId, Integer cid) {
		String sql = "select count(1) from exam_course where stu_id=? and course_id=? and submit_flag='1'";
		return jdbc.queryForObject(sql, new Object[]{stuId, cid}, Integer.class) > 0;
	}
	
}
