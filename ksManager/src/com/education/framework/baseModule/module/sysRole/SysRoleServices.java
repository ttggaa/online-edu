package com.education.framework.baseModule.module.sysRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class SysRoleServices extends BaseServices implements IDao<SysRole>{

	@Override
	public List<SysRole> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,role_name,role_code,remark,create_time,create_user,update_time,update_user,getUsersByRole(id) usernames FROM sys_role");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("roleName") && !"".equals((String)searchParams.get("roleName"))){
				sql.append(lp).append(" role_name like ? ");
				argsList.add("%" + searchParams.get("roleName") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<SysRole> list = dao.query(pageSQL(sql.toString(),page),args,new SysRoleRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysRole> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,role_name,role_code,remark,create_time,create_user,update_time,update_user,getUsersByRole(id) usernames FROM sys_role");
		
		List<SysRole> list = dao.query(sql.toString(),new SysRoleRowmapper());
		return list;
	}
	
	public List<SysRole> findByUser(int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT r.id,r.role_name,role_code,r.remark,ur.user_id FROM sys_role r ");
		sql.append("left join sys_user_role ur on r.id=ur.role_id and ur.user_id=?");
		
		List<SysRole> list = dao.query(sql.toString(),new Object[]{userId},new RowMapper<SysRole>(){
			@Override
			public SysRole mapRow(ResultSet rs, int arg1) throws SQLException {
				SysRole obj = new SysRole();
				obj.setId(rs.getInt("id")); 
				obj.setRemark(rs.getString("remark")); 
				obj.setRoleName(rs.getString("role_name")); 
				obj.setSelect(rs.getString("user_id"));
				obj.setRoleCode(rs.getString("role_code"));
				obj.setSelect(rs.getInt("user_id") == 0?"":"1");
				return obj;
			}
		});
		return list;
	}
	
	@Override
	public int save(SysRole obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_role ( "); 
		 sql.append("role_name,remark,create_time,create_user,role_code ");  
		 sql.append(" ) values(?,?,now(),?,?) "); 
		 Object[] args = {obj.getRoleName(),obj.getRemark(),obj.getCreateUser(),obj.getRoleCode() };
		 
		 dao.update(sql.toString(), args);
		 int roleId = dao.queryForInt("SELECT LAST_INSERT_ID()"); 
		
		 if(null != obj.getMenuAuth()){
			 String mSQL = "insert into sys_role_menu(role_id,menu_id,create_time,create_user) values(?,?,now(),?)";
			 for(int m : obj.getMenuAuth()){
				 Object[] o = {roleId, m, obj.getCreateUser()};
				 dao.update(mSQL, o);
			 }
		 }
		 
		 if(null != obj.getMenuFunc()){
			 String mSQL = "insert into sys_role_func(role_id,func_code,create_time,create_user) values(?,?,now(),?)";
			 for(int m : obj.getMenuFunc()){
				 Object[] o = {roleId, m, obj.getCreateUser()};
				 dao.update(mSQL, o);
			 }
		 }
		 return roleId;
	}

	@Override
	public SysRole findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,role_name,remark,create_time,create_user,update_time,update_user,role_code,getUsersByRole(id) usernames FROM sys_role ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysRoleRowmapper());
	}

	@Override
	public void update(SysRole obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_role "); 
		 sql.append("set role_name=?,remark=?,update_user=?,role_code=? where id=?"); 
		 Object[] args = {obj.getRoleName(),obj.getRemark(),obj.getUpdateUser(),obj.getRoleCode(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
		 
		 
		 //
		 String delSql = "delete from sys_role_menu where role_id=?";
		 dao.update(delSql,new Object[]{obj.getId()});
		
		 String delSql2 = "delete from sys_role_func where role_id=?";
		 dao.update(delSql2,new Object[]{obj.getId()});
		 
		 if(null != obj.getMenuAuth()){
			 String mSQL = "insert into sys_role_menu(role_id,menu_id,create_time,create_user) values(?,?,now(),?)";
			 for(int m : obj.getMenuAuth()){
				 Object[] o = {obj.getId(), m, obj.getUpdateUser()};
				 dao.update(mSQL, o);
			 }
		 }
		 
		 if(null != obj.getMenuFunc()){
			 String mSQL = "insert into sys_role_func(role_id,func_code,create_time,create_user) values(?,?,now(),?)";
			 for(int m : obj.getMenuFunc()){
				 Object[] o = {obj.getId(), m, obj.getUpdateUser()};
				 dao.update(mSQL, o);
			 }
		 }
	}

	@Override
	public void delete(Integer id) {
		String delSql = "delete from sys_role_menu where role_id=?";
		dao.update(delSql,new Object[]{id});
		
		String delSql2 = "delete from sys_role_func where role_id=?";
		dao.update(delSql2,new Object[]{id});
		
		String sql = "delete from sys_role where id=?";
		dao.update(sql, new Object[]{id});
	}
	
	public boolean deleteCheck(Integer id){
		String findSql = "select count(1) from sys_user_role where role_id=?";
		int r1 = dao.queryForInt(findSql,new Object[]{id});
		
		if(r1 > 0){
			return false;
		}
		return true; //允许删除
	}
	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysRoleRowmapper implements RowMapper<SysRole> {
		@Override
		public SysRole mapRow(ResultSet rs, int arg1) throws SQLException {
			SysRole obj = new SysRole();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setId(rs.getInt("id")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setRoleName(rs.getString("role_name")); 
			obj.setUpdateTime(rs.getString("update_time")); 
			obj.setUpdateUser(rs.getInt("update_user")); 
			obj.setRoleCode(rs.getString("role_code"));
			obj.setRoleUsers(rs.getString("usernames"));
			return obj;
		}
	}

	public List<SysRole> findForUserId(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT r.id,r.role_name,r.remark,r.create_time,r.create_user,r.update_time,r.update_user, r.role_code,getUsersByRole(r.id) usernames FROM sys_role r ");
		sql.append("inner join sys_user_role ur on r.id=ur.role_id where ur.user_id=?");
		
		List<SysRole> list = dao.query(sql.toString(),new Object[]{id}, new SysRoleRowmapper());
		return list;
	}

	public void deleteRoleMenu(Integer roleId,Integer menuId) {
		String sql = "delete from sys_role_menu where role_id=? and menu_id=?";
		dao.update(sql, new Object[]{roleId,menuId});
	}

	public Map<Integer, Integer> findFuncMap(List<SysRole> roleList) {
		if(null == roleList) return new HashMap<Integer, Integer>();
		if(roleList.size() == 0) return new HashMap<Integer, Integer>();
		List<Object> argsList = new ArrayList<Object>();
		StringBuffer tjBuff = new StringBuffer();
		String lp = "";
		for(SysRole role : roleList){
			argsList.add(role.getId());
			tjBuff.append(lp).append("?");
			lp = ",";
		}
		String sql = "select func_code from sys_role_func where role_id in(" + tjBuff.toString() + ")";
		List<Integer> list = dao.query(sql, argsList.toArray(), new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt("func_code");
			}
		});
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Integer s : list){
			map.put(s, s);
		}
		return map;
	}
}
