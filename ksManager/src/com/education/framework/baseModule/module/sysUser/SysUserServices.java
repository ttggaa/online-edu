package com.education.framework.baseModule.module.sysUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.Md5Util;

@Service
public class SysUserServices extends BaseServices implements IDao<SysUser>{

	@Override
	public List<SysUser> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,loginname,passwd,truename,gender,org_id,telephone,state,email,remark,audit_flag,audit_date,wechat_openid,entry_time,leave_time,create_time,create_user,update_time,update_user,ext1,ext2,ext3,getRoles(id) roles,business_id FROM sys_user");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("loginname") && !"".equals((String)searchParams.get("loginname"))){
				sql.append(lp).append(" loginname like ? ");
				argsList.add("%" + searchParams.get("loginname") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("truename") && !"".equals((String)searchParams.get("truename"))){
				sql.append(lp).append(" truename like ? ");
				argsList.add("%" + searchParams.get("truename") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("orgId") && !"".equals((String)searchParams.get("orgId"))){
				sql.append(lp).append(" org_id = ? ");
				argsList.add(searchParams.get("orgId"));
				lp = " and ";
			}
			if(null != searchParams.get("roleId") && !"".equals((String)searchParams.get("roleId"))){
				sql.append(lp).append(" id in (select distinct ur.user_id from sys_user_role ur where ur.role_id=?) ");
				argsList.add(searchParams.get("roleId"));
				lp = " and ";
			}
		}
		
		SysUser sysUser = SessionHelper.getInstance().getUser();
		if(null != sysUser.getBusinessId() && sysUser.getBusinessId().intValue() > 0){
			sql.append(lp).append(" business_id = ? ");
			argsList.add(sysUser.getBusinessId());
			lp = " and ";
		}
		Object[] args = argsList.toArray();
		List<SysUser> list = dao.query(pageSQL(sql.toString(),page),args,new SysUserRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysUser> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,loginname,passwd,truename,gender,org_id,telephone,state,email,remark,audit_flag,audit_date,wechat_openid,entry_time,leave_time,create_time,create_user,update_time,update_user,ext1,ext2,ext3,getRoles(id) roles,business_id FROM sys_user");
		
		List<SysUser> list = dao.query(sql.toString(),new SysUserRowmapper());
		return list;
	}

	public List<SysUser> findByRoles(String roleIds) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT u.id,u.loginname,u.truename, u.telephone FROM sys_user u ");
		sql.append("inner join sys_user_role ur on u.id=ur.user_id ");
		sql.append("where u.state='1' and role_id in(").append(roleIds).append(")");
		
