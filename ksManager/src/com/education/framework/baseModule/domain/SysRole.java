package com.education.framework.baseModule.domain ; 

import java.io.Serializable;
import java.util.List;
 

public class SysRole implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is role_name 
    private String roleName; 

    // datebase colume is update_time 
    private String updateTime; 
    private String roleCode;
    // datebase colume is update_user 
    private int updateUser; 
    private List<SysMenu> menuList;
    private String roleUsers;
    
    ///////
    private String select;
    private int[] menuAuth; //角色赋权使用
    private int[] menuFunc;
    
    public String getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(String roleUsers) {
		this.roleUsers = roleUsers;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public int[] getMenuAuth() {
		return menuAuth;
	}

	public void setMenuAuth(int[] menuAuth) {
		this.menuAuth = menuAuth;
	}

	public int[] getMenuFunc() {
		return menuFunc;
	}

	public void setMenuFunc(int[] menuFunc) {
		this.menuFunc = menuFunc;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

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


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public String getRoleName(){ 
        return this.roleName; 
    } 

    public void setRoleName(String roleName){ 
        this.roleName=roleName; 
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