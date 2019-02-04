package com.edufe.module.entity;

import java.io.Serializable;

/* 
 *  
 * 商家
 */

public class CacheBusiness implements Serializable {

	private static final long serialVersionUID = 2L;
	private Integer id;
	private String businessName;
	private String kind;
	private String logo;
	private String advertLogo;
	private String domain;
	private String authFlag;
	private String proName;
	private String summary;
	private String background;
	private int integrate;
	private int overdraft;
	private String member;
	private String footerViewFlag;
	private int onlineUser;
	private String account;
	private int costPrice;
	
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getOverdraft() {
		return overdraft;
	}
	public void setOverdraft(int overdraft) {
		this.overdraft = overdraft;
	}
	public int getOnlineUser() {
		return onlineUser;
	}
	public void setOnlineUser(int onlineUser) {
		this.onlineUser = onlineUser;
	}
	
	public String getFooterViewFlag() {
		return footerViewFlag;
	}
	public void setFooterViewFlag(String footerViewFlag) {
		this.footerViewFlag = footerViewFlag;
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