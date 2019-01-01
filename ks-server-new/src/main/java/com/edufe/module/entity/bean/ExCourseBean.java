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
@ApiModel(value = "考试科目表单")
public class ExCourseBean extends ExBean implements Serializable {

	private static final long serialVersionUID = 2L;
	@ApiModelProperty(value = "考试科目ID")
    @NotBlank(message="考试科目不能为空")
	private String cid;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}