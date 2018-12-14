package com.education.module.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.Type;
import com.education.framework.base.BaseServices;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class TypeServices extends BaseServices implements IDao<Type>{

	@Override
	public List<Type> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT typeid,typename,type_code,typedesc,typesort FROM type");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("typename") && !"".equals((String)searchParams.get("typename"))){
				sql.append(lp).append(" typename like ? ");
				argsList.add("%" + searchParams.get("typename") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<Type> list = dao.query(pageSQL(sql.toString(),page),args,new TypeRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<Type> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT typeid,typename,type_code,typedesc,typesort FROM type");
		
		List<Type> list = dao.query(sql.toString(),new TypeRowmapper());
		return list;
	}

	@Override
	public int save(Type obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("insert into type ( "); 
 sql.append("typeid,typename,type_code,typedesc,typesort ");  
 sql.append(" ) values(?,?,?,?,?) "); 
 Object[] args = {obj.getTypeid(),obj.getTypename(),obj.getTypeCode(),obj.getTypedesc(),obj.getTypesort() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public Type findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT typeid,typename,type_code,typedesc,typesort FROM type ");
		sql.append(" where typeid=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new TypeRowmapper());
	}

	@Override
	public void update(Type obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("update type "); 
 sql.append("set  "); 
 sql.append("typename=?,type_code=?,typedesc=?,typesort=? where typeid=?  "); 
 Object[] args = {obj.getTypename(),obj.getTypeCode(),obj.getTypedesc(),obj.getTypesort(),obj.getTypeid() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from type where typeid=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class TypeRowmapper implements RowMapper<Type> {
		@Override
		public Type mapRow(ResultSet rs, int arg1) throws SQLException {
			Type obj = new Type();
			obj.setTypeCode(rs.getString("type_code")); 
			obj.setTypedesc(rs.getString("typedesc")); 
			obj.setTypeid(rs.getInt("typeid")); 
			obj.setTypename(rs.getString("typename")); 
			obj.setTypesort(rs.getInt("typesort")); 

			return obj;
		}
	}
	
	public Map<String, Type> findByMap() {
		Map<String, Type> map = new HashMap<String,Type>();
		List<Type> list = find();
		for(Type t : list){
			map.put(t.getTypeCode(), t);
		}
		return map;
	}
	
	public List<com.edufe.module.entity.Type> findForEdu() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT typeid,typename,type_code,typedesc,typesort FROM type");
		
		List<com.edufe.module.entity.Type> list = dao.query(sql.toString(),new RowMapper<com.edufe.module.entity.Type>(){

			@Override
			public com.edufe.module.entity.Type mapRow(ResultSet rs, int arg1)
					throws SQLException {
				com.edufe.module.entity.Type obj = new com.edufe.module.entity.Type();
				obj.setTypeCode(rs.getString("type_code")); 
				obj.setTypedesc(rs.getString("typedesc")); 
				obj.setTypeid(rs.getInt("typeid")); 
				obj.setTypename(rs.getString("typename")); 
				obj.setTypesort(rs.getInt("typesort")); 
				return obj;
			}
			
		});
		return list;
	}

}
