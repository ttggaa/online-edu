package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Wed Sep 19 09:08:12 CST 2018 
 */  

public class ExamStu implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is exam_id 
    private Integer examId; 

    // datebase colume is exam_roomid 
    private String examRoomid; 

    // datebase colume is exam_roomname 
    private String examRoomname; 

    // datebase colume is exam_siteid 
    private String examSiteid; 

    // datebase colume is exam_sitename 
    private String examSitename; 

    // datebase colume is id 
    private Integer id; 

    // datebase colume is idcard 
    private String idcard; 

    // datebase colume is identitycode 
    private String identitycode; 

    // datebase colume is login_time 
    private String loginTime; 

    // datebase colume is loginip 
    private String loginip; 

    // datebase colume is photo 
    private String photo; 

    // datebase colume is seatnum 
    private String seatnum; 

    // datebase colume is truename 
    private String truename;
    
    //////////////////////
    private String examName;//考试活动名称
    private ExamCourse examCourse; 
    private Integer businessId;
    private String testFlag;
    
	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public ExamCourse getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(ExamCourse examCourse) {
		this.examCourse = examCourse;
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

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamRoomid() {
		return examRoomid;
	}

	public void setExamRoomid(String examRoomid) {
		this.examRoomid = examRoomid;
	}

	public String getExamRoomname() {
		return examRoomname;
	}

	public void setExamRoomname(String examRoomname) {
		this.examRoomname = examRoomname;
	}

	public String getExamSiteid() {
		return examSiteid;
	}

	public void setExamSiteid(String examSiteid) {
		this.examSiteid = examSiteid;
	}

	public String getExamSitename() {
		return examSitename;
	}

	public void setExamSitename(String examSitename) {
		this.examSitename = examSitename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdentitycode() {
		return identitycode;
	}

	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	} 

}