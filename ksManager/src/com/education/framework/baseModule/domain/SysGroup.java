package com.education.framework.baseModule.domain ; 

import java.io.Serializable; 

/* 
 *  
 * Tue Mar 06 09:23:25 CST 2018 
 */  

public class SysGroup implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is name 
    private String name; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is role_id 
    private int roleId; 

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


    public String getName(){ 
        return this.name; 
    } 

    public void setName(String name){ 
        this.name=name; 
    } 


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public int getRoleId(){ 
        return this.roleId; 
    } 

    public void setRoleId(int roleId){ 
        this.roleId=roleId; 
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