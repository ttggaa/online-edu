package com.edufe.openApi;

import java.io.Serializable;

/* 
 *  
 * 商户注册返回消息对象
 */

public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 2L;

	private String type;
	private String name;
	private String telephone;
	private String email;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}