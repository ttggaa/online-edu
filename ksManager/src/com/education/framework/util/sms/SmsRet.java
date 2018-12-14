package com.education.framework.util.sms;

/**
 * 短信发送返回对象
 * @author yangchao
 *
 */
public class SmsRet {

	private int statusCode;
	
	private String responseBody;
	
	private boolean sendSuccessFlag;
	
	public boolean isSendSuccessFlag() {
		return sendSuccessFlag;
	}

	public void setSendSuccessFlag(boolean sendSuccessFlag) {
		this.sendSuccessFlag = sendSuccessFlag;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
}
