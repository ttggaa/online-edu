package com.edufe.module.entity.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 考试时间对象
 *
 * @author yangchao
 * @since 1.0 2018-12-18
 */
@ApiModel(value = "考试时间对象")
public class ExCourseEndTimeBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer id;
	private String endTime;
	private String examSumTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getExamSumTime() {
		return examSumTime;
	}
	public void setExamSumTime(String examSumTime) {
		this.examSumTime = examSumTime;
	}
	
}