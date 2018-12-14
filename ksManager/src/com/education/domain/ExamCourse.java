package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Fri Sep 14 18:27:26 CST 2018 
 */  

public class ExamCourse implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is course_id 
    private Integer courseId; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is end_time 
    private String endTime; 

    // datebase colume is id 
    private Integer id; 

    // datebase colume is score 
    private String score; 

    // datebase colume is stu_id 
    private Integer stuId; 

    // datebase colume is submit_time 
    private String submitTime; 
    private String submitFlag;
    
    private String courseName;
    private Integer examId;
    
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getSubmitFlag() {
		return submitFlag;
	}
	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
    
}