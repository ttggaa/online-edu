package com.education.framework.baseModule.bean;

import java.io.Serializable;

/* 
 *  
 * 商家
 */

public class BusinessBean implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer id;
	private String businessName;
	private String kind;
	private String logo;
	private String advertLogo;
	private String domain;
	private String authFlag;
	private String authExpire;
	private String account;
	private int examAvailableCount;
	private String proName;
	private String summary;
	private String background;
	private int integrate;
	private String member;
	private int onlineUser;
	
	private String telephone;
	private String mail;
	private String businessRemark;
	private int examUserNum;
	private int apiDayCount;
	
	public String getAuthExpire() {
		return authExpire;
	}
	public void setAuthExpire(String authExpire) {
		this.authExpire = authExpire;
	}
	public int getExamUserNum() {
		return examUserNum;
	}
	public void setExamUserNum(int examUserNum) {
		this.examUserNum = examUserNum;
	}
	public int getApiDayCount() {
		return apiDayCount;
	}
	public void setApiDayCount(int apiDayCount) {
		this.apiDayCount = apiDayCount;
	}
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
	public int getOnlineUser() {
		return onlineUser;
	}
	public void setOnlineUser(int onlineUser) {
		this.onlineUser = onlineUser;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getExamAvailableCount() {
		return examAvailableCount;
	}
	public void setExamAvailableCount(int examAvailableCount) {
		this.examAvailableCount = examAvailableCount;
	}
	public int getIntegrate() {
		return integrate;
	}
	public void setIntegrate(int integrate) {
		this.integrate = integrate;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getAuthFlag() {
		return authFlag;
	}
	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAdvertLogo() {
		return advertLogo;
	}
	public void setAdvertLogo(String advertLogo) {
		this.advertLogo = advertLogo;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}