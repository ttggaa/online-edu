package com.education.framework.util.cache;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.util.cache.redis.JedisClient;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.PaperExaminationCacheBean;

@Component
public class CacheManager implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private JedisClient jedis;	
	
    /**
     * 加前缀
     * @param key
     * @return
     */
	public String addPrefix(String key) {
		return CacheConstants.REDIS_PREFIX + key;
	}
	
	/**
     * 获取 dict List缓存
     * @param key 键
     * @return 值
     */
    public  List<SysDictionary> getDictList() {
    	String key = addPrefix("DICT");
    	return (List<SysDictionary>)jedis.getObjectList(key);
    }

    /**
     * 设置List缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setDictList(List<SysDictionary> list) {
    	if(null == list) return "";
    	String key = addPrefix("DICT");
    	return jedis.setObjectList(key, list, 0);
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

	public void removePaperExaminationList(String cid, int index) {
		String cacheKey = "paper_ques_" + cid + "_" + index;
		jedis.delObject(cacheKey);
	}

	public void setPaperExaminationList(String cid, int index, List<PaperExaminationCacheBean> list) {
		String cacheKey = "paper_ques_" + cid + "_" + index;
		jedis.setObject(cacheKey, list, 0);
	}
}