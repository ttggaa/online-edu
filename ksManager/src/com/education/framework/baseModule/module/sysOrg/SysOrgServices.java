package com.education.framework.baseModule.module.sysOrg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.SysOrg;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class SysOrgServices extends BaseServices implements IDao<SysOrg>{

	@Override
	public List<SysOrg> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,org_name,remark,create_time,create_user,user_id,getUsers(user_id) username,pid,getOrgName(pid) parentOrgName FROM sys_org");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("orgName") && !"".equals((String)searchParams.get("orgName"))){
				sql.append(lp).append(" org_name like ? ");
				argsList.add("%" + searchParams.get("orgName") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("pid") && !"".equals((String)searchParams.get("pid"))){
				sql.append(lp).append(" pid = ? ");
				argsList.add(searchParams.get("pid"));
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<SysOrg> list = dao.query(pageSQL(sql.toString(),"order by pid,org_name",page),args,new SysOrgRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysOrg> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,org_name,remark,create_time,create_user,user_id,getUsers(user_id) username,pid,getOrgName(pid) parentOrgName FROM sys_org");
		sql.append(" order by org_name");
		List<SysOrg> list = dao.query(sql.toString(),new SysOrgRowmapper());
		return list;
	}
	
	public List<SysOrg> findParentOrgList(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,org_name,remark,create_time,create_user,user_id,getUsers(user_id) username,pid,getOrgName(pid) parentOrgName FROM sys_org");
		sql.append(" where pid=0 ");
		if(id > 0){
			sql.append(" and id<>").append(id);
		}
		sql.append(" order by org_name");
		List<SysOrg> list = dao.query(sql.toString(),new SysOrgRowmapper());
		return list;
	}
	
	public List<SysOrg> findForRecursive() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,org_name,remark,create_time,create_user,user_id,getUsers(user_id) username,pid,getOrgName(pid) parentOrgName FROM sys_org where pid <>0 ");
		sql.append(" order by org_name");
		List<SysOrg> list = dao.query(sql.toString(),new SysOrgRowmapper());
		
//		List<SysOrg> retList = new ArrayList<SysOrg>();
//		for(SysOrg o : list){
//			if(o.getPid()==0){
//				retList.add(o);
//				recursive(list,o);
//			}
//		}
		return list;
	}
	
	private void recursive(List<SysOrg> list, SysOrg mParam) {
		for(SysOrg o : list){
			if(mParam.getId() == o.getPid()){
				if(null == mParam.getSubList()){
					mParam.setSubList(new ArrayList<SysOrg>());
				}
				mParam.getSubList().add(o);
				recursive(list,o);
			}
		}
	}

	@Override
	public int save(SysOrg obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_org ( "); 
		 sql.append("org_name,remark,create_time,create_user,user_id,pid ");  
		 sql.append(" ) values(?,?,now(),?,?,?) "); 
		 Object[] args = {obj.getOrgName(),obj.getRemark(),obj.getCreateUser(),obj.getUserId(),obj.getPid() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysOrg findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,org_name,remark,create_time,create_user,user_id,getUsers(user_id) username,pid,getOrgName(pid) parentOrgName FROM sys_org ");
		sql.append(" where id=? ");
		try{
			Object[] args = {id};
			return dao.queryForObject(sql.toString(),args,new SysOrgRowmapper());
		}catch(Exception ex){
			
		}
		return new SysOrg();
	}

	@Override
	public void update(SysOrg obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_org "); 
		 sql.append("set  "); 
		 sql.append("org_name=?,remark=?,user_id=?,pid=? where id=?  "); 
		 Object[] args = {obj.getOrgName(),obj.getRemark(),obj.getUserId(),obj.getPid(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from sys_org where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysOrgRowmapper implements RowMapper<SysOrg> {
		@Override
		public SysOrg mapRow(ResultSet rs, int arg1) throws SQLException {
			SysOrg obj = new SysOrg();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setId(rs.getInt("id")); 
			obj.setOrgName(rs.getString("org_name")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setUserId(rs.getString("user_id"));
			obj.setUsernameView(rs.getString("username"));
			obj.setPid(rs.getInt("pid"));
			obj.setParentOrgName(rs.getString("parentOrgName"));
			return obj;
		}
	}

}
