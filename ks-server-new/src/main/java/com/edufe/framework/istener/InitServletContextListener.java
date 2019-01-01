package com.edufe.framework.istener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.edufe.framework.common.cache.CacheUtil;
import com.edufe.framework.helper.ApplicationHelper;

@WebListener
public class InitServletContextListener implements ServletContextListener{
	
	@Autowired
	private InitServletServices services;
	@Autowired
	private CacheUtil cacheUtil;
	
	@Override
    public void contextInitialized(ServletContextEvent sce) {
		ApplicationHelper.getInstance().setQuesTypeMap(services.findByMap());
		ApplicationHelper.getInstance().setQuesTypeList(services.find());
		//初使化商户对象至缓存
		services.initBusinessCache();
        System.out.println("ServletContex初始化");
	}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContex销毁");
    }
}
