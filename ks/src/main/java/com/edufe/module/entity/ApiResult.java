package com.edufe.module.entity;

public class ApiResult {

	int code;
	Object data;
	String authToken;
	String toast;
	String attach;
	
	public ApiResult() {
	}

	public ApiResult(int code, Object data) {
		this.code = code;
		this.data = data;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getToast() {
		return toast;
	}

	public void setToast(String toast) {
		this.toast = toast;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
