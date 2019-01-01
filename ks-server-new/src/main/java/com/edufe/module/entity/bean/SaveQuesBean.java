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
@ApiModel(value = "保存试题formbean")
public class SaveQuesBean implements Serializable {

	private static final long serialVersionUID = 2L;
	@ApiModelProperty(value = "科目ID")
    @NotBlank(message="科目ID不能为空")
	private Integer courseId;
	
	@ApiModelProperty(value = "试题保存参数")
    @NotBlank(message="试题保存参数不能为空")
	private String param;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}