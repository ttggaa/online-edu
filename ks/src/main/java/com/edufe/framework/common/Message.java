/**
 * Message.java
 * 
 * 类 名 称: Message
 * 功    能: Message辅助类
 * 
 * 版本     创建日期        作者     部门      内容
 * Ver1.0  Aug 4, 2009    赵恩升  Edufe:TD   新建
 * 
 * Copyright(c) 2008---2013 Dufetech All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.edufe.framework.common;

public class Message {
	private boolean right;			//正确消息还是错误消息
	private String alertMessage;	//报出的消息内容
	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(boolean right) {
		this.right = right;
	}
	/**
	 * @return the alertMessage
	 */
	public String getAlertMessage() {
		return alertMessage;
	}
	/**
	 * @param alertMessage the alertMessage to set
	 */
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
}
