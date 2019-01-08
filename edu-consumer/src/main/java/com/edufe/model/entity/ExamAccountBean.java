package com.edufe.model.entity;

import java.io.Serializable;

/**
 * 考试商家账户对象
 *
 * @author yangchao
 * @since 1.0 2018-12-26
 */
public class ExamAccountBean implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private String type; //add : 增   /dec减 
	private String money; //金额
	private Integer businessId;
	private Integer examStuId; 
	private String loginIP;
	private String loginTime;
	public ExamAccountBean(){}
	public ExamAccountBean(String type,Integer examStuId,Integer businessId, String money,String loginIP,String loginTime){
		this.type = type;
		this.money = money;
		this.examStuId = examStuId;
		this.businessId = businessId;
		this.loginIP = loginIP;
		this.loginTime = loginTime;
	}
	
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public Integer getExamStuId() {
		return examStuId;
	}
	public void setExamStuId(Integer examStuId) {
		this.examStuId = examStuId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
}