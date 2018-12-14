package com.education.domain ; 

import java.io.Serializable;

/* 
 *  
 * Fri Sep 14 18:09:57 CST 2018 
 */  

public class Exam implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is exam_begintime 
    private String examBegintime; 

    // datebase colume is exam_endtime 
    private String examEndtime; 

    // datebase colume is exam_name 
    private String examName; 

    // datebase colume is id 
    private Integer id; 

    private Integer examUserCount;//考试人数
    private float passScore;
    private String introduce;
    private Integer businessId;
    
    private String[] selCourseArr;
    
    public String[] getSelCourseArr() {
		return selCourseArr;
	}

	public void setSelCourseArr(String[] selCourseArr) {
		this.selCourseArr = selCourseArr;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public float getPassScore() {
		return passScore;
	}

	public void setPassScore(float passScore) {
		this.passScore = passScore;
	}

	public Integer getExamUserCount() {
		return examUserCount;
	}

	public void setExamUserCount(Integer examUserCount) {
		this.examUserCount = examUserCount;
	}

	public String getCreateTime(){ 
        return this.createTime; 
    } 

    public void setCreateTime(String createTime){ 
        this.createTime=createTime; 
    } 


    public int getCreateUser(){ 
        return this.createUser; 
    } 

    public void setCreateUser(int createUser){ 
        this.createUser=createUser; 
    } 


    public String getExamBegintime(){ 
        return this.examBegintime; 
    } 

    public void setExamBegintime(String examBegintime){ 
        this.examBegintime=examBegintime; 
    } 


    public String getExamEndtime(){ 
        return this.examEndtime; 
    } 

    public void setExamEndtime(String examEndtime){ 
        this.examEndtime=examEndtime; 
    } 


    public String getExamName(){ 
        return this.examName; 
    } 

    public void setExamName(String examName){ 
        this.examName=examName; 
    } 


    public Integer getId(){ 
        return this.id; 
    } 

    public void setId(Integer id){ 
        this.id=id; 
    } 


}