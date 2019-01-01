package com.edufe.module.entity;

import java.io.Serializable;

/* 
 *  
 * Fri Sep 14 18:27:21 CST 2018 
 */

public class ExamStu implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer id;
	private int examId;
	private String examRoomid;
	private String examRoomname;
	private String examSiteid;
	private String examSitename;
	private String idcard;
	private String identitycode;
	private String loginTime;
	private String loginip;
	private String seatnum;
	private String truename;
	private String photo;
	private String costFlag;
	private String testFlag;
	private Exam exam;

	public String getCostFlag() {
		return costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}

	public String getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getExamId() {
		return this.examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getExamRoomid() {
		return this.examRoomid;
	}

	public void setExamRoomid(String examRoomid) {
		this.examRoomid = examRoomid;
	}

	public String getExamRoomname() {
		return this.examRoomname;
	}

	public void setExamRoomname(String examRoomname) {
		this.examRoomname = examRoomname;
	}

	public String getExamSiteid() {
		return this.examSiteid;
	}

	public void setExamSiteid(String examSiteid) {
		this.examSiteid = examSiteid;
	}

	public String getExamSitename() {
		return this.examSitename;
	}

	public void setExamSitename(String examSitename) {
		this.examSitename = examSitename;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdentitycode() {
		return this.identitycode;
	}

	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getSeatnum() {
		return this.seatnum;
	}

	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}