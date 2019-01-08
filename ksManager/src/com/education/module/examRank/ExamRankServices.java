package com.education.module.examRank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.domain.Exam;
import com.education.domain.ExamCourse;
import com.education.domain.ExamStu;
import com.education.domain.ResCourse;
import com.education.domain.extend.ExamRankBase;
import com.education.domain.extend.ExamRankCourse;
import com.education.framework.base.BaseServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.CommonTools;
import com.education.framework.util.cache.CacheManager;
import com.education.module.exam.ExamServices;
import com.education.module.resCourse.ResCourseServices;
import com.edufe.module.entity.ResCourseBean;

@Service
@Transactional
public class ExamRankServices extends BaseServices{
	@Autowired
	private ExamServices examServices;
	@Autowired
	private ResCourseServices resServices;
	@Resource(name="cacheManager")
	private CacheManager cache;
	
	public List<ExamStu> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT es.id,idcard,identitycode,truename,es.exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname,loginip,login_time,seatnum,es.create_time,es.create_user,photo,test_flag ");
		sql.append(",ec.course_id,rc.course_name,ec.score,DATE_FORMAT(ec.submit_time,'%Y-%m-%d %H:%i:%s') submit_time,ec.submit_flag,ec.right_count,ec.wrong_count,ec.end_time, e.exam_name ");
		sql.append("FROM exam_course ec inner join exam_stu es on ec.stu_id=es.id ");
		sql.append("inner join exam e on e.id=es.exam_id ");
		sql.append("inner join res_course rc on rc.id=ec.course_id ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			sql.append(lp).append(" es.exam_id = ? ");
			argsList.add(searchParams.get("eid"));
			lp = " and ";
//			if(null != searchParams.get("examname") && !"".equals((String)searchParams.get("examname"))){
//				sql.append(lp).append(" e.exam_name like ? ");
//				argsList.add("%" + searchParams.get("examname") + "%");
//				lp = " and ";
//			}
			if(null != searchParams.get("truename") && !"".equals((String)searchParams.get("truename"))){
				sql.append(lp).append(" truename like ? ");
				argsList.add("%" + searchParams.get("truename") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("idcard") && !"".equals((String)searchParams.get("idcard"))){
				sql.append(lp).append(" idcard like ? ");
				argsList.add("%" + searchParams.get("idcard") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("pageItem") && !"".equals((String)searchParams.get("pageItem"))){
				if(null != page) page.setPerItem(CommonTools.parseInt((String)searchParams.get("pageItem")));
			}
		}else{
			return null;
		}
		sql.append(lp).append(" es.business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		Object[] args = argsList.toArray();
		List<ExamStu> list = dao.query(pageSQL(sql.toString(),"order by ec.course_id, ifnull(ec.score,0) desc",page),args,new RowMapper(){
			@Override
			public ExamStu mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamStu obj = new ExamStu();
				obj.setCreateTime(rs.getString("create_time")); 
				obj.setCreateUser(rs.getInt("create_user")); 
				obj.setExamId(rs.getInt("exam_id")); 
				obj.setExamRoomid(rs.getString("exam_roomid")); 
				obj.setExamRoomname(rs.getString("exam_roomname")); 
				obj.setExamSiteid(rs.getString("exam_siteid")); 
				obj.setExamSitename(rs.getString("exam_sitename")); 
				obj.setId(rs.getInt("id")); 
				obj.setIdcard(rs.getString("idcard")); 
				obj.setIdentitycode(rs.getString("identitycode")); 
				obj.setLoginTime(rs.getString("login_time")); 
				obj.setLoginip(rs.getString("loginip")); 
				obj.setPhoto(rs.getString("photo")); 
				obj.setSeatnum(rs.getString("seatnum")); 
				obj.setTruename(rs.getString("truename")); 
				obj.setTestFlag(rs.getString("test_flag"));
				ExamCourse examCourse = new ExamCourse();
				examCourse.setCourseId(rs.getInt("course_id"));
				examCourse.setCourseName(rs.getString("course_name"));
				examCourse.setSubmitFlag(rs.getString("submit_flag"));
				examCourse.setEndTime(rs.getString("end_time"));
				if("1".equals(examCourse.getSubmitFlag())){
					examCourse.setSubmitTime(rs.getString("submit_time"));
					examCourse.setScore(rs.getString("score"));
				}
				obj.setExamCourse(examCourse);
				return obj;
			}
			
		});
		if(null != page) page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}

	public boolean reexamine(Integer uid,Integer examId, Integer cid) {
		cache.removeExamPaper(uid, cid);
		dao.update("update exam_course set score=0,submit_flag='0',end_time='',submit_time=null where stu_id=? and course_id=? and exam_id=?" , new Object[]{uid, cid, examId});
		//
		dao.update("update exam_stu set cost_flag='0' where id=?", new Object[]{uid});
		return true;
	}

	public boolean delayed(Integer uid, Integer examId, Integer cid, Integer minTime) {
		int n = dao.update("update exam_course set end_time=DATE_ADD(DATE_FORMAT(end_time,'%Y-%m-%d %H:%i:%s'),INTERVAL ? MINUTE) where stu_id=? and course_id=? and exam_id=? and ifnull(end_time,'') <> ''" , new Object[]{minTime,uid, cid, examId});
		return n > 0;
	}

	public ExamRankBase findExamRankBase(String eid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) sumCount, count(login_time) loginCount from exam_stu es ");
		sql.append("where es.exam_id=? and es.business_id=? group by es.exam_id");
		List<ExamRankBase> list = dao.query(sql.toString(), new Object[]{eid,SessionHelper.getInstance().getUser().getBusinessId()}, new RowMapper<ExamRankBase>(){
			@Override
			public ExamRankBase mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamRankBase erb = new ExamRankBase();
				erb.setSumCount(rs.getInt("sumCount"));
				erb.setLoginCount(rs.getInt("loginCount"));
				erb.setNoLoginCount(erb.getSumCount() - erb.getLoginCount());
				return erb;
			}
		});
		if(list.size() > 0) return list.get(0);
		return null;
	}

	public List<ExamRankCourse> findExamRankCourseList(String eid, ExamRankBase baseBean) {
		List<ExamRankCourse> list = new ArrayList<ExamRankCourse>();
		Exam exam = examServices.findForObject(Integer.parseInt(eid));
		List<ResCourseBean> selCourseArr = exam.getSelCourseArr();
		
		for(ResCourseBean c: selCourseArr){
			ExamRankCourse ercBean = findExamRankCourse(eid, c, baseBean);
			list.add(ercBean);
		}
		return list;
	}

	private ExamRankCourse findExamRankCourse(String eid, ResCourseBean c, ExamRankBase baseBean) {
		int loginCount = 0;
		if(null != baseBean) loginCount = baseBean.getLoginCount();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(ec.end_time) examingCount, count(if(ec.submit_flag='1',ec.submit_flag,null)) submitCount ");
		sql.append(", (count(if(ec.score>=e.pass_score ,ec.score,null)) / ? * 100) passRate,max(ec.score) maxScore,");
		sql.append("(select GROUP_CONCAT(t2.truename) truename from exam_course t1 ");
		sql.append("   inner join exam_stu t2 on t1.exam_id=t2.exam_id and t1.stu_id=t2.id ");
		sql.append(" where t1.score = max(ec.score) group by t2.id ");
		sql.append(") maxScoreUserInfo ");
		sql.append("from exam_course ec inner join exam e on e.id=ec.exam_id ");
		sql.append("where ec.exam_id=? and ec.course_id=? group by ec.exam_id,ec.course_id");
		  
		List<ExamRankCourse> list = dao.query(sql.toString(), new Object[]{loginCount, eid,c.getId()}, new RowMapper<ExamRankCourse>(){
			@Override
			public ExamRankCourse mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamRankCourse erb = new ExamRankCourse();
				erb.setExamingCount(rs.getInt("examingCount"));
				erb.setSubmitCount(rs.getInt("submitCount"));
				erb.setPassRate(rs.getString("passRate"));
				erb.setMaxScore(rs.getString("maxScore"));
				erb.setMaxScoreUserInfo(rs.getString("maxScoreUserInfo"));
				return erb;
			}
		});
		ExamRankCourse erc = null ;
		if(list.size() > 0) {
			erc = list.get(0);
			erc.setCid(c.getId());
			
			ResCourse rc = resServices.findForObject(c.getId());
			erc.setCourseName(rc.getCourseName());
			return erc;
		}else{
			erc = new ExamRankCourse();
			erc.setCid(c.getId());
			ResCourse rc = resServices.findForObject(c.getId());
			erc.setCourseName(rc.getCourseName());
		}
		return erc;
	}

}
