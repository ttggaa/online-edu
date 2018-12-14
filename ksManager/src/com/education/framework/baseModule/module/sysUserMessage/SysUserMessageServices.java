package com.education.framework.baseModule.module.sysUserMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.baseModule.domain.SysUserMessage;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;

@Service
public class SysUserMessageServices extends BaseServices implements IDao<SysUserMessage>{

	@Override
	public List<SysUserMessage> find(SearchParams searchParams, Page page) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,msg_type,msg_content,user_id,read_flag,DATE_FORMAT(create_time, '%Y-%m-%d') create_time,create_user,url FROM sys_user_message where user_id=? ");
		String lp = " and ";
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(sessionUser.getId());
		if(null != searchParams){
			if(null != searchParams.get("msgContent") && !"".equals((String)searchParams.get("msgContent"))){
				sql.append(lp).append(" msg_content like ? ");
				argsList.add("%" + searchParams.get("msgContent") + "%");
				lp = " and ";
			}
		} 
		Object[] args = argsList.toArray();
		List<SysUserMessage> list = dao.query(pageSQL(sql.toString(),"order by create_time desc",page),args,new SysUserMessageRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysUserMessage> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,msg_type,msg_content,user_id,read_flag,create_time,create_user,url FROM sys_user_message");
		
		List<SysUserMessage> list = dao.query(sql.toString(),new SysUserMessageRowmapper());
		return list;
	}

	@Override
	public int save(SysUserMessage obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_user_message ( "); 
		 sql.append("id,msg_type,msg_content,user_id,read_flag "); 
		 sql.append(",create_time,url ");  
		 sql.append(" ) values(?,?,?,?,?,now(),?) "); 
		 Object[] args = {obj.getId(),obj.getMsgType(),obj.getMsgContent(),obj.getUserId(),obj.getReadFlag(),obj.getUrl()
		  };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysUserMessage findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,msg_type,msg_content,user_id,read_flag,create_time,create_user,url FROM sys_user_message ");
		sql.append(" where id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysUserMessageRowmapper());
	}

	@Override
	public void update(SysUserMessage obj) {
		 
 StringBuffer sql = new StringBuffer(); 
 sql.append("update sys_user_message "); 
 sql.append("set  "); 
 sql.append("msg_type=?,msg_content=?,user_id=?,read_flag=?,url=? where id=?  "); 
 Object[] args = {obj.getMsgType(),obj.getMsgContent(),obj.getUserId(),obj.getReadFlag(),obj.getUrl(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from sys_user_message where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysUserMessageRowmapper implements RowMapper<SysUserMessage> {
		@Override
		public SysUserMessage mapRow(ResultSet rs, int arg1) throws SQLException {
			SysUserMessage obj = new SysUserMessage();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setId(rs.getInt("id")); 
			obj.setMsgContent(rs.getString("msg_content")); 
			obj.setMsgType(rs.getString("msg_type")); 
			obj.setReadFlag(rs.getString("read_flag")); 
			obj.setUserId(rs.getInt("user_id")); 
			obj.setUrl(rs.getString("url"));
			return obj;
		}
	}

	public void save(Integer userId, String content, String msgType,String url, String priDomain,String flowCode) {
		//查询未读状态消息是否已经存在，如果在则不新增
		int n = dao.queryForInt("select count(1) from sys_user_message where msg_type=? and user_id=? and pri_domain=? and read_flag='0'" , new Object[]{msgType,userId,priDomain});
		if(n > 0){
			//删除
			dao.update("update sys_user_message set create_time=now() where msg_type=? and user_id=? and pri_domain=? and read_flag='0'" , new Object[]{msgType,userId,priDomain});
		}else{
			SysUser sessionUser = SessionHelper.getInstance().getUser();
			 StringBuffer sql = new StringBuffer(); 
			 sql.append("insert into sys_user_message ( "); 
			 sql.append("msg_type,msg_content,user_id,create_time,create_user,url,pri_domain,flow_code "); 
			 sql.append(" ) values(?,?,?,now(),?,?,?,?) "); 
			 Object[] args = {msgType,content,userId, sessionUser.getId(),url,priDomain, flowCode};
			 dao.update(sql.toString(), args);
		}
	}

	public List<SysUserMessage> findForSessionUser() {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,msg_type,msg_content,user_id,read_flag,create_time,create_user,url FROM sys_user_message ");
		sql.append(" where user_id=? and read_flag='0' order by create_time desc limit 0,5");
		
		Object[] args = {sessionUser.getId()};
		return dao.query(sql.toString(),args,new SysUserMessageRowmapper());
	}

	public void updateReadFlag(Integer id) {
		dao.update("update sys_user_message set read_flag='1' where id=?" , new Object[]{id});
	}

	/**
	 * 按priDomain删除未读消息
	 * @param priDomain
	 */
	public void deleteForPriDomain(String msgType, String priDomain) {
		dao.update("delete from sys_user_message where msg_type=? and pri_domain=? and read_flag='0'" , new Object[]{msgType,priDomain});
	}
	
	/**
	 * 按priDomain删除未读消息
	 * @param priDomain
	 */
	public void deleteForPriDomainArr(String msgType, String priDomain) {
		dao.update("delete from sys_user_message where msg_type=? and pri_domain in(" + priDomain + ") and read_flag='0'" , new Object[]{msgType});
	}
}
