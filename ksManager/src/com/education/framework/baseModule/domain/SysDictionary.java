package com.education.framework.baseModule.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Tue Mar 06 09:22:43 CST 2018 
 */  

public class SysDictionary implements Serializable {  

  private static final long serialVersionUID = 2L; 

  private Integer id;
    // datebase colume is code 
    private String code; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is ext1 
    private String ext1; 

    // datebase colume is ext2 
    private String ext2; 

    // datebase colume is ext3 
    private int ext3; 

    // datebase colume is field_id 
    private String fieldId; 

    // datebase colume is field_name 
    private String fieldName; 

    // datebase colume is label 
    private String label; 

    // datebase colume is order_index 
    private int orderIndex; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is update_time 
    private String updateTime; 

    // datebase colume is update_user 
    private int updateUser; 

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode(){ 
        return this.code; 
    } 

    public void setCode(String code){ 
        this.code=code; 
    } 


    public String getCreateTime(){ 
        return this.createTime; 
    } 

    public void setCreateTime(String createTime){ 
        this.createTime=createTime; 
    } 


    public int getCreateUser(){ 
        return this.createUser; 
    } 

    public void setCreateUser(int createUser){ 
        this.createUser=createUser; 
    } 


    public String getExt1(){ 
        return this.ext1; 
    } 

    public void setExt1(String ext1){ 
        this.ext1=ext1; 
    } 


    public String getExt2(){ 
        return this.ext2; 
    } 

    public void setExt2(String ext2){ 
        this.ext2=ext2; 
    } 


    public int getExt3(){ 
        return this.ext3; 
    } 

    public void setExt3(int ext3){ 
        this.ext3=ext3; 
    } 


    public String getFieldId(){ 
        return this.fieldId; 
    } 

    public void setFieldId(String fieldId){ 
        this.fieldId=fieldId; 
    } 


    public String getFieldName(){ 
        return this.fieldName; 
    } 

    public void setFieldName(String fieldName){ 
        this.fieldName=fieldName; 
    } 


    public String getLabel(){ 
        return this.label; 
    } 

    public void setLabel(String label){ 
        this.label=label; 
    } 


    public int getOrderIndex(){ 
        return this.orderIndex; 
    } 

    public void setOrderIndex(int orderIndex){ 
        this.orderIndex=orderIndex; 
    } 


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public String getUpdateTime(){ 
        return this.updateTime; 
    } 

    public void setUpdateTime(String updateTime){ 
        this.updateTime=updateTime; 
    } 


    public int getUpdateUser(){ 
        return this.updateUser; 
    } 

    public void setUpdateUser(int updateUser){ 
        this.updateUser=updateUser; 
    } 


}