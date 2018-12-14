package com.edufe.framework.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edufe.framework.common.cache.redis.JedisClient;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.SysConfig;

@Component
public class CacheUtil {

	@Autowired
    private JedisClient jedis;
	
	/**
	 * 获取系统配置
	 * @return
	 */
	public SysConfig getSysConfig() {
		SysConfig sysConfig = (SysConfig)jedis.getObject("sysConfig");
		
        return sysConfig;
    }
	
	/**
	 * 设置系统配置
	 * @return
	 */
	public boolean setSysConfig(SysConfig config) {
		jedis.setObject("sysConfig", config, 0);
        return true;
    }

	public ExamCourse getExamPaper(int stuId ,int cid) {
		String cacheKey = "paper_" + stuId + "_" + cid;
		ExamCourse ec = (ExamCourse)jedis.getObject(cacheKey);
		return ec;
	}
	
	public boolean setExamPaper(int stuId ,int cid, ExamCourse ec) {
		String cacheKey = "paper_" + stuId + "_" + cid;
		jedis.setObject(cacheKey, ec, 0);
        return true;
    }
	
	public boolean removeExamPaper(int stuId ,int cid) {
		String cacheKey = "paper_" + stuId + "_" + cid;
		jedis.delObject(cacheKey);
		return true;
	}
	
	public CacheBusiness getBusiness(String url) {
		if(null == url) return null;
		if("".equals(url)) return null;
		String cacheKey = "busin_" + url;
		CacheBusiness obj = (CacheBusiness)jedis.getObject(cacheKey);
		return obj;
	}
	
	public boolean setBusiness(CacheBusiness obj) {
		if(null == obj.getDomain()) return false;
		if("".equals(obj.getDomain())) return false;
		String cacheKey = "busin_" + obj.getDomain();
		jedis.setObject(cacheKey, obj, 0);
		return true;
	}
    
	public boolean removeExamPaper(String url) {
		String cacheKey = "busin_" + url;
		jedis.delObject(cacheKey);
		return true;
	}
}
