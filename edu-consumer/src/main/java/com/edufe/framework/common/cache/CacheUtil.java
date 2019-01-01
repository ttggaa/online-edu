package com.edufe.framework.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edufe.framework.common.cache.redis.JedisClient;

@Component
public class CacheUtil {

	@Autowired
    private JedisClient jedis;
	
}
