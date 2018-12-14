package com.education.framework.baseModule.module.sysMenu;

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
import com.education.framework.baseModule.bean.SysMenuBean;
import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

@Service
public class SysMenuServices extends BaseServices implements IDao<SysMenu>{

	@Override
	public List<SysMenu> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,name,link_url,pid,remark,icon,order_index,DATE_FORMAT(create_time,'%Y-%m-%d') create_time,create_user,DATE_FORMAT(update_time,'%Y-%m-%d') update_time,update_user FROM sys_menu");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("name") && !"".equals((String)searchParams.get("name"))){
				sql.append(lp).append(" name like ? ");
				argsList.add("%" + searchParams.get("name") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("linkUrl") && !"".equals((String)searchParams.get("linkUrl"))){
				sql.append(lp).append(" link_url like ? ");
				argsList.add("%" + searchParams.get("linkUrl") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<SysMenu> list = dao.query(pageSQL(sql.toString()," order by CAST(order_index AS CHAR) asc",page),args,new SysMenuRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysMenu> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,name,link_url,pid,remark,icon,order_index,DATE_FORMAT(create_time,'%Y-%m-%d') create_time,create_user,DATE_FORMAT(update_time,'%Y-%m-%d') update_time,update_user FROM sys_menu");
		
		List<SysMenu> list = dao.query(sql.toString(),new SysMenuRowmapper());
		return list;
	}

	@Override
	public int save(SysMenu obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_menu ( "); 
		 sql.append("name,link_url,pid,remark "); 
		 sql.append(",icon,order_index,create_time,create_user ");  
		 sql.append(" ) values(?,?,?,?,?,?,now(),?) "); 
		 Object[] args = {obj.getName(),obj.getLinkUrl(),obj.getPid(),obj.getRemark(),obj.getIcon() 
		 ,obj.getOrderIndex(),obj.getCreateUser() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysMenu findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,name,link_url,pid,remark,icon,order_index,DATE_FORMAT(create_time,'%Y-%m-%d') create_time,create_user,DATE_FORMAT(update_time,'%Y-%m-%d') update_time,update_user FROM sys_menu ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysMenuRowmapper());
	}

	public List<SysMenu> find(List<SysRole> roleList) {
		if(null == roleList) return null;
		//权限查询逻辑占时未做，所有菜单全部读取
		
		//数据分级处理
		List<SysMenu> list = findMenuByRoles(roleList);
		
		List<SysMenu> retList = new ArrayList<SysMenu>();
		for(SysMenu menu : list){
			if(-1 == menu.getPid()){
				retList.add(menu);
				recursive(list,menu);
			}
		}
		
		return retList;
	}
	
	private void recursive(List<SysMenu> list, SysMenu mParam) {
		for(SysMenu menu : list){
			if(mParam.getId() == menu.getPid()){
				if(null == mParam.getChildList()){
					mParam.setChildList(new ArrayList<SysMenu>());
				}
				mParam.getChildList().add(menu);
				recursive(list,menu);
			}
		}
	}
	
	public List<String> findUserMenuLink(List<SysRole> roleList) {
		if(null == roleList) return new ArrayList<String>();
		if(roleList.size() == 0) return new ArrayList<String>();
		List<Object> argsList = new ArrayList<Object>();
		StringBuffer tjBuff = new StringBuffer();
		String lp = "";
		for(SysRole role : roleList){
			argsList.add(role.getId());
			tjBuff.append(lp).append("?");
			lp = ",";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.link_url FROM sys_menu m ");
		sql.append("inner join sys_role_menu s on s.menu_id=m.id and s.role_id in(").append(tjBuff.toString()).append(") ");
		sql.append("where m.link_url is not null");
		
		List<String> list = dao.query(sql.toString(),argsList.toArray(),new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("link_url");
			}
			
		});
		return list;
	}
	
	public List<SysMenu> findMenuByRoles(List<SysRole> roleList) {
		if(null == roleList) return new ArrayList<SysMenu>();
		if(roleList.size() == 0) return new ArrayList<SysMenu>();
		List<Object> argsList = new ArrayList<Object>();
		StringBuffer tjBuff = new StringBuffer();
		String lp = "";
		for(SysRole role : roleList){
			argsList.add(role.getId());
			tjBuff.append(lp).append("?");
			lp = ",";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT distinct m.id, m.name,m.link_url,m.remark,m.pid,m.icon,m.create_time,m.update_time,m.create_user,m.update_user,m.order_index FROM sys_menu m ");
		sql.append("inner join sys_role_menu s on s.menu_id=m.id and s.role_id in(").append(tjBuff.toString()).append(") ");
		sql.append("order by m.order_index");
		List<SysMenu> list = dao.query(sql.toString(),argsList.toArray(), new SysMenuRowmapper());
		return list;
	}
	
	public List<SysMenuBean> findMenu(int roleId) {
		final Map<Integer,Integer> roleFuncMap = new HashMap<Integer,Integer>();
		if(roleId > 0){
			String funcSql = "select func_code from sys_role_func where role_id=?";
			dao.query(funcSql,new Object[]{roleId}, new RowMapper(){
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					roleFuncMap.put(rs.getInt("func_code"), rs.getInt("func_code"));
					return null;
				}
				
			});
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.id, m.name,m.remark,m.pid,ifnull(s.role_id,0) role_id FROM sys_menu m ");
		sql.append("left join sys_role_menu s on s.menu_id=m.id and s.role_id =? ");
		sql.append("order by CAST(m.order_index AS CHAR) asc");
		List<SysMenuBean> list = dao.query(sql.toString(),new Object[]{roleId}, new RowMapper<SysMenuBean>(){
			@Override
			public SysMenuBean mapRow(ResultSet rs, int arg1) throws SQLException {
				SysMenuBean bean = new SysMenuBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setRemark(rs.getString("remark"));
				bean.setPid(rs.getInt("pid"));
				bean.setMenuAuth(rs.getInt("role_id")==0 ? "0" : "1");
				bean.setMenuFuncList(ApplicationHelper.getInstance().getFuncMap(bean.getId()),roleFuncMap);
				return bean;
			}
			
		});
		
		List<SysMenuBean> retList = new ArrayList<SysMenuBean>();
		for(SysMenuBean menu : list){
			if(-1 == menu.getPid()){
				retList.add(menu);
				recursiveBean(list,menu);
			}
		}
		return retList;
	}
	
	private void recursiveBean(List<SysMenuBean> list, SysMenuBean mParam) {
		for(SysMenuBean menu : list){
			if(mParam.getId() == menu.getPid()){
				if(null == mParam.getChildList()){
					mParam.setChildList(new ArrayList<SysMenuBean>());
				}
				mParam.getChildList().add(menu);
				recursiveBean(list,menu);
			}
		}
	}
	
	public List<SysMenu> findMenuNotRole(int roleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.id, m.name,m.link_url,m.remark,m.pid,m.icon,m.create_time,m.update_time,m.create_user,m.update_user,m.order_index FROM sys_menu m ");
		sql.append("inner join sys_role_menu s on s.menu_id=m.id and s.role_id <> ? ");
		sql.append("order by m.order_index");
		List<SysMenu> list = dao.query(sql.toString(),new Object[]{roleId}, new SysMenuRowmapper());
		return list;
	}
	
	@Override
	public void update(SysMenu obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_menu "); 
		 sql.append("set  "); 
		 sql.append("name=?,link_url=?,pid=?,remark=?,icon=? "); 
		 sql.append(",order_index=?,update_user=?,update_time=now() where id=?  "); 
		 Object[] args = {obj.getName(),obj.getLinkUrl(),obj.getPid(),obj.getRemark(),obj.getIcon(),obj.getOrderIndex() 
		 ,obj.getUpdateUser(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		dao.update("delete from sys_menu_func where menu_id=?", new Object[]{id});
		dao.update("delete from sys_role_menu where menu_id=?", new Object[]{id});
		
		String sql = "delete from sys_menu where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysMenuRowmapper implements RowMapper<SysMenu> {
		@Override
		public SysMenu mapRow(ResultSet rs, int arg1) throws SQLException {
			SysMenu obj = new SysMenu();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setIcon(rs.getString("icon")); 
			obj.setId(rs.getInt("id")); 
			obj.setLinkUrl(rs.getString("link_url")); 
			obj.setName(rs.getString("name")); 
			obj.setOrderIndex(rs.getInt("order_index")); 
			obj.setPid(rs.getInt("pid")); 
			obj.setRemark(rs.getString("remark")); 
			obj.setUpdateTime(rs.getString("update_time")); 
			obj.setUpdateUser(rs.getInt("update_user")); 
			return obj;
		}
	}

}
