package com.education.domain ; 

import java.io.Serializable;

/* 
 *  
 * Fri Sep 14 10:12:57 CST 2018 
 */  

public class ResCourse implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is business_id 
    private Integer businessId; 

    // datebase colume is course_code 
    private String courseCode; 

    // datebase colume is course_name 
    private String courseName; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is exam_sum_time 
    private String examSumTime; 

    // datebase colume is id 
    private Integer id; 

    // datebase colume is indexno 
    private Integer indexno;
    private String remark;
    private Integer quesSumCount;
    
    private String selCourseFlag;
    
	public Integer getQuesSumCount() {
		return quesSumCount;
	}

	public void setQuesSumCount(Integer quesSumCount) {
		this.quesSumCount = quesSumCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSelCourseFlag() {
		return selCourseFlag;
	}

	public void setSelCourseFlag(String selCourseFlag) {
		this.selCourseFlag = selCourseFlag;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
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