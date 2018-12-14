package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Thu Jan 29 06:06:30 CST 2015 
 */  

public class TkExamination implements Serializable {  

  private static final long serialVersionUID = 2L; 
    private Integer id;
    // datebase colume is DEFAULT_POINT 
    private String defaultPoint; 

    // datebase colume is dsh_type_code 
    private String dshTypeCode; 

    // datebase colume is chapter_id 
    private String courseName; 

    // datebase colume is answer 
    private String answer; 

    // datebase colume is type_code 
    private String typeCode; 

    // datebase colume is EXAMINATION_DESCRIPTION 
    private String examinationDescription; 

    // datebase colume is BATCH_INFO 
    private String batchInfo; 

    // datebase colume is EXAMINATION_CONTENT 
    private String examinationContent; 

    // datebase colume is DIFFICULTY 
    private String difficulty; 

    // datebase colume is course_id 
    private int courseId; 
    private String accountCode;
    private String examinationContentHtml;

    private Type type;
    private String sourceId;
    private String auditState;
    private String knowledgePoint;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String createTime; 
    private Integer createUser; 
    private String updateTime; 
    private Integer updateUser; 
    
    public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKnowledgePoint() {
		return knowledgePoint;
	}

	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getExaminationContentHtml() {
		return examinationContentHtml;
	}

	public void setExaminationContentHtml(String examinationContentHtml) {
		this.examinationContentHtml = examinationContentHtml;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getDefaultPoint(){ 
        return this.defaultPoint; 
    } 

    public void setDefaultPoint(String defaultPoint){ 
        this.defaultPoint=defaultPoint; 
    } 


    public String getDshTypeCode(){ 
        return this.dshTypeCode; 
    } 

    public void setDshTypeCode(String dshTypeCode){ 
        this.dshTypeCode=dshTypeCode; 
    } 

    public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAnswer(){ 
        return this.answer; 
    } 

    public void setAnswer(String answer){ 
        this.answer=answer; 
    } 

    public String getTypeCode(){ 
        return this.typeCode; 
    } 

    public void setTypeCode(String typeCode){ 
        this.typeCode=typeCode; 
    } 


    public String getExaminationDescription(){ 
        return this.examinationDescription; 
    } 

    public void setExaminationDescription(String examinationDescription){ 
        this.examinationDescription=examinationDescription; 
    } 


    public String getBatchInfo(){ 
        return this.batchInfo; 
    } 

    public void setBatchInfo(String batchInfo){ 
        this.batchInfo=batchInfo; 
    } 


    public String getExaminationContent(){ 
        return this.examinationContent; 
    } 

    public void setExaminationContent(String examinationContent){ 
        this.examinationContent=examinationContent; 
    } 


    public String getDifficulty(){ 
        return this.difficulty; 
    } 

    public void setDifficulty(String difficulty){ 
        this.difficulty=difficulty; 
    } 

    public int getCourseId(){ 
        return this.courseId; 
    } 

    public void setCourseId(int courseId){ 
        this.courseId=courseId; 
    }
    
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	
}