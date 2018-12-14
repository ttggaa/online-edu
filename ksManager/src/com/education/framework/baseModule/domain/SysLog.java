package com.education.framework.baseModule.domain ; 

import java.io.Serializable; 

public class SysLog implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is access_ip 
    private String accessIp; 

    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is log_content 
    private String logContent; 

    // datebase colume is log_type 
    private String logType; 
    private String msgKind;
    private String loginname;
    private String truename;
    
    private String msgNewFlag;
    private String createTimeView;
    
    public String getMsgKind() {
		return msgKind;
	}

	public void setMsgKind(String msgKind) {
		this.msgKind = msgKind;
	}

	public String getCreateTimeView() {
		return createTimeView;
	}

	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}

	public String getMsgNewFlag() {
		return msgNewFlag;
	}

	public void setMsgNewFlag(String msgNewFlag) {
		this.msgNewFlag = msgNewFlag;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessIp(){ 
        return this.accessIp; 
    } 

    public void setAccessIp(String accessIp){ 
        this.accessIp=accessIp; 
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


    public String getLogContent(){ 
        return this.logContent; 
    } 

    public void setLogContent(String logContent){ 
        this.logContent=logContent; 
    } 


    public String getLogType(){ 
        return this.logType; 
    } 

    public void setLogType(String logType){ 
        this.logType=logType; 
    } 


}