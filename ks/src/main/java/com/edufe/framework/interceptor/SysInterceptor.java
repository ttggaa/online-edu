package com.edufe.framework.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class SysInterceptor extends WebMvcConfigurerAdapter{
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SysInterceptorHandle()).addPathPatterns("/**");
//        registry.addInterceptor(new SysInterceptorHandle());
//    }
	
	@Bean
	SessionInterceptorHandle localInterceptor() {
        return new SessionInterceptorHandle();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
    }
}