		List<SysUser> list = dao.query(sql.toString(),new RowMapper<SysUser>(){
			@Override
			public SysUser mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser obj = new SysUser();
				obj.setId(rs.getInt("id")); 
				obj.setLoginname(rs.getString("loginname") == null ? "":rs.getString("loginname")); 
				obj.setTruename(rs.getString("truename") == null ? "":rs.getString("truename")); 
				obj.setTelephone(rs.getString("telephone") == null ? "":rs.getString("telephone"));
				return obj;
			}
		});
		return list;
	}
	@Override
	public int save(SysUser obj) {
		 //默认值设定
		 obj.setAuditFlag("1");
		 if(null == obj.getState()) obj.setState("0");
		 SysUser sysUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_user ( "); 
		 sql.append("loginname,passwd,truename,gender "); 
		 sql.append(",org_id,telephone,state,email,remark "); 
		 sql.append(",audit_flag,audit_date,wechat_openid,entry_time,leave_time "); 
		 sql.append(",create_time,create_user,ext1,ext2,ext3,business_id ");  
		 sql.append(" ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "); 
		 Object[] args = {obj.getLoginname(),Md5Util.MD5Encode(obj.getPasswd(),"utf-8"),obj.getTruename(),obj.getGender(),obj.getOrgId() 
		 ,obj.getTelephone(),obj.getState(),obj.getEmail(),obj.getRemark(),obj.getAuditFlag(),obj.getAuditDate() 
		 ,obj.getWechatOpenid(),obj.getEntryTime(),obj.getLeaveTime(),obj.getCreateTime(),obj.getCreateUser(),obj.getExt1() 
		 ,obj.getExt2(),obj.getExt3(),sysUser.getBusinessId()};
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysUser findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,loginname,passwd,truename,gender,org_id,telephone,state,email,remark,audit_flag,audit_date,wechat_openid,entry_time,leave_time,create_time,create_user,update_time,update_user,ext1,ext2,ext3,getRoles(id) roles,business_id FROM sys_user ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysUserRowmapper());
	}

	@Override
	public void update(SysUser obj) {
		 if(null == obj.getState()) obj.setState("0");
		 SysUser sysUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_user "); 
		 sql.append("set  "); 
		 sql.append("loginname=?,truename=?,gender=?,org_id=? "); 
		 sql.append(",telephone=?,state=?,email=?,remark=? "); 
		 sql.append(",wechat_openid=?,entry_time=?,leave_time=?,update_user=? "); 
		 sql.append(",ext1=?,ext2=?,ext3=?,org_id=?,business_id=? where id=?  "); 
		 Object[] args = {obj.getLoginname(),obj.getTruename(),obj.getGender(),obj.getOrgId(),obj.getTelephone() 
		 ,obj.getState(),obj.getEmail(),obj.getRemark(),obj.getWechatOpenid() 
		 ,obj.getEntryTime(),obj.getLeaveTime(),obj.getUpdateUser(),obj.getExt1(),obj.getExt2(),obj.getExt3() 
		 ,obj.getOrgId(),sysUser.getBusinessId(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		dao.update("delete from sys_user_role where user_id=?", new Object[]{id});
		
		String sql = "delete from sys_user where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String loginname) {
		SysUser sysUser = SessionHelper.getInstance().getUser();
		return dao.queryForInt("select count(1) from sys_user where business_id=? and loginname=?" , new Object[]{sysUser.getBusinessId(),loginname}) > 0;
	}
	
	public boolean findIsExist(int uid, String loginname) {
		SysUser sysUser = SessionHelper.getInstance().getUser();
		return dao.queryForInt("select count(1) from sys_user where business_id=? and id<>? and loginname=?" , new Object[]{sysUser.getBusinessId(),uid,loginname}) > 0;
	}
	
	private class SysUserRowmapper implements RowMapper<SysUser> {
		@Override
		public SysUser mapRow(ResultSet rs, int arg1) throws SQLException {
			SysUser obj = new SysUser();
			obj.setAuditDate(rs.getString("audit_date")); 
			obj.setAuditFlag(rs.getString("audit_flag")); 
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setEmail(rs.getString("email")); 
			obj.setEntryTime(rs.getDate("entry_time")); 
			obj.setExt1(rs.getString("ext1")); 
			obj.setExt2(rs.getString("ext2")); 
			obj.setExt3(rs.getInt("ext3")); 
			obj.setGender(rs.getString("gender")); 
			obj.setId(rs.getInt("id")); 
			obj.setLeaveTime(rs.getString("leave_time")); 
			obj.setLoginname(rs.getString("loginname")); 
			obj.setOrgId(rs.getInt("org_id")); 
			obj.setPasswd(rs.getString("passwd")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setState(rs.getString("state")); 
			obj.setTelephone(rs.getString("telephone")); 
			obj.setTruename(rs.getString("truename")); 
			obj.setUpdateTime(rs.getString("update_time")); 
			obj.setUpdateUser(rs.getInt("update_user")); 
			obj.setWechatOpenid(rs.getString("wechat_openid")); 
			obj.setRoles(rs.getString("roles"));
			obj.setBusinessId(rs.getInt("business_id"));
			return obj;
		}
	}
	
	public SysUser findUserByLoginName(String loginname) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,loginname,passwd,truename,gender,org_id,telephone,state,email,remark,audit_flag,audit_date,wechat_openid,entry_time,leave_time,create_time,create_user,update_time,update_user,ext1,ext2,ext3,getRoles(id) roles,business_id FROM sys_user ");
		sql.append("where loginname=? and state='1' and business_id=?");
		
		Object[] args = {loginname ,ApplicationHelper.getInstance().getBusiness().getId()};
		List<SysUser> list = dao.query(sql.toString(),args,new SysUserRowmapper());
		SysUser user = null;
		if(null != list && list.size() > 0){
			user = list.get(0);
		}
		return user;
	}

	public boolean checkOldPasswd(Integer id, String oldpasswd) {
		String sql = "select count(1) from sys_user where id=? and passwd = ?";
		int r = dao.queryForInt(sql,new Object[]{id, Md5Util.MD5Encode(oldpasswd)});
		
		return r > 0 ? true : false;
	}

	public void updatePasswd(Integer id, String passwd) {
		String sql = "update sys_user set passwd = ? where id=?";
		dao.update(sql, new Object[]{Md5Util.MD5Encode(passwd),id});
	}

	public void resetPwd(Integer id) {
		String sql = "update sys_user set passwd = ? where id=?";
		dao.update(sql, new Object[]{Md5Util.MD5Encode("123456"),id});
	}

	public void updatePwd(String loginname, String passwd) {
		String sql = "update sys_user set passwd = ? where loginname=?";
		dao.update(sql, new Object[]{Md5Util.MD5Encode(passwd),loginname});
	}

	public void updateUserRole(int uid, String[] roleIds) {
		SysUser user = SessionHelper.getInstance().getUser();
		
		dao.update("delete from sys_user_role where user_id = ?", new Object[]{uid});
		
		List<SysRole> roleList = SessionHelper.getInstance().getUser().getRoleList();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into sys_user_role(user_id,role_id,create_time,create_user) values(?,?,now(),?)");
		if(null != roleList){
			for(SysRole role : roleList){
				Object[] args = {uid, role.getId(), user.getId()};
				dao.update(sql.toString(), args);
			}
		}
	}
	
	/**
	 * 按funccode权限代码，查询用户列表
	 */
	public List<SysUser> findUserListByFunccode(String funcCode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT distinct u.id,u.loginname,u.truename FROM sys_user u ");
		sql.append("left join sys_user_role ur on u.id=ur.user_id ");
		sql.append("left join sys_role_func f on f.role_id=ur.role_id ");
		sql.append("where f.func_code=? and u.state='1'");
		List<SysUser> list = dao.query(sql.toString(),new Object[]{funcCode},new RowMapper<SysUser>(){
			@Override
			public SysUser mapRow(ResultSet rs, int arg1) throws SQLException {
				SysUser obj = new SysUser();
				obj.setId(rs.getInt("id")); 
				obj.setLoginname(rs.getString("loginname"));
				obj.setTruename(rs.getString("truename"));
				return obj;
			}
		});
		return list;
	}
	
	public Map<Integer,SysUser> findUserMapByFunccode(String funcCode) {
		List<SysUser> list = findUserListByFunccode(funcCode);
		Map<Integer,SysUser> map = new HashMap<Integer,SysUser>();
		for(SysUser user : list){
			map.put(user.getId(), user);
		}
		return map;
	}

}
