package com.education.framework.baseModule.bean ; 

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.education.framework.baseModule.domain.SysMenu;
import com.education.framework.baseModule.domain.SysMenuFunc;

/* 
 *  
 * Tue Mar 06 09:23:18 CST 2018 
 */  

public class SysMenuBean implements Serializable {  

    private static final long serialVersionUID = 2L; 
    private Integer id;
    private String name; 
    private int pid; 
    private String remark; 
    private String menuAuth ;//菜单权限
    private String funcAuth;
    private List<SysMenuFunc> menuFuncList;
    private List<SysMenuBean> childList;
    
    public List<SysMenuBean> getChildList() {
		return childList;
	}

	public void setChildList(List<SysMenuBean> childList) {
		this.childList = childList;
	}
	public List<SysMenuFunc> getMenuFuncList() {
		return menuFuncList;
	}
	public void setMenuFuncList(List<SysMenuFunc> menuFuncList) {
		this.menuFuncList = menuFuncList;
	}
	
	public void setMenuFuncList(List<SysMenuFunc> menuFuncList, Map<Integer,Integer> roleFuncMap) {
		if(null != menuFuncList){
			for(SysMenuFunc m : menuFuncList){
				if(roleFuncMap.containsKey(m.getFuncCode())){
					m.setAuthFlag("1");
				}else{
					m.setAuthFlag("0");
				}
			}
		}
		this.menuFuncList = menuFuncList;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMenuAuth() {
		return menuAuth;
	}
	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}
	public String getFuncAuth() {
		return funcAuth;
	}
	public void setFuncAuth(String funcAuth) {
		this.funcAuth = funcAuth;
	}

}