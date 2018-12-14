package com.education.framework.baseModule.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Tue Mar 06 09:22:58 CST 2018 
 */  

public class SysUserGroup implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is group_id 
    private int groupId; 

    // datebase colume is user_id 
    private int userId; 

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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


    public int getGroupId(){ 
        return this.groupId; 
    } 

    public void setGroupId(int groupId){ 
        this.groupId=groupId; 
    } 


    public int getUserId(){ 
        return this.userId; 
    } 

    public void setUserId(int userId){ 
        this.userId=userId; 
    } 


}