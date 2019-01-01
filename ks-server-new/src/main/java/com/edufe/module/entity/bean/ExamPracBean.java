package com.edufe.module.entity.bean;

import java.io.Serializable;

/* 
 *  
 * Tue Aug 18 07:47:19 CST 2015 
 */

public class ExamPracBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private String typeCode;
	private String difid;
	private String count;
	private String score;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getDifid() {
		return difid;
	}
	public void setDifid(String difid) {
		this.difid = difid;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}