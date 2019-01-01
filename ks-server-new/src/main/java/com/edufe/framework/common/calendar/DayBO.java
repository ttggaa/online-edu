package com.edufe.framework.common.calendar;

public class DayBO {

	private String date ;    //日期参数
	
	private String signFlag ;    //

	private String day;
	
	private String isCurrMonth;   //是否为本月日期 1为本月 
	
	private String isToday;
	
	public String getIsToday() {
		return isToday;
	}

	public void setIsToday(String isToday) {
		this.isToday = isToday;
	}

	public String getIsCurrMonth() {
		return isCurrMonth;
	}

	public void setIsCurrMonth(String isCurrMonth) {
		this.isCurrMonth = isCurrMonth;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
  	
}
