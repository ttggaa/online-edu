package com.edufe.openApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.edufe.framework.common.Md5Util;
import com.edufe.framework.common.RandomUtil;
import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.module.entity.CacheBusiness;

@Service
public class OpenApiServices {
	@Autowired
	protected JdbcTemplate jdbc;
	@Autowired
	private CacheUtil cache;
	
	public RegisterRetBean register(RegisterBean bean) {
		String type = bean.getType();
		String name = bean.getName();
		String telephone = bean.getTelephone();
		String email = bean.getEmail();
		RegisterRetBean rrb = new RegisterRetBean();
		String sql = "select count(1) from business where telephone=?";
		int r = jdbc.queryForObject(sql, new Object[]{telephone}, Integer.class);
		if(r > 0){
			//大于0 ，已存在商户
			rrb.setMsg("exist");
			return rrb;
		}else{
			//查询可用的DOMAIN
			String domain = "";
			try{
				domain = jdbc.queryForObject("select domain from grant_business_domain where state='0' limit 1 for update", String.class);
			}catch(Exception ex){
			}
			jdbc.update("update grant_business_domain set state='1',update_time=now() where domain=?", new Object[]{domain});
			if(null != domain && !"".equals(domain)){
				//初使化商户
				StringBuffer insSql = new StringBuffer();
				insSql.append("insert into business(business_name,telephone,mail,audit_flag,domain,create_time) ");
				insSql.append("values(?,?,?,'1',?,now())");
				Object[] args = {name, telephone, email, domain};
				jdbc.update(insSql.toString(),args);
				int businessId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class); 
				if(businessId > 0){
					CacheBusiness cacheBusiness = new CacheBusiness();
					cacheBusiness.setId(businessId);
					cacheBusiness.setBusinessName(name);
					cacheBusiness.setDomain(domain);
					cache.setBusiness(cacheBusiness);
					//初使化商户管理员账号
					rrb.setBusinessName(name);
					rrb.setAdminLoginName("admin");
					rrb.setAdminPassword(RandomUtil.generateRandomStr(8));
					rrb.setDomain(domain);
					//创建默认管理员账号
					StringBuffer sysUserInsSql = new StringBuffer(); 
					sysUserInsSql.append("insert into sys_user ( "); 
					sysUserInsSql.append("loginname,passwd,truename,telephone,state,email "); 
					sysUserInsSql.append(",create_time,create_user,business_id ");  
					sysUserInsSql.append(" ) values(?,?,?,?,'1',?, now(), 0,?) "); 
					Object[] sysUserInsArgs = {rrb.getAdminLoginName(),Md5Util.MD5Encode(rrb.getAdminPassword(),"utf-8"),"管理员",telephone,email,businessId};
					int r1 = jdbc.update(sysUserInsSql.toString(), sysUserInsArgs);
					
					if(r1 > 0){
						int userId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",Integer.class); 
						int roleId = jdbc.queryForObject("select id from sys_role where role_code='BUSINESS' limit 1",Integer.class);
						if(roleId > 0){
							//添加默认角色权限
							jdbc.update("insert into sys_user_role(user_id,role_id,create_time,create_user) values(?,?,now(),0)"
									, new Object[]{userId,roleId});
						}
					}
				}
			}else{
				rrb.setMsg("免费注册名额已经用完了，敬请期待！！！");
			}
			
		}
		return rrb;
	}


}
