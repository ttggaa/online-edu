package com.education.domain.extend;

import java.io.Serializable;

public class ExamRankBase implements Serializable {

	private static final long serialVersionUID = 2L;
	private int sumCount; //考生总数
	private int loginCount; //登录人数
	private int noLoginCount; //未登录人数
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public int getNoLoginCount() {
		return noLoginCount;
	}
	public void setNoLoginCount(int noLoginCount) {
		this.noLoginCount = noLoginCount;
	}
	
}