package com.edufe.module.entity;

import java.io.Serializable;


/* 
 *  
 * Sat Mar 28 06:19:11 CST 2015 
 */

public class PaperExaminationCacheBean implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private Integer id;
	private String answer;//正确答案
	private Integer paperId;
	private String typeCode;
	private String defaultPoint;
	private String examinationContent;

	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String optionF;

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getPaperId() {
		return this.paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDefaultPoint() {
		return this.defaultPoint;
	}

	public void setDefaultPoint(String defaultPoint) {
		this.defaultPoint = defaultPoint;
	}

	public String getExaminationContent() {
		return this.examinationContent;
	}

	public void setExaminationContent(String examinationContent) {
		this.examinationContent = examinationContent;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

}