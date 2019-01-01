package com.edufe.module.entity.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表单父类
 *
 * @author yangchao
 * @since 1.0 2018-12-18
 */
@ApiModel(value = "表单父类")
public class ExBean implements Serializable {

	private static final long serialVersionUID = 2L;
	@ApiModelProperty(value = "请求URL")
    @NotBlank(message="请求URL不能为空")
	private String reqUrl;
	
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	
}