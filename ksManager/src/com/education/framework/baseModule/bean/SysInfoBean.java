package com.education.framework.baseModule.bean;

import java.io.Serializable;

public class SysInfoBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private String sysName;
	private String sysSummary;
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysSummary() {
		return sysSummary;
	}
	public void setSysSummary(String sysSummary) {
		this.sysSummary = sysSummary;
	}
	
}