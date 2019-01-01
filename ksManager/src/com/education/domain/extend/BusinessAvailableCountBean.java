package com.education.domain.extend;

import java.io.Serializable;

/* 
 *  
 * Tue Aug 18 07:47:19 CST 2015 
 */

public class BusinessAvailableCountBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private String id;
	private String exam_available_count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExam_available_count() {
		return exam_available_count;
	}
	public void setExam_available_count(String exam_available_count) {
		this.exam_available_count = exam_available_count;
	}
	
}