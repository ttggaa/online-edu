package com.education.framework.baseModule.module.sysLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysLog;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.CommonTools;

@Service
public class SysLogServices extends BaseServices implements IDao<SysLog>{

	/**
	 * 保存LOG
	 * @param logType
	 * @param logContent
	 * @param request
	 * @param msgKind 消息类型
	 * @return
	 */
	public boolean insertLog(String logType,String logContent,HttpServletRequest request,String msgKind){
		SysUser user = SessionHelper.getInstance().getUser();
		if(null == user) return false;
		SysLog obj = new SysLog();
		obj.setLogType(logType);
		obj.setLogContent(logContent);
		obj.setCreateUser(user.getId());
		obj.setAccessIp(CommonTools.getClientIP(request));
		obj.setMsgKind(msgKind);
		return save(obj) > 0 ? true : false;
	}
	
	public boolean insertLog(String logType,String logContent,HttpServletRequest request){
		SysUser user = SessionHelper.getInstance().getUser();
		if(null == user) return false;
		SysLog obj = new SysLog();
		obj.setLogType(logType);
		obj.setLogContent(logContent);
		obj.setCreateUser(user.getId());
		obj.setAccessIp(CommonTools.getClientIP(request));
		obj.setMsgKind("0");//系统
		return save(obj) > 0 ? true : false;
	}
	
	public boolean insertLog(String logType,String logContent,String logLevel,HttpServletRequest request){
		SysUser user = SessionHelper.getInstance().getUser();
		if(null == user) return false;
		SysLog obj = new SysLog();
		obj.setLogType(logType);
		obj.setLogContent(logContent);
		obj.setCreateUser(user.getId());
		obj.setAccessIp(CommonTools.getClientIP(request));
		obj.setMsgKind("0");//系统
		return save(obj) > 0 ? true : false;
	}
	
	@Override
	public List<SysLog> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT l.id,l.log_type,l.log_content,DATE_FORMAT(l.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(l.create_time,'%m-%d') create_time_view,l.create_user,l.access_ip,s.loginname,s.truename, (DATE_FORMAT(l.create_time,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d')) msgNewFlag ,msg_kind FROM sys_log l ");
		sql.append(" left join sys_user s on s.id=l.create_user ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("logType") && !"".equals((String)searchParams.get("logType"))){
				sql.append(lp).append(" l.log_type like ? ");
				argsList.add("%" + searchParams.get("logType") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("logContent") && !"".equals((String)searchParams.get("logContent"))){
				sql.append(lp).append(" l.log_content like ? ");
				argsList.add("%" + searchParams.get("logContent") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("loginname") && !"".equals((String)searchParams.get("loginname"))){
				sql.append(lp).append(" s.loginname like ? ");
				argsList.add("%" + searchParams.get("loginname") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("truename") && !"".equals((String)searchParams.get("truename"))){
				sql.append(lp).append(" s.truename like ? ");
				argsList.add("%" + searchParams.get("truename") + "%");
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		List<SysLog> list = dao.query(pageSQL(sql.toString(),"order by create_time desc",page),args,new SysLogRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<SysLog> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT l.id,l.log_type,l.log_content,DATE_FORMAT(l.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(l.create_time,'%m-%d') create_time_view,l.create_user,l.access_ip,s.loginname,s.truename, (DATE_FORMAT(l.create_time,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d')) msgNewFlag,msg_kind FROM sys_log l ");
		sql.append(" left join sys_user s on s.id=l.create_user ");
		
		List<SysLog> list = dao.query(sql.toString(),new SysLogRowmapper());
		return list;
	}

	@Override
	public int save(SysLog obj) {
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into sys_log ( "); 
		 sql.append("log_type,log_content,create_time,access_ip,create_user,msg_kind ");  
		 sql.append(" ) values(?,?,now(),?,?,?) "); 
		 Object[] args = {obj.getLogType(),obj.getLogContent(),obj.getAccessIp(),obj.getCreateUser(),obj.getMsgKind() };
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public SysLog findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT l.id,l.log_type,l.log_content,DATE_FORMAT(l.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(l.create_time,'%m-%d') create_time_view,l.create_user,l.access_ip,s.loginname,s.truename, (DATE_FORMAT(l.create_time,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d')) msgNewFlag,msg_kind FROM sys_log l ");
		sql.append(" left join sys_user s on s.id=l.create_user ");
		sql.append(" where l.id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new SysLogRowmapper());
	}

	@Override
	public void update(SysLog obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update sys_log "); 
		 sql.append("set  "); 
		 sql.append("log_type=?,log_content=?,access_ip=?,msg_kind=? where id=?  "); 
		 Object[] args = {obj.getLogType(),obj.getLogContent(),obj.getAccessIp(),obj.getMsgKind(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from sys_log where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class SysLogRowmapper implements RowMapper<SysLog> {
		@Override
		public SysLog mapRow(ResultSet rs, int arg1) throws SQLException {
			SysLog obj = new SysLog();
			obj.setAccessIp(rs.getString("access_ip")); 
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setId(rs.getInt("id")); 
			obj.setLogContent(rs.getString("log_content")); 
			obj.setLogType(rs.getString("log_type")); 
			obj.setLoginname(rs.getString("loginname"));
			obj.setTruename(rs.getString("truename"));
			obj.setMsgNewFlag(rs.getString("msgNewFlag"));
			obj.setCreateTimeView(rs.getString("create_time_view"));
			obj.setMsgKind(rs.getString("msg_kind"));
			return obj;
		}
	}

	/**
	 * 最新动态
	 * @return
	 */
	public List<SysLog> findNewMessageList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT l.id,l.log_type,l.log_content,DATE_FORMAT(l.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(l.create_time,'%m-%d') create_time_view,l.create_user,l.access_ip,s.loginname,s.truename, (DATE_FORMAT(l.create_time,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d')) msgNewFlag,msg_kind FROM sys_log l ");
		sql.append(" left join sys_user s on s.id=l.create_user ");
		sql.append("where l.msg_kind='1' ");
		sql.append(" order by l.create_time desc limit 0,6 ");
		List<SysLog> list = dao.query(sql.toString(),new SysLogRowmapper());
		return list;
	}
	
	/**
	 * 最新动态
	 * @return
	 */
	public List<SysLog> findNewMessageList(SearchParams searchParams, Page page) {
		List<Object> argsList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT l.id,l.log_type,l.log_content,DATE_FORMAT(l.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(l.create_time,'%m-%d') create_time_view,l.create_user,l.access_ip,s.loginname,s.truename, (DATE_FORMAT(l.create_time,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d')) msgNewFlag,msg_kind FROM sys_log l ");
		sql.append(" left join sys_user s on s.id=l.create_user ");
		sql.append("where l.msg_kind='1' ");
		
		if(null != searchParams){
			if(null != searchParams.get("logContent") && !"".equals((String)searchParams.get("logContent"))){
				sql.append(" and ").append(" l.log_content like ? ");
				argsList.add("%" + searchParams.get("logContent") + "%");
			}
		}
		
		Object[] args = argsList.toArray();
		List<SysLog> list = dao.query(pageSQL(sql.toString(),"order by create_time desc",page),args,new SysLogRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}

}
