package com.education.domain ; 

import java.io.Serializable; 
import java.util.Map;

/* 
 *  
 * Mon Mar 02 18:00:16 CST 2015 
 */  

public class Pracmain implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is createUser 
    private int createuser; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is sumScore 
    private double sumscore; 

    // datebase colume is business_id 
    private int businessId; 

    // datebase colume is pracname 
    private String pracname; 

    // datebase colume is passScore 
    private double passscore; 

    // datebase colume is course_id 
    private int courseId; 

    // datebase colume is createDate 
    private String createdate; 
    private String examSumTime; 
    private Map<String,PracmainSub> pracmainSubMap;
    public String getExamSumTime() {
		return examSumTime;
	}

	public void setExamSumTime(String examSumTime) {
		this.examSumTime = examSumTime;
	}
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, PracmainSub> getPracmainSubMap() {
		return pracmainSubMap;
	}

	public void setPracmainSubMap(Map<String, PracmainSub> pracmainSubMap) {
		this.pracmainSubMap = pracmainSubMap;
	}

	public int getCreateuser(){ 
        return this.createuser; 
    } 

    public void setCreateuser(int createuser){ 
        this.createuser=createuser; 
    } 


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public double getSumscore(){ 
        return this.sumscore; 
    } 

    public void setSumscore(double sumscore){ 
        this.sumscore=sumscore; 
    } 


    public int getBusinessId(){ 
        return this.businessId; 
    } 

    public void setBusinessId(int businessId){ 
        this.businessId=businessId; 
    } 


    public String getPracname(){ 
        return this.pracname; 
    } 

    public void setPracname(String pracname){ 
        this.pracname=pracname; 
    } 


    public double getPassscore(){ 
        return this.passscore; 
    } 

    public void setPassscore(double passscore){ 
        this.passscore=passscore; 
    } 


    public int getCourseId(){ 
        return this.courseId; 
    } 

    public void setCourseId(int courseId){ 
        this.courseId=courseId; 
    } 


    public String getCreatedate(){ 
        return this.createdate; 
    } 

    public void setCreatedate(String createdate){ 
        this.createdate=createdate; 
    } 

}