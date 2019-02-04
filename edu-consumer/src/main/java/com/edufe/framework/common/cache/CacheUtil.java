package com.edufe.framework.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edufe.framework.common.cache.redis.JedisClient;
import com.edufe.module.entity.CacheBusiness;

@Component
public class CacheUtil {

	@Autowired
    private JedisClient jedis;
	
	public CacheBusiness getBusiness(String url) {
		if(null == url) return null;
		if("".equals(url)) return null;
		String cacheKey = "busin_" + url;
		CacheBusiness obj = (CacheBusiness)jedis.getObject(cacheKey);
		return obj;
	}
	
	public boolean removeExamPaper(int stuId ,int cid) {
		String cacheKey = "paper_" + stuId + "_" + cid;
		jedis.delObject(cacheKey);
		return true;
	}
}
