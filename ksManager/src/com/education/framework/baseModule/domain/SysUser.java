package com.education.framework.baseModule.domain ; 

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

/* 
 *  
 * Tue Mar 06 09:22:51 CST 2018 
 */  

public class SysUser implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is audit_date 
    private String auditDate; 

    // datebase colume is audit_flag 
    private String auditFlag; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is email 
    private String email; 

    // datebase colume is entry_time 
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date entryTime; 

    // datebase colume is ext1 
    private String ext1; 

    // datebase colume is ext2 
    private String ext2; 

    // datebase colume is ext3 
    private int ext3; 

    // datebase colume is gender 
    private String gender; 

    // datebase colume is leave_time 
    private String leaveTime; 

    // datebase colume is loginname 
    private String loginname; 

    // datebase colume is org_id 
    private Integer orgId = 0; 

    // datebase colume is passwd 
    private String passwd; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is state 
    private String state; 

    // datebase colume is telephone 
    private String telephone; 

    // datebase colume is truename 
    private String truename; 

    // datebase colume is update_time 
    private String updateTime; 

    // datebase colume is update_user 
    private int updateUser; 

    // datebase colume is wechat_openid 
    private String wechatOpenid; 

    private List<SysRole> roleList;
    private List<SysMenu> menuList;
    private List<String> menuListStr;
    private Map<Integer,Integer> funcMap;//用户功能权限MAP
    private String oldpasswd;
    private String roles;
    private Integer orgPid;
    private Integer businessId;
    
    public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public List<String> getMenuListStr() {
		return menuListStr;
	}

	public void setMenuListStr(List<String> menuListStr) {
		this.menuListStr = menuListStr;
	}

	public Integer getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(Integer orgPid) {
		this.orgPid = orgPid;
	}

	public Map<Integer, Integer> getFuncMap() {
		return funcMap;
	}

	public void setFuncMap(Map<Integer, Integer> funcMap) {
		this.funcMap = funcMap;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuditDate(){ 
        return this.auditDate; 
    } 

    public void setAuditDate(String auditDate){ 
        this.auditDate=auditDate; 
    } 


    public String getAuditFlag(){ 
        return this.auditFlag; 
    } 

    public void setAuditFlag(String auditFlag){ 
        this.auditFlag=auditFlag; 
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


    public String getEmail(){ 
        return this.email; 
    } 

    public void setEmail(String email){ 
        this.email=email; 
    } 


    public Date getEntryTime(){ 
        return this.entryTime; 
    } 

    public void setEntryTime(Date entryTime){ 
        this.entryTime=entryTime; 
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


    public String getGender(){ 
        return this.gender; 
    } 

    public void setGender(String gender){ 
        this.gender=gender; 
    } 


    public String getLeaveTime(){ 
        return this.leaveTime; 
    } 

    public void setLeaveTime(String leaveTime){ 
        this.leaveTime=leaveTime; 
    } 


    public String getLoginname(){ 
        return this.loginname; 
    } 

    public void setLoginname(String loginname){ 
        this.loginname=loginname; 
    } 


    public Integer getOrgId(){ 
        return this.orgId; 
    } 

    public void setOrgId(Integer orgId){ 
        this.orgId=orgId; 
    } 


    public String getPasswd(){ 
        return this.passwd; 
    } 

    public void setPasswd(String passwd){ 
        this.passwd=passwd; 
    } 


    public String getRemark(){ 
        return this.remark; 
    } 

    public void setRemark(String remark){ 
        this.remark=remark; 
    } 


    public String getState(){ 
        return this.state; 
    } 

    public void setState(String state){ 
        this.state=state; 
    } 


    public String getTelephone(){ 
        return this.telephone; 
    } 

    public void setTelephone(String telephone){ 
        this.telephone=telephone; 
    } 


    public String getTruename(){ 
        return this.truename; 
    } 

    public void setTruename(String truename){ 
        this.truename=truename; 
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


    public String getWechatOpenid(){ 
        return this.wechatOpenid; 
    } 

    public void setWechatOpenid(String wechatOpenid){ 
        this.wechatOpenid=wechatOpenid; 
    } 


}