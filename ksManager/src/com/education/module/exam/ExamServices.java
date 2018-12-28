package com.education.module.exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.Exam;
import com.education.domain.extend.ExamPracBean;
import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.GUID;
import com.education.framework.util.cache.CacheManager;
import com.education.framework.util.calendar.CalendarUtil;
import com.edufe.module.entity.PaperExaminationOld;
import com.edufe.module.entity.Type;
import com.edufe.module.entity.bean.ExaminationType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ExamServices extends BaseServices implements IDao<Exam>{

	@Autowired
	private CacheManager cache;
	
	@Override
	public List<Exam> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,exam_name,exam_begintime,exam_endtime,create_time,create_user,getExamUserCount(id) examUserCount,introduce,course_conf,business_id,prac_conf, paper_build_count,pass_score FROM exam");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("examName") && !"".equals((String)searchParams.get("examName"))){
				sql.append(lp).append(" exam_name like ? ");
				argsList.add("%" + searchParams.get("examName") + "%");
				lp = " and ";
			}
		}
		
		sql.append(lp).append(" business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		
		Object[] args = argsList.toArray();
		List<Exam> list = dao.query(pageSQL(sql.toString(),"order by create_time desc",page),args,new ExamRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<Exam> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,exam_name,exam_begintime,exam_endtime,create_time,create_user,getExamUserCount(id) examUserCount,introduce,course_conf,business_id,prac_conf, paper_build_count,pass_score FROM exam ");
		sql.append("where business_id=?");
		List<Exam> list = dao.query(sql.toString(),new Object[]{SessionHelper.getInstance().getUser().getBusinessId()},new ExamRowmapper());
		return list;
	}
	
	public List<Exam> findAll() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,exam_name,exam_begintime,exam_endtime,create_time,create_user,getExamUserCount(id) examUserCount,introduce,course_conf,business_id,prac_conf, paper_build_count,pass_score FROM exam");
		
		List<Exam> list = dao.query(sql.toString(),new ExamRowmapper());
		return list;
	}

	@Override
	public int save(Exam obj) {
		String courseConf = "";
		 String lp = "";
		 if(null != obj.getSelCourseArr() && obj.getSelCourseArr().length >0){
			 for(String cs : obj.getSelCourseArr()){
				 courseConf += lp + cs;
				 lp = ",";
			 }
		 }
		 String pracConf = convertPracConf(obj.getPracList());
		 SysUser sessionUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into exam ( "); 
		 sql.append("exam_name,exam_begintime,exam_endtime,create_time,create_user,introduce,business_id,course_conf,prac_conf, paper_build_count,pass_score ");  
		 sql.append(" ) values(?,?,?,now(),?,?,?,?,?,?,?) "); 
		 String beginTime = obj.getExamBegintime();
		 String endTime = obj.getExamEndtime();
		 if(obj.getExamBegintime().length() == 16) beginTime = obj.getExamBegintime() + ":00";
		 if(obj.getExamEndtime().length() == 16) endTime = obj.getExamEndtime() + ":00";
		 Object[] args = {obj.getExamName(),beginTime,endTime,sessionUser.getId()
				 ,obj.getIntroduce(),SessionHelper.getInstance().getUser().getBusinessId(), 
				 courseConf,pracConf, obj.getPaperBuildCount(), obj.getPassScore()};
		 
		 dao.update(sql.toString(), args);
		 return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public Exam findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,exam_name,exam_begintime,exam_endtime,create_time,create_user,getExamUserCount(id) examUserCount,introduce,course_conf,business_id,prac_conf, paper_build_count,pass_score FROM exam ");
		sql.append(" where id=? ");
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new ExamRowmapper());
	}
	
	public String findPracConf(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT prac_conf FROM exam ");
		sql.append(" where id=? ");
		Object[] args = {id};
		String ret = "";
		try{
			ret = dao.queryForObject(sql.toString(),args,String.class);
		}catch(Exception ex){
		}
		return null == ret ? "":ret;
	}

	@Override
	public void update(Exam obj) {
		String courseConf = "";
		 String lp = "";
		 if(null != obj.getSelCourseArr() && obj.getSelCourseArr().length >0){
			 for(String cs : obj.getSelCourseArr()){
				 courseConf += lp + cs;
				 lp = ",";
			 }
		 }
		 
		 String pracConf = convertPracConf(obj.getPracList());
		 obj.setPracConf(pracConf);
		 String beginTime = obj.getExamBegintime();
		 String endTime = obj.getExamEndtime();
		 if(obj.getExamBegintime().length() == 16) beginTime = obj.getExamBegintime() + ":00";
		 if(obj.getExamEndtime().length() == 16) endTime = obj.getExamEndtime() + ":00";
		 if(!CalendarUtil.isDateTimeStr(beginTime) || !CalendarUtil.isDateTimeStr(endTime)) return ;
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update exam "); 
		 sql.append("set  "); 
		 sql.append("exam_name=?,exam_begintime=?,exam_endtime=?,introduce=?,course_conf=? ,prac_conf=?, paper_build_count=?,pass_score=? where id=?"); 
		 Object[] args = {obj.getExamName(),beginTime,endTime,obj.getIntroduce(),courseConf, pracConf, obj.getPaperBuildCount(),obj.getPassScore(),obj.getId() };
		 dao.update(sql.toString(), args);
	}
	
	public String convertPracConf(List<ExamPracBean> pracList){
		String pracConf = JSONArray.fromObject(pracList).toString();
		return pracConf;
	}
	
	public List<ExamPracBean> convertPracConfList(String pracConf){
		List<ExamPracBean> list = new ArrayList<ExamPracBean>();
		if(null == pracConf || "".equals(pracConf)) return list;
		
		JSONArray array = JSONArray.fromObject(pracConf);
		for(int i=0;i<array.size();i++){
			JSONObject json = array.getJSONObject(i);
			ExamPracBean b = (ExamPracBean)JSONObject.toBean(json,ExamPracBean.class);
			list.add(b);
		}
		return list;
	}

	@Override
	public void delete(Integer id) {
		dao.update("delete from exam_course where exam_id=?", new Object[]{id});
		dao.update("delete from exam_stu where exam_id=?", new Object[]{id});
		String sql = "delete from exam where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class ExamRowmapper implements RowMapper<Exam> {
		@Override
		public Exam mapRow(ResultSet rs, int arg1) throws SQLException {
			Exam obj = new Exam();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setExamBegintime(rs.getString("exam_begintime")); 
			obj.setExamEndtime(rs.getString("exam_endtime")); 
			obj.setExamName(rs.getString("exam_name")); 
			obj.setId(rs.getInt("id")); 
			obj.setExamUserCount(rs.getInt("examUserCount"));
			obj.setIntroduce(rs.getString("introduce"));
			obj.setBusinessId(rs.getInt("business_id"));
			String courseConf = rs.getString("course_conf");
			String[] courseConfArr = courseConf.split(",");
			if(null != courseConfArr){
				obj.setSelCourseArr(courseConfArr);
			}
			obj.setPaperBuildCount(rs.getInt("paper_build_count"));
			obj.setPracConf(rs.getString("prac_conf"));
			obj.setPassScore(rs.getFloat("pass_score"));
			return obj;
		}
	}

	public List<String> findExamNameList(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT exam_name FROM exam where exam_name like ? ");
		sql.append(" and business_id = ? ");
		List<String> list = dao.query(sql.toString(),new Object[]{"%" + name + "%",SessionHelper.getInstance().getUser().getBusinessId()},new RowMapper(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("exam_name");
			}
			
		});
		return list;
	}
	
	/**
	 * 清除考试活动下所有已被缓存过的试卷对象
	 * @param examId
	 * @return
	 */
	public boolean batchRemoveCachePaper(String examId) {
		List<com.edufe.module.entity.ExamCourse> waitProcessExamStuList = findCacheExamCourseList(examId);
		for(com.edufe.module.entity.ExamCourse ec : waitProcessExamStuList){
			cache.removeExamPaper(ec.getStuId(), ec.getCourseId());
		}
		return true;
	}

	/**
	 * 批量缓存考试活动下未缓存的试卷对象
	 * @param examId
	 * @return
	 */
	public boolean batchCachePaper(String examId) {
		List<com.edufe.module.entity.ExamCourse> waitProcessExamStuList = findWaitProcessExamCourseList(examId);
		for(com.edufe.module.entity.ExamCourse ec : waitProcessExamStuList){
			//随机获取试卷ID
			int paperId = getRoundPaperId(ec.getCourseId());
			
			//查询考试试题
			if(paperId > 0){
				List<ExaminationType> examinationTypeList = new ArrayList<ExaminationType>();
				
				List<PaperExaminationOld> paperExaminationList = findPaperExaminationList(paperId);
				List<Type> typeList = ApplicationHelper.getInstance().getQuesTypeList();
				//按题型分类存放试题
				for(Type type : typeList){
					ExaminationType et = new ExaminationType();
					et.setType(type);
					List<PaperExaminationOld> peTempList = new ArrayList<PaperExaminationOld>();
					for(PaperExaminationOld e : paperExaminationList){
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
				
				//每次缓存试卷前生成认证码
				ec.setAuthCode(GUID.getGUID());
				//保存到缓存
				cache.setExamPaper(ec.getStuId(), ec.getCourseId(), ec);
				dao.update("update exam_course set cache_examcourse_flag='1' where id=?" , new Object[]{ec.getId()});
			}
		}
		return true;
	}

	private List<com.edufe.module.entity.ExamCourse> findWaitProcessExamCourseList(String examId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ec.id,ec.stu_id,ec.course_id,ec.end_time,ec.score,ec.submit_time,ec.submit_flag,ec.exam_id, rc.course_name,rc.exam_sum_time ");
		sql.append("FROM exam_course ec inner join res_course rc on ec.course_id=rc.id ");
		sql.append("where ec.exam_id=? and cache_examcourse_flag='0'");
		
		List<com.edufe.module.entity.ExamCourse> list = dao.query(sql.toString(),new Object[]{examId},new RowMapper<com.edufe.module.entity.ExamCourse>(){
			@Override
			public com.edufe.module.entity.ExamCourse mapRow(ResultSet rs, int arg1) throws SQLException {
				com.edufe.module.entity.ExamCourse c = new com.edufe.module.entity.ExamCourse();
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
		return list;
	}
	
	/**
	 * 查询被缓存过试卷的科目列表
	 * @param examId
	 * @return
	 */
	private List<com.edufe.module.entity.ExamCourse> findCacheExamCourseList(String examId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ec.id,ec.stu_id,ec.course_id,ec.end_time,ec.score,ec.submit_time,ec.submit_flag,ec.exam_id, rc.course_name,rc.exam_sum_time ");
		sql.append("FROM exam_course ec inner join res_course rc on ec.course_id=rc.id ");
		sql.append("where ec.exam_id=? and cache_examcourse_flag='1'");
		
		List<com.edufe.module.entity.ExamCourse> list = dao.query(sql.toString(),new Object[]{examId},new RowMapper<com.edufe.module.entity.ExamCourse>(){
			@Override
			public com.edufe.module.entity.ExamCourse mapRow(ResultSet rs, int arg1) throws SQLException {
				com.edufe.module.entity.ExamCourse c = new com.edufe.module.entity.ExamCourse();
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
		return list;
	}
	
	private int getRoundPaperId(Integer cid) {
		return dao.queryForObject("select p.id from paper p where p.course_id=? ORDER BY RAND() LIMIT 1", new Object[]{cid}, Integer.class);
	}
	
	public List<PaperExaminationOld> findPaperExaminationList(int paperId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_content_html,answer,default_point,difficulty,paper_id,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
		sql.append("where paper_id=? ");
		List<PaperExaminationOld> list = dao.query(sql.toString(),new Object[]{paperId} ,new RowMapper<PaperExaminationOld>(){
			@Override
			public PaperExaminationOld mapRow(ResultSet rs, int rowNum) throws SQLException {
				PaperExaminationOld obj = new PaperExaminationOld();
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
}
