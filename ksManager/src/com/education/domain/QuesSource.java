package com.education.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Fri Sep 14 10:12:37 CST 2018 
 */  

public class QuesSource implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is business_id 
    private int businessId; 

    // datebase colume is id 
    private Integer id; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is role 
    private String role; 

    // datebase colume is source_name 
    private String sourceName; 

    // datebase colume is state 
    private String state; 

    public int getBusinessId(){ 
        return this.businessId; 
    } 

    public void setBusinessId(int businessId){ 
        this.businessId=businessId; 
    } 


    public Integer getId(){ 
        return this.id; 
    } 

    public void setId(Integer id){ 
        this.id=id; 
    } 


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public String getRole(){ 
        return this.role; 
    } 

    public void setRole(String role){ 
        this.role=role; 
    } 


    public String getSourceName(){ 
        return this.sourceName; 
    } 

    public void setSourceName(String sourceName){ 
        this.sourceName=sourceName; 
    } 


    public String getState(){ 
        return this.state; 
    } 

    public void setState(String state){ 
        this.state=state; 
    } 


}