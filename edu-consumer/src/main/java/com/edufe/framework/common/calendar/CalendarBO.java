package com.edufe.framework.common.calendar;

public class CalendarBO {

	private String date ;    //日期参数
	
	private String week ;    //保存操作类 
  	
	//是否为其它月份数据 1为其它月份数据
	private String dispFlag;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDispFlag() {
		return dispFlag;
	}

	public void setDispFlag(String dispFlag) {
		this.dispFlag = dispFlag;
	}
}
