/**
 * OutParam.java
 * 
 * 类 名 称: OutParam
 * 功    能: 存储过程用输出参数类
 * 
 * 版本     创建日期         作者     部门       内容
 * Ver1.0  Jul 23, 2009    赵恩升   Edufe:TD   新建
 * 
 * Copyright(c) 2008---2013 Dufetech All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.edufe.framework.common;

public class OutParam {
	private int position;
	private int type;
	
	public OutParam(int position, int type) {
		this.position = position;
		this.type= type;
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
