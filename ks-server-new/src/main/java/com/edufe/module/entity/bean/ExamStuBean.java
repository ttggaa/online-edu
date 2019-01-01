package com.edufe.module.entity.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录表单
 *
 * @author yangchao
 * @since 1.0 2018-12-18
 */
@ApiModel(value = "登录表单")
public class ExamStuBean extends ExBean implements Serializable {

	private static final long serialVersionUID = 2L;
	@ApiModelProperty(value = "准考证号")
    @NotBlank(message="准考证号不能为空")
	private String idcard;
	
	@ApiModelProperty(value = "姓名")
    @NotBlank(message="姓名不能为空")
	private String truename;
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}

}