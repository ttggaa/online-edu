package com.education.module.quesSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.QuesSource;
import com.education.domain.extend.QuesSourceInfo;
import com.education.framework.base.BaseServices;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class QuesSourceServices extends BaseServices implements IDao<QuesSource>{

	@Override
	public List<QuesSource> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,source_name,remark,business_id,role,state FROM ques_source");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("sourceName") && !"".equals((String)searchParams.get("sourceName"))){
				sql.append(lp).append(" source_name like ? ");
				argsList.add("%" + searchParams.get("sourceName") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<QuesSource> list = dao.query(pageSQL(sql.toString(),page),args,new QuesSourceRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<QuesSource> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,source_name,remark,business_id,role,state FROM ques_source");
		
		List<QuesSource> list = dao.query(sql.toString(),new QuesSourceRowmapper());
		return list;
	}

	@Override
	public int save(QuesSource obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("insert into ques_source ( "); 
 sql.append("id,source_name,remark,business_id,role "); 
 sql.append(",state ");  
 sql.append(" ) values(?,?,?,?,?,?) "); 
 Object[] args = {obj.getId(),obj.getSourceName(),obj.getRemark(),obj.getBusinessId(),obj.getRole(),obj.getState() 
  };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public QuesSource findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,source_name,remark,business_id,role,state FROM ques_source ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new QuesSourceRowmapper());
	}

	@Override
	public void update(QuesSource obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("update ques_source "); 
 sql.append("set  "); 
 sql.append("source_name=?,remark=?,business_id=?,role=?,state=? where id=?  "); 
 Object[] args = {obj.getSourceName(),obj.getRemark(),obj.getBusinessId(),obj.getRole(),obj.getState(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from ques_source where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	public List<QuesSourceInfo> findQuesSourceAnalysis(Integer sourceId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.source_id,t.course_id,rc.course_name,t.type_code,t2.typename,count(1) as cnum  ");
		sql.append("from tk_examination t ");
		sql.append("inner join type t2 on t2.type_code=t.type_code ");
		sql.append("inner join res_course rc on rc.id = t.course_id ");
		sql.append("where t.source_id=? ");
		sql.append("group by t.source_id, t.course_id,t.type_code ");
		sql.append("order by t.source_id, t.course_id,t2.typesort ");
		List<QuesSourceInfo> list = dao.query(sql.toString(), new Object[]{sourceId}, new RowMapper<QuesSourceInfo>(){
			@Override
			public QuesSourceInfo mapRow(ResultSet rs, int arg1) throws SQLException {
				QuesSourceInfo q = new QuesSourceInfo();
				q.setSourceId(rs.getString("source_id"));
				q.setCourseId(rs.getString("course_id"));
				q.setCourseName(rs.getString("course_name"));
				q.setTypeCode(rs.getString("type_code"));
				q.setTypeCodeName(rs.getString("typename"));
				q.setNum(rs.getInt("cnum"));
				return q;
			}
		});
		
		return list;
	}
	
	private class QuesSourceRowmapper implements RowMapper<QuesSource> {
		@Override
		public QuesSource mapRow(ResultSet rs, int arg1) throws SQLException {
			QuesSource obj = new QuesSource();
			obj.setBusinessId(rs.getInt("business_id")); 
			obj.setId(rs.getInt("id")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setRole(rs.getString("role")); 
			obj.setSourceName(rs.getString("source_name")); 
			obj.setState(rs.getString("state")); 

			return obj;
		}
	}

}
