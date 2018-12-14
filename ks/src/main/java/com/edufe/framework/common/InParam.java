/**
 * InParam.java
 * 
 * 类 名 称: InParam
 * 功    能: 存储过程用输入参数类
 * 
 * 版本     创建日期         作者     部门       内容
 * Ver1.0  Jul 23, 2009    赵恩升   Edufe:TD   新建
 * 
 * Copyright(c) 2008---2013 Dufetech All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.edufe.framework.common;

public class InParam {
	private int position;
	private Object paramObject;
	
	public InParam(int position, Object paramObject) {
		this.position = position;
		this.paramObject = paramObject;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the paramObject
	 */
	public Object getParamObject() {
		return paramObject;
	}
	/**
	 * @param paramObject the paramObject to set
	 */
	public void setParamObject(Object paramObject) {
		this.paramObject = paramObject;
	}
}