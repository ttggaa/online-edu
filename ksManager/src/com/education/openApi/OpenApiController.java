package com.education.openApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.beans.ApiResult;
import com.education.framework.base.BaseController;
import com.education.framework.constants.ApiConstants;
import com.education.framework.util.CommonTools;

@Controller
@RequestMapping("openApi")
public class OpenApiController extends BaseController{

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
	@RequestMapping(value = "register", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult register(
			@RequestParam(value="type",required=true) String type,
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="telephone",required=true) String telephone,
			@RequestParam(value="email",required=false) String email) {
		ApiResult result = new ApiResult();
		if(CommonTools.isMobileNO(telephone)){
			RegisterRetBean rrb = services.register(type, name ,telephone, email);
			result.setMsg(rrb.getMsg());
			result.setObj(rrb);
			result.setCode(ApiConstants.RET_SUCCESS);
		}else{
			result.setCode(ApiConstants.RET_FAIL);
		}
		return result;
	}
	
	/**
	 * 按手机号码查询商户是否已经注册
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "findRegisterTel", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult findRegisterTel(@RequestParam(value="telephone",required=true) String telephone) {
		ApiResult result = new ApiResult();
		
		return result;
	}
}
