package com.education.framework.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysMenuFunc;
import com.education.framework.util.cache.CacheManager;
import com.edufe.module.entity.CacheBusiness;

@Service
public class InitServices extends BaseServices{
	@Autowired
	private CacheManager cache;
	/**
	 * 查询功能权限集合
	 * @return
	 */
	public Map<Integer, List<SysMenuFunc>> findFuncMap() {
		StringBuffer sql = new StringBuffer();
		sql.append("select func_code,func_name,menu_id from sys_menu_func");
		List<SysMenuFunc> list = dao.query(sql.toString(), new RowMapper<SysMenuFunc>(){
			@Override
			public SysMenuFunc mapRow(ResultSet rs, int arg1) throws SQLException {
				SysMenuFunc obj = new SysMenuFunc();
				obj.setFuncCode(rs.getInt("func_code"));
				obj.setFuncName(rs.getString("func_name"));
				obj.setMenuId(rs.getInt("menu_id"));
				return obj;
			}
		});
		
		Map<Integer,List<SysMenuFunc>> map = new HashMap<Integer,List<SysMenuFunc>>();
		for(SysMenuFunc obj : list){
			if(map.containsKey(obj.getMenuId())){
				map.get(obj.getMenuId()).add(obj);
			}else{
				List<SysMenuFunc> fList = new ArrayList<SysMenuFunc>();
				fList.add(obj);
				map.put(obj.getMenuId(), fList);
			}
		}
		return map;
	}

	/**
	 * 缓存已审核商户信息
	 */
	public void initBusinessCache() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,business_name,kind,logo,advert_logo,domain,auth_flag,pro_name,summary,background,footer_view_flag,online_user,account,overdraft,member,cost_price from business ");
		sql.append("where audit_flag='1'");
		List<CacheBusiness> list = dao.query(sql.toString(), new RowMapper<CacheBusiness>(){
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
				obj.setFooterViewFlag(rs.getString("footer_view_flag"));
				obj.setOnlineUser(rs.getInt("online_user"));
				obj.setAccount(rs.getString("account"));
				obj.setOverdraft(rs.getInt("overdraft"));
				obj.setMember(rs.getString("member"));
				obj.setCostPrice(rs.getInt("cost_price"));
				return obj;
			}
		});
		
		if(null != list){
			for(CacheBusiness obj : list){
				boolean r = cache.setBusiness(obj);
			}
		}
	}
	
	/**
	 * 缓存商户信息
	 */
	public void initBusinessCache(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,business_name,kind,logo,advert_logo,domain,auth_flag,pro_name,summary,background,footer_view_flag,online_user,account,overdraft,member,cost_price from business ");
		sql.append("where audit_flag='1' and id=?");
		List<CacheBusiness> list = dao.query(sql.toString(), new Object[]{id} ,new RowMapper<CacheBusiness>(){
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
				obj.setFooterViewFlag(rs.getString("footer_view_flag"));
				obj.setOnlineUser(rs.getInt("online_user"));
				obj.setAccount(rs.getString("account"));
				obj.setOverdraft(rs.getInt("overdraft"));
				obj.setMember(rs.getString("member"));
				obj.setCostPrice(rs.getInt("cost_price"));
				return obj;
			}
		});
		
		if(null != list){
			for(CacheBusiness obj : list){
				boolean r = cache.setBusiness(obj);
			}
		}
	}
	
}
