package com.edufe.framework.istener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.Type;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.common.cache.redis.JedisClient;
@Service
@Transactional
public class InitServletServices {

	@Autowired
	protected JdbcTemplate jdbc;

	@Autowired
	private JedisClient jedis;
	@Autowired
	private CacheUtil cacheUtil;
	
	public Map<String, Type> findByMap() {
		Map<String, Type> map = new HashMap<String,Type>();
		List<Type> list = find();
		for(Type t : list){
			map.put(t.getTypeCode(), t);
		}
		return map;
	}
	
	public List<Type> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT typeid,typename,type_code,typedesc,typesort FROM type order by typesort");
		
		List<Type> list = jdbc.query(sql.toString(),new TypeRowmapper());
		return list;
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

	public void initBusinessCache() {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,business_name,kind,logo,advert_logo,domain,auth_flag,pro_name,summary,background,footer_view_flag,online_user,account,overdraft,member,cost_price from business ");
		sql.append("where audit_flag='1'");
		List<CacheBusiness> list = jdbc.query(sql.toString(), new RowMapper<CacheBusiness>(){
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
				cacheUtil.setBusiness(obj);
			}
		}
	}
}
