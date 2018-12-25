package com.edufe.module.entity;

import java.io.Serializable;

/* 
 *  
 * Sat Mar 28 06:19:11 CST 2015 
 */

public class PaperExamination implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer id;
	private String answer;//正确答案
	private String difficulty;
	private Integer paperId;
	private String examinationDescription;
	private String typeCode;
	private String quesTypeName;
	private String defaultPoint;
	private String examinationContent;
	private String accountCode;
	private String auditState;
	private String dshTypeCode;
	private String examinationContentHtml;
	private int qid;

	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String optionF;

	//作答状态1:已答
	private String answerState;
	//未确定标记
	private String uncertainFlag;
	
	//用户答案
	private String userAnswer="";
	//用户选择情况
	private String optionASel;
	private String optionBSel;
	private String optionCSel;
	private String optionDSel;
	private String optionESel;
	private String optionFSel;
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getOptionASel() {
		return optionASel;
	}

	public void setOptionASel(String optionASel) {
		this.optionASel = optionASel;
	}

	public String getOptionBSel() {
		return optionBSel;
	}

	public void setOptionBSel(String optionBSel) {
		this.optionBSel = optionBSel;
	}

	public String getOptionCSel() {
		return optionCSel;
	}

	public void setOptionCSel(String optionCSel) {
		this.optionCSel = optionCSel;
	}

	public String getOptionDSel() {
		return optionDSel;
	}

	public void setOptionDSel(String optionDSel) {
		this.optionDSel = optionDSel;
	}

	public String getOptionESel() {
		return optionESel;
	}

	public void setOptionESel(String optionESel) {
		this.optionESel = optionESel;
	}

	public String getOptionFSel() {
		return optionFSel;
	}

	public void setOptionFSel(String optionFSel) {
		this.optionFSel = optionFSel;
	}

	public String getAnswerState() {
		return answerState;
	}

	public void setAnswerState(String answerState) {
		this.answerState = answerState;
	}

	public String getUncertainFlag() {
		return uncertainFlag;
	}

	public void setUncertainFlag(String uncertainFlag) {
		this.uncertainFlag = uncertainFlag;
	}

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

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getExaminationContentHtml() {
		return examinationContentHtml;
	}

	public void setExaminationContentHtml(String examinationContentHtml) {
		this.examinationContentHtml = examinationContentHtml;
	}

	public String getDshTypeCode() {
		return dshTypeCode;
	}

	public void setDshTypeCode(String dshTypeCode) {
		this.dshTypeCode = dshTypeCode;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public int getPaperId() {
		return this.paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String getExaminationDescription() {
		return this.examinationDescription;
	}

	public void setExaminationDescription(String examinationDescription) {
		this.examinationDescription = examinationDescription;
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

	public String getQuesTypeName() {
		return quesTypeName;
	}

	public void setQuesTypeName(String quesTypeName) {
		this.quesTypeName = quesTypeName;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public void fillSel(String answerParam) {
		if(null == answerParam) return ;
		optionASel = "";
		optionBSel = "";
		optionCSel = "";
		optionDSel = "";
		optionESel = "";
		optionFSel = "";
		if("danx".equals(this.typeCode)){
			if("a".equals(answerParam)){
				optionASel = "1";
			}else if("b".equals(answerParam)){
				optionBSel = "1";
			}else if("c".equals(answerParam)){
				optionCSel = "1";
			}else if("d".equals(answerParam)){
				optionDSel = "1";
			}else if("e".equals(answerParam)){
				optionESel = "1";
			}else if("f".equals(answerParam)){
				optionFSel = "1";
			}
		}else if("duox".equals(this.typeCode)){
			int n = answerParam.length();
			for(int i=0;i<n;i++){
				String optionTemp = String.valueOf(answerParam.charAt(i));
				if("a".equals(optionTemp)){
					optionASel = "1";
				}else if("b".equals(optionTemp)){
					optionBSel = "1";
				}else if("c".equals(optionTemp)){
					optionCSel = "1";
				}else if("d".equals(optionTemp)){
					optionDSel = "1";
				}else if("e".equals(optionTemp)){
					optionESel = "1";
				}else if("f".equals(optionTemp)){
					optionFSel = "1";
				}
			}
		}
	}

}