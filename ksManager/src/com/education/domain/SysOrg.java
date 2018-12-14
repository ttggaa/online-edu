package com.education.domain ; 

import java.io.Serializable;
import java.util.List;

/* 
 *  
 * Fri May 11 14:57:38 CST 2018 
 */  

public class SysOrg implements Serializable {  

  private static final long serialVersionUID = 2L; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private Integer createUser; 

    // datebase colume is id 
    private Integer id; 

    // datebase colume is org_name 
    private String orgName; 

    // datebase colume is remark 
    private String remark; 
    
    private String userId;
    private String usernameView;
    private Integer pid;
    private String parentOrgName;
    private List<SysOrg> subList;

	public List<SysOrg> getSubList() {
		return subList;
	}

	public void setSubList(List<SysOrg> subList) {
		this.subList = subList;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUsernameView() {
		return usernameView;
	}

	public void setUsernameView(String usernameView) {
		this.usernameView = usernameView;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}