package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Sat Mar 28 06:19:00 CST 2015 
 */  

public class Paper implements Serializable {  

	public Paper(){
		
	}
	public Paper(int id , String name){
		this.setId(id);
		this.setPaperName(name);
	}
    private static final long serialVersionUID = 2L; 
    private Integer id;
    // datebase colume is pracid 
    private int pracid; 
    private Pracmain pracmain;
    
    // datebase colume is business_id 
    private int businessId; 

    // datebase colume is paper_name 
    private String paperName; 

    // datebase colume is course_id 
    private int courseId; 
    private ResCourse resCourse;
    private int quesCount;
    private int totalScore;
    private int passScore;
    private int useCount;

    private String state;
    //temp
    private String opterType;
    private String kindCs;
    private String kindMobile;
    private String levelId;
    private String[] sourceIds;
    private String[] sourceIdsValue;
    private String createTime;
    private Integer createUser;
    
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
	public String[] getSourceIdsValue() {
		return sourceIdsValue;
	}
	public void setSourceIdsValue(String[] sourceIdsValue) {
		this.sourceIdsValue = sourceIdsValue;
	}
	public String[] getSourceIds() {
		return sourceIds;
	}
	public void setSourceIds(String[] sourceIds) {
		this.sourceIds = sourceIds;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getKindCs() {
		return kindCs;
	}
	public void setKindCs(String kindCs) {
		this.kindCs = kindCs;
	}
	public String getKindMobile() {
		return kindMobile;
	}
	public void setKindMobile(String kindMobile) {
		this.kindMobile = kindMobile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOpterType() {
		return opterType;
	}
	public void setOpterType(String opterType) {
		this.opterType = opterType;
	}
	public int getPracid(){ 
        return this.pracid; 
    } 

    public void setPracid(int pracid){ 
        this.pracid=pracid; 
    } 

    public int getBusinessId(){ 
        return this.businessId; 
    } 

    public void setBusinessId(int businessId){ 
        this.businessId=businessId; 
    } 


    public String getPaperName(){ 
        return this.paperName; 
    } 

    public void setPaperName(String paperName){ 
        this.paperName=paperName; 
    } 

    public int getCourseId(){ 
        return this.courseId; 
    } 

    public void setCourseId(int courseId){ 
        this.courseId=courseId; 
    }

	public int getQuesCount() {
		return quesCount;
	}

	public void setQuesCount(int quesCount) {
		this.quesCount = quesCount;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getPassScore() {
		return passScore;
	}

	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}
	public Pracmain getPracmain() {
		return pracmain;
	}
	public void setPracmain(Pracmain pracmain) {
		this.pracmain = pracmain;
	}
	public ResCourse getResCourse() {
		return resCourse;
	}
	public void setResCourse(ResCourse resCourse) {
		this.resCourse = resCourse;
	} 


}