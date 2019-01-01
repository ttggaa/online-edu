package com.edufe.module.entity.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录页配置对象
 *
 * @author yangchao
 * @since 1.0 2018-12-26
 */
@ApiModel(value = "登录页配置对象")
public class LoginConfBean implements Serializable {

	private static final long serialVersionUID = 2L;
	@ApiModelProperty(value = "产品名称")
	private String proName;
	
	@ApiModelProperty(value = "产品摘要说明")
	private String summary;
	
	@ApiModelProperty(value = "登录页背景图")
	private String background;

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}
	
	
}