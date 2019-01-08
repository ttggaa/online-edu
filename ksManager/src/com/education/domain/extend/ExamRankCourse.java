package com.education.domain.extend;

import java.io.Serializable;

public class ExamRankCourse implements Serializable {

	private static final long serialVersionUID = 2L;
	private int cid; 
	private String courseName;//科目名称
	private int examingCount; //开考人数
	private int submitCount; //交卷人数
	private String passRate; //通过率
	private String maxScore; //最高分
	private String maxScoreUserInfo;//最高分用户信息
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getExamingCount() {
		return examingCount;
	}
	public void setExamingCount(int examingCount) {
		this.examingCount = examingCount;
	}
	public int getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(int submitCount) {
		this.submitCount = submitCount;
	}
	public String getPassRate() {
		return passRate;
	}
	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}
	public String getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
	public String getMaxScoreUserInfo() {
		return maxScoreUserInfo;
	}
	public void setMaxScoreUserInfo(String maxScoreUserInfo) {
		this.maxScoreUserInfo = maxScoreUserInfo;
	}
}