package com.education.framework.baseModule.module.sysDictionary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class SysDictionaryServices extends BaseServices implements IDao<SysDictionary>{

	@Override
	public List<SysDictionary> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,field_id,field_name,code,label,remark,order_index,create_time,create_user,update_time,update_user,ext1,ext2,ext3 FROM sys_dictionary");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("fieldId") && !"".equals((String)searchParams.get("fieldId"))){
				sql.append(lp).append(" field_id = ? ");
				argsList.add(searchParams.get("fieldId"));
				lp = " and ";
			}
			if(null != searchParams.get("fieldName") && !"".equals((String)searchParams.get("fieldName"))){
				sql.append(lp).append(" field_name like ? ");
				argsList.add("%" + searchParams.get("fieldName") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("code") && !"".equals((String)searchParams.get("code"))){
				sql.append(lp).append(" code like ? ");
				argsList.add("%" + searchParams.get("code") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("label") && !"".equals((String)searchParams.get("label"))){
				sql.append(lp).append(" label like ? ");
				argsList.add("%" + searchParams.get("label") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<SysDictionary> list = dao.query(pageSQL(sql.toString(),"order by order_index",page),args,new SysDictionaryRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysDictionary> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,field_id,field_name,code,label,remark,order_index,create_time,create_user,update_time,update_user,ext1,ext2,ext3 FROM sys_dictionary");
		sql.append(" order by order_index");
		List<SysDictionary> list = dao.query(sql.toString(),new SysDictionaryRowmapper());
		return list;
	}

	public List<SysDictionary> findByFieldsList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT distinct field_id,field_name FROM sys_dictionary ");
		
		List<SysDictionary> list = dao.query(sql.toString(),new RowMapper<SysDictionary>(){
			@Override
			public SysDictionary mapRow(ResultSet rs, int arg1) throws SQLException {
				SysDictionary obj = new SysDictionary();
				obj.setFieldName(rs.getString("field_name")); 
				obj.setFieldId(rs.getString("field_id")); 
				return obj;
			}
			
		});
		return list;
	}
	
	@Override
	public int save(SysDictionary obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_dictionary ( "); 
		 sql.append("id,field_id,field_name,code,label "); 
		 sql.append(",remark,order_index,create_time,create_user ");  
		 sql.append(" ) values(?,?,?,?,?,?,?,now(),?) "); 
		 Object[] args = {obj.getId(),obj.getFieldId(),obj.getFieldName(),obj.getCode(),obj.getLabel(),obj.getRemark() 
		 ,obj.getOrderIndex(),obj.getCreateUser() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysDictionary findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,field_id,field_name,code,label,remark,order_index,create_time,create_user,update_time,update_user,ext1,ext2,ext3 FROM sys_dictionary ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysDictionaryRowmapper());
	}

	@Override
	public void update(SysDictionary obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_dictionary "); 
		 sql.append("set  "); 
		 sql.append("field_id=?,field_name=?,code=?,label=?,remark=? "); 
		 sql.append(",order_index=?,update_time=now() where id=?  "); 
		 Object[] args = {obj.getFieldId(),obj.getFieldName(),obj.getCode(),obj.getLabel(),obj.getRemark(),obj.getOrderIndex() 
		 ,obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from sys_dictionary where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysDictionaryRowmapper implements RowMapper<SysDictionary> {
		@Override
		public SysDictionary mapRow(ResultSet rs, int arg1) throws SQLException {
			SysDictionary obj = new SysDictionary();
			obj.setId(rs.getInt("id")); 
			obj.setUpdateTime(rs.getString("update_time")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setOrderIndex(rs.getInt("order_index")); 
			obj.setLabel(rs.getString("label")); 
			obj.setCode(rs.getString("code")); 
			obj.setFieldName(rs.getString("field_name")); 
			obj.setFieldId(rs.getString("field_id")); 
			obj.setCreateTime(rs.getString("create_time"));
			obj.setCreateUser(rs.getInt("create_user"));
			obj.setExt1(rs.getString("ext1"));
			obj.setExt2(rs.getString("ext2"));
			obj.setExt3(rs.getInt("ext3"));
			return obj;
		}
	}

	
	public boolean authDict(String token) {
		 
		StringBuffer sql2 = new StringBuffer();
		sql2.append("SELECT count(1) FROM sys_dictionary where fieldId='t'");
		
		int r = dao.queryForInt(sql2.toString());
		if(r <= 0){
			 StringBuffer sql = new StringBuffer(); 
			 sql.append("insert into sys_dictionary ( "); 
			 sql.append("field_id,field_name,code,label "); 
			 sql.append(",order_index,update_time ");  
			 sql.append(" ) values('t','t',?,'t','0',now()) "); 
			 dao.update(sql.toString(), new Object[]{token});
		}else{
			String sql = "update sys_dictionary set code=? where field_id='t'"; 
			dao.update(sql.toString(), new Object[]{token});
		}
		return true;
	}

}
