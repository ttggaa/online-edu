package com.education.framework.baseModule.module.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.bean.BusinessBean;
import com.education.framework.baseModule.bean.SysInfoBean;
import com.education.framework.session.SessionHelper;
import com.edufe.module.entity.CacheBusiness;

@Service
public class BusinessServices extends BaseServices{

	public BusinessBean findObject(Integer businessId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,business_name,kind,logo,advert_logo,domain,auth_flag,exam_available_count,pro_name,summary,account,background,integrate,member from business ");
		sql.append("where id=?");
		BusinessBean bean = dao.queryForObject(sql.toString(),new Object[]{businessId}, new RowMapper<BusinessBean>(){
			@Override
			public BusinessBean mapRow(ResultSet rs, int arg1) throws SQLException {
				BusinessBean obj = new BusinessBean();
				obj.setId(rs.getInt("id"));
				obj.setBusinessName(rs.getString("business_name"));
				obj.setKind(rs.getString("kind"));
				obj.setLogo(rs.getString("logo"));
				obj.setAdvertLogo(rs.getString("advert_logo"));
				obj.setDomain(rs.getString("domain"));
				obj.setAuthFlag(rs.getString("auth_flag"));
				obj.setAccount(rs.getString("account"));
				obj.setExamAvailableCount(rs.getInt("exam_available_count"));
				obj.setProName(rs.getString("pro_name"));
				obj.setSummary(rs.getString("summary"));
				obj.setBackground(rs.getString("background"));
				obj.setIntegrate(rs.getInt("integrate"));
				obj.setMember(rs.getString("member"));
				return obj;
			}
		});
		return bean;
	}

	public void updateSysInfo(SysInfoBean sysinfo) {
		int bid = SessionHelper.getInstance().getUser().getBusinessId();
		String sql = "update business set business_name=?,summary=? where id=?";
		dao.update(sql, new Object[]{sysinfo.getSysName(), sysinfo.getSysSummary(), bid});
	}

	public CacheBusiness findObjectCache() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,business_name,kind,logo,advert_logo,domain,auth_flag,pro_name,summary,background from business ");
		sql.append("where audit_flag='1' and id=?");
		
		int bid = SessionHelper.getInstance().getUser().getBusinessId();
		try{
			CacheBusiness obj = dao.queryForObject(sql.toString(),new Object[]{bid}, new RowMapper<CacheBusiness>(){
				@Override
				public CacheBusiness mapRow(ResultSet rs, int arg1) throws SQLException {
					CacheBusiness obj = new CacheBusiness();
					obj.setId(rs.getInt("id"));
					obj.setBusinessName(rs.getString("business_name"));
					obj.setKind(rs.getString("kind"));
					obj.setLogo(rs.getString("logo"));
					obj.setAdvertLogo(rs.getString("advert_logo"));
					obj.setDomain(rs.getString("domain"));
					obj.setAuthFlag(rs.getString("auth_flag"));
					obj.setProName(rs.getString("pro_name"));
					obj.setSummary(rs.getString("summary"));
					obj.setBackground(rs.getString("background"));
					return obj;
				}
			});
			return obj;
		}catch(Exception ex){
		}
		return null;
	}

	
}
