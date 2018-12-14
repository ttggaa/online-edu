package com.education.module.resCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.ResCourse;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;

@Service
public class ResCourseServices extends BaseServices implements IDao<ResCourse>{

	@Override
	public List<ResCourse> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,course_name,course_code,exam_sum_time,create_time,create_user,indexno,business_id FROM res_course");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("courseName") && !"".equals((String)searchParams.get("courseName"))){
				sql.append(lp).append(" course_name like ? ");
				argsList.add("%" + searchParams.get("courseName") + "%");
				lp = " and ";
			}
		}
		sql.append(lp).append(" business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		
		Object[] args = argsList.toArray();
		List<ResCourse> list = dao.query(pageSQL(sql.toString(),page),args,new ResCourseRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<ResCourse> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,course_name,course_code,exam_sum_time,create_time,create_user,indexno,business_id FROM res_course ");
		sql.append("where business_id=?");
		List<ResCourse> list = dao.query(sql.toString(),new Object[]{SessionHelper.getInstance().getUser().getBusinessId()},new ResCourseRowmapper());
		return list;
	}
	
	public List<ResCourse> fillSelCourse(List<ResCourse> list, String[] selCourseArr) {
		for(ResCourse c : list){
			if(null != selCourseArr){
				for(String selCourseId : selCourseArr){
					if(selCourseId.equals(String.valueOf(c.getId()))){
						c.setSelCourseFlag("1");
						break;
					}
				}
			}
		}
		return list;
	}
	
	public List<ResCourse> findAll() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,course_name,course_code,exam_sum_time,create_time,create_user,indexno,business_id FROM res_course");
		
		List<ResCourse> list = dao.query(sql.toString(),new ResCourseRowmapper());
		return list;
	}

	@Override
	public int save(ResCourse obj) {
		 SysUser sysUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into res_course ( "); 
		 sql.append("course_name,course_code,exam_sum_time,create_time,create_user "); 
		 sql.append(",indexno,business_id ");  
		 sql.append(" ) values(?,?,?,now(),?,?,?) "); 
		 Object[] args = {obj.getCourseName(),obj.getCourseCode(),obj.getExamSumTime(),sysUser.getId(),obj.getIndexno() 
		 ,sysUser.getBusinessId() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public ResCourse findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,course_name,course_code,exam_sum_time,create_time,create_user,indexno,business_id FROM res_course ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new ResCourseRowmapper());
	}

	@Override
	public void update(ResCourse obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update res_course "); 
		 sql.append("set  "); 
		 sql.append("course_name=?,course_code=?,exam_sum_time=?,indexno=? where id=?  "); 
		 Object[] args = {obj.getCourseName(),obj.getCourseCode(),obj.getExamSumTime(),obj.getIndexno(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from res_course where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class ResCourseRowmapper implements RowMapper<ResCourse> {
		@Override
		public ResCourse mapRow(ResultSet rs, int arg1) throws SQLException {
			ResCourse obj = new ResCourse();
			obj.setBusinessId(rs.getInt("business_id")); 
			obj.setCourseCode(rs.getString("course_code")); 
			obj.setCourseName(rs.getString("course_name")); 
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setExamSumTime(rs.getString("exam_sum_time")); 
			obj.setId(rs.getInt("id")); 
			obj.setIndexno(rs.getInt("indexno")); 
			return obj;
		}
	}

}
