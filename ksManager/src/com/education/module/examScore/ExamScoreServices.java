package com.education.module.examScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.ExamCourse;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;

@Service
public class ExamScoreServices extends BaseServices implements IDao<ExamCourse>{

	@Override
	public List<ExamCourse> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,stu_id,course_id,end_time,score,submit_time,create_time FROM exam_score");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("title") && !"".equals((String)searchParams.get("title"))){
				sql.append(lp).append(" title like ? ");
				argsList.add("%" + searchParams.get("title") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<ExamCourse> list = dao.query(pageSQL(sql.toString(),page),args,new ExamScoreRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<ExamCourse> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,stu_id,course_id,end_time,score,submit_time,create_time FROM exam_score");
		
		List<ExamCourse> list = dao.query(sql.toString(),new ExamScoreRowmapper());
		return list;
	}

	@Override
	public int save(ExamCourse obj) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
 StringBuffer sql = new StringBuffer(); 
 sql.append("insert into exam_score ( "); 
 sql.append("stu_id,course_id,end_time,score "); 
 sql.append(",submit_time,create_time,create_user ");  
 sql.append(" ) values(?,?,?,?,?,now(),?) "); 
 Object[] args = {obj.getId(),obj.getStuId(),obj.getCourseId(),obj.getEndTime(),obj.getScore(),obj.getSubmitTime() 
 ,sessionUser.getId() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public ExamCourse findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,stu_id,course_id,end_time,score,submit_time,create_time FROM exam_score ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new ExamScoreRowmapper());
	}

	@Override
	public void update(ExamCourse obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("update exam_score "); 
 sql.append("set  "); 
 sql.append("stu_id=?,course_id=?,end_time=?,score=?,submit_time=? where id=?  "); 
 Object[] args = {obj.getStuId(),obj.getCourseId(),obj.getEndTime(),obj.getScore(),obj.getSubmitTime(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from exam_score where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class ExamScoreRowmapper implements RowMapper<ExamCourse> {
		@Override
		public ExamCourse mapRow(ResultSet rs, int arg1) throws SQLException {
			ExamCourse obj = new ExamCourse();
			obj.setCourseId(rs.getInt("course_id")); 
obj.setCreateTime(rs.getString("create_time")); 
obj.setEndTime(rs.getString("end_time")); 
obj.setId(rs.getInt("id")); 
obj.setScore(rs.getString("score")); 
obj.setStuId(rs.getInt("stu_id")); 
obj.setSubmitTime(rs.getString("submit_time")); 

			return obj;
		}
	}

}
