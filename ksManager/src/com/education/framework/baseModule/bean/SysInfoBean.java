package com.education.framework.baseModule.bean;

import java.io.Serializable;

public class SysInfoBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private String sysName;
	private String sysSummary;
	private String background;
	private String telephone;
	private String mail;
	private String businessRemark;
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBusinessRemark() {
		return businessRemark;
	}
	public void setBusinessRemark(String businessRemark) {
		this.businessRemark = businessRemark;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
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