package com.education.framework.baseModule.domain ; 

import java.io.Serializable;


public class SysMenuFunc implements Serializable {  

    private static final long serialVersionUID = 2L; 
    private Integer funcCode;
    private String funcName;
    
    private Integer menuId;
    private String authFlag;//授权状态
    
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(Integer funcCode) {
		this.funcCode = funcCode;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getAuthFlag() {
		return authFlag;
	}
	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	} 

}