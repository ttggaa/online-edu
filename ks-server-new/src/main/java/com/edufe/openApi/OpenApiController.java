package com.edufe.openApi;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufe.framework.base.BaseController;
import com.edufe.framework.beans.R;
import com.edufe.framework.common.Util;
import com.edufe.framework.validator.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("openApi")
@Api("外部服务接口")
public class OpenApiController extends BaseController{

	@Autowired
	HttpServletRequest request;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OpenApiServices services;
	/**
	 * 注册平台商户
	 * @param type : 个人/企业
	 * @param name : 姓名/企业名称
	 * @param telephone
	 * @param email
	 * @return
	 */
	@PostMapping("register")
	@ApiOperation("注册商户")
	public R register(@RequestBody RegisterBean bean) {
		//表单校验
        ValidatorUtils.validateEntity(bean);
		
        if(Util.isMobileNO(bean.getTelephone())){
			RegisterRetBean rrb = services.register(bean);
			//initServices.initBusinessCache();
			if("".equals(rrb.getMsg())){
				Map<String, Object> map = new HashMap<>();
		        map.put("rrb", rrb);
				return R.ok(map);
			}else{
				return R.error(rrb.getMsg());
			}
		}else{
			return R.error("手机号码格式有误，请添写正确的手机号码！");
		}
	}
		
}
