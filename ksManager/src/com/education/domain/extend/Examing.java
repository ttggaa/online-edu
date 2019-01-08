package com.education.domain.extend ; 

import java.io.Serializable;

import com.education.domain.Exam;

/* 
 *  
 * Fri Sep 14 18:09:57 CST 2018 
 */  

public class Examing extends Exam implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // 开始日期
    private String viewDate; 

    // 显示时间 
    private String viewTime;

	public String getViewDate() {
		return viewDate;
	}

	public void setViewDate(String viewDate) {
		this.viewDate = viewDate;
	}

	public String getViewTime() {
		return viewTime;
	}

	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	} 
    

}