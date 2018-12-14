package com.education.framework.baseModule.domain ; 

import java.io.Serializable;
import java.util.List;

/* 
 *  
 * Tue Mar 06 09:23:18 CST 2018 
 */  

public class SysMenu implements Serializable {  

  private static final long serialVersionUID = 2L; 
  private Integer id;
    // datebase colume is create_time 
    private String createTime; 

    // datebase colume is create_user 
    private int createUser; 

    // datebase colume is icon 
    private String icon; 

    // datebase colume is link_url 
    private String linkUrl; 

    // datebase colume is name 
    private String name; 

    // datebase colume is order_index 
    private int orderIndex; 

    // datebase colume is pid 
    private int pid; 

    // datebase colume is remark 
    private String remark; 

    // datebase colume is update_time 
    private String updateTime; 

    // datebase colume is update_user 
    private int updateUser; 
    
    private List<SysMenu> childList;
    
    public List<SysMenu> getChildList() {
		return childList;
	}

	public void setChildList(List<SysMenu> childList) {
		this.childList = childList;
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


    public String getIcon(){ 
        return this.icon; 
    } 

    public void setIcon(String icon){ 
        this.icon=icon; 
    } 


    public String getLinkUrl(){ 
        return this.linkUrl; 
    } 

    public void setLinkUrl(String linkUrl){ 
        this.linkUrl=linkUrl; 
    } 


    public String getName(){ 
        return this.name; 
    } 

    public void setName(String name){ 
        this.name=name; 
    } 


    public int getOrderIndex(){ 
        return this.orderIndex; 
    } 

    public void setOrderIndex(int orderIndex){ 
        this.orderIndex=orderIndex; 
    } 


    public int getPid(){ 
        return this.pid; 
    } 

    public void setPid(int pid){ 
        this.pid=pid; 
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