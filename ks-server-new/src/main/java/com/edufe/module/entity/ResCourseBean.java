package com.edufe.module.entity ; 

import java.io.Serializable;

/* 
 *  
 * Fri Sep 14 10:12:57 CST 2018 
 */  

public class ResCourseBean implements Serializable {  
  private static final long serialVersionUID = 2L; 
    private String courseName; 
    private String examSumTime; 
    private Integer id; 
    private Integer indexno;
    
    public ResCourseBean(){
    	
    }
    
    public ResCourseBean(Integer id, String courseName,String examSumTime,Integer indexno){
    	this.id = id;
    	this.courseName = courseName;
    	this.examSumTime = examSumTime;
    	this.indexno = indexno;
    }
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getExamSumTime() {
		return examSumTime;
	}
	public void setExamSumTime(String examSumTime) {
		this.examSumTime = examSumTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIndexno() {
		return indexno;
	}
	public void setIndexno(Integer indexno) {
		this.indexno = indexno;
	}
}