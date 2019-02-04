package com.education.domain;

public class ImportReturnMessage {
	private int messageCode ;
	private String retrunMessage ;
	private String typeName;
	private int rowNum ;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}
	public String getRetrunMessage() {
		return retrunMessage;
	}
	public void setRetrunMessage(String retrunMessage) {
		this.retrunMessage = retrunMessage;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
