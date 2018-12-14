package com.education.framework.util.exportExcel;

import jxl.format.Colour;

public class ExcelHeader {

	private String fieldsCode;
	private String fieldsName;
	private String fieldsKind;
	private int width;
	private String dictFieldId;
	private Colour headBackground;
	
	public ExcelHeader(){
	}
			
	public ExcelHeader(String fieldsCode,String fieldsName,int width){
		this.setFieldsCode(fieldsCode);
		this.setFieldsName(fieldsName);
		this.setWidth(width);
	}
	
	public ExcelHeader(String fieldsCode,String fieldsName,int width, String fieldKinds){
		this.setFieldsCode(fieldsCode);
		this.setFieldsName(fieldsName);
		this.setFieldsKind(fieldKinds);
		this.setWidth(width);
	}  
	public ExcelHeader(String fieldsCode,String fieldsName,String dictFieldId,int width){
		this.setFieldsCode(fieldsCode);
		this.setFieldsName(fieldsName);
		this.setWidth(width);
		this.setDictFieldId(dictFieldId);
	}
	
	public ExcelHeader(String fieldsCode,String fieldsName,int width,String dictFieldId,Colour headBackground){
		this.setFieldsCode(fieldsCode);
		this.setFieldsName(fieldsName);
		this.setWidth(width);
		this.setDictFieldId(dictFieldId);
		this.setHeadBackground(headBackground);
	}
	public String getFieldsCode() {
		return fieldsCode;
	}
	public void setFieldsCode(String fieldsCode) {
		this.fieldsCode = fieldsCode;
	}
	public String getFieldsName() {
		return fieldsName;
	}
	public void setFieldsName(String fieldsName) {
		this.fieldsName = fieldsName;
	}
	public String getFieldsKind() {
		return fieldsKind;
	}
	public void setFieldsKind(String fieldsKind) {
		this.fieldsKind = fieldsKind;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getDictFieldId() {
		return dictFieldId;
	}
	public void setDictFieldId(String dictFieldId) {
		this.dictFieldId = dictFieldId;
	}
	public Colour getHeadBackground() {
		return headBackground;
	}
	public void setHeadBackground(Colour headBackground) {
		this.headBackground = headBackground;
	}
	
}
