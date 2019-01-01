package com.edufe.module.entity.bean;

import java.io.Serializable;
import java.util.List;

import com.edufe.module.entity.PaperExamination;
import com.edufe.module.entity.Type;

/* 
 * 题型试题对象 
 */

public class ExaminationType implements Serializable {

	private static final long serialVersionUID = 2L;
	private Type type ;
	private List<PaperExamination> paperExaminationList = null;
	private Integer quesCount; //共多少题
	private String score;//题型分值
	
	public Integer getQuesCount() {
		return quesCount;
	}
	public void setQuesCount(Integer quesCount) {
		this.quesCount = quesCount;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public List<PaperExamination> getPaperExaminationList() {
		return paperExaminationList;
	}
	public void setPaperExaminationList(List<PaperExamination> paperExaminationList) {
		this.paperExaminationList = paperExaminationList;
	}
	
}