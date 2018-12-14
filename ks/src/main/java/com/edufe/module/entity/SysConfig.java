package com.edufe.module.entity;

import java.io.Serializable;
/**
 * 系统配置表 实体类
 * @author yangchao
 *
 */
public class SysConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String systemName;  //名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
}
