package com.edufe.framework.base;

import javax.servlet.http.HttpServletRequest;


public class BaseController {

	public Integer getSuperTenantId(HttpServletRequest request){ 
		Integer tenantId = (Integer)request.getAttribute("SYS_TENANT_ID");
    	return tenantId;
	}
}
