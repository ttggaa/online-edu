package com.education.module.paper.bean;

import java.io.Serializable;

public class TQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145058455787226029L;
	
	private String qid;
	private String qcontent;
	private String typeCode;
	private String typeName;
	private String answer;
	private String point;  //DEFAULT_POINT
	private String optionCount; //选项个数,为空时，默认4个abcd
	
	//用户答案
	private String userAnswer;
	private boolean isRight;
	
	public TQuestion(){
		
	}
	public TQuestion(String qid,String qcontent,String typeCode,String typeName,String answer,String point){
		this.qid = qid;
		this.qcontent = qcontent;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.answer = answer;
		this.point = point;
	}
	public TQuestion(String qid,String qcontent,String typeCode,String typeName,String answer,String point,String optionCount){
		this.qid = qid;
		this.qcontent = qcontent;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.answer = answer;
		this.point = point;
		this.optionCount = optionCount;
	}
	public String getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(String optionCount) {
		this.optionCount = optionCount;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public boolean isRight() {
		if(answer.equals(userAnswer)){
			isRight = true;
		}else{
			isRight = false;
		}
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	
}
