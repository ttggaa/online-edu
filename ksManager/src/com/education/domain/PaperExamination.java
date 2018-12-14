package com.education.domain ; 

import java.io.Serializable;

/* 
 *  
 * Sat Mar 28 06:19:11 CST 2015 
 */  

public class PaperExamination implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is answer 
    private String answer; 

    // datebase colume is difficulty 
    private String difficulty; 

    // datebase colume is paper_id 
    private int paperId; 
    private Paper paper;

    // datebase colume is examination_description 
    private String examinationDescription; 

    // datebase colume is type_code 
    private String typeCode; 
    private Type quesType;
    // datebase colume is default_point 
    private String defaultPoint; 

    // datebase colume is examination_content 
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

	public String getAnswer(){ 
        return this.answer; 
    } 

    public void setAnswer(String answer){ 
        this.answer=answer; 
    } 


    public String getDifficulty(){ 
        return this.difficulty; 
    } 

    public void setDifficulty(String difficulty){ 
        this.difficulty=difficulty; 
    } 


    public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public int getPaperId(){ 
        return this.paperId; 
    } 

    public void setPaperId(int paperId){ 
        this.paperId=paperId; 
    } 

    public String getExaminationDescription(){ 
        return this.examinationDescription; 
    } 

    public void setExaminationDescription(String examinationDescription){ 
        this.examinationDescription=examinationDescription; 
    } 


    public String getTypeCode(){ 
        return this.typeCode; 
    } 

    public void setTypeCode(String typeCode){ 
        this.typeCode=typeCode; 
    } 

    public String getDefaultPoint(){ 
        return this.defaultPoint; 
    } 

    public void setDefaultPoint(String defaultPoint){ 
        this.defaultPoint=defaultPoint; 
    } 

    public String getExaminationContent(){ 
        return this.examinationContent; 
    } 

    public void setExaminationContent(String examinationContent){ 
        this.examinationContent=examinationContent; 
    }

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Type getQuesType() {
		return quesType;
	}

	public void setQuesType(Type quesType) {
		this.quesType = quesType;
	} 


}