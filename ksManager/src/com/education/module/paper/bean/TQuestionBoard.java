package com.education.module.paper.bean;

import java.io.Serializable;

//答题板
public class TQuestionBoard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1137291660837000782L;
	private String qId ; //ID
	private String qTypeCode ; //题型code
	private String qNum ; //题号
	private String state ; //已答 ，未答
	private String uncertain; //疑难未确定
	
	public TQuestionBoard(){
		
	}
	public TQuestionBoard(String qId,String qTypeCode,String qNum,String state,String uncertain){
		this.qId = qId;
		this.qTypeCode = qTypeCode;
		this.qNum = qNum;
		this.state = state;
		this.uncertain = uncertain;
	}
	
	public String getqTypeCode() {
		return qTypeCode;
	}
	public void setqTypeCode(String qTypeCode) {
		this.qTypeCode = qTypeCode;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getqNum() {
		return qNum;
	}
	public void setqNum(String qNum) {
		this.qNum = qNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUncertain() {
		return uncertain;
	}
	public void setUncertain(String uncertain) {
		this.uncertain = uncertain;
	}
	
}
