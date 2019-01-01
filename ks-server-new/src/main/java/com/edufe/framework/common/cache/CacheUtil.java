package com.edufe.framework.common.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edufe.framework.common.cache.redis.JedisClient;
import com.edufe.module.entity.CacheBusiness;
import com.edufe.module.entity.ExamCourse;
import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.PaperExaminationCacheBean;
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

	public List<PaperExamination> getPaperExaminationList(int examId, String cid, int index) {
		String cacheKey = "paper_ques_" + examId + "_" + cid + "_" + index;
		List<PaperExaminationCacheBean> list = (List<PaperExaminationCacheBean>)jedis.getObject(cacheKey);
		List<PaperExamination> retList = new ArrayList<PaperExamination>();
		for(PaperExaminationCacheBean c : list){
			PaperExamination p = new PaperExamination();
			p.setId(c.getId());
			p.setExaminationContent(c.getExaminationContent());
			p.setOptionA(c.getOptionA());
			p.setOptionB(c.getOptionB());
			p.setOptionC(c.getOptionC());
			p.setOptionD(c.getOptionD());
			p.setOptionE(c.getOptionE());
			p.setOptionF(c.getOptionF());
			p.setDefaultPoint(c.getDefaultPoint());
			p.setTypeCode(c.getTypeCode());
			p.setAnswer(c.getAnswer());
			retList.add(p);
		}
		Collections.shuffle(retList);
		return retList;
	}
}
