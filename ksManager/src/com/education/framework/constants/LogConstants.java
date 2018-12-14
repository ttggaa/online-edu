package com.education.framework.constants;

public class LogConstants {

	/** LOG TYPE 定义 **/
	//登录类型
	public static final String LOGIN_TYPE = "LOGIN_TYPE";
	//项目创建类型
	public static final String PROJECT_CREATE_TYPE = "PROJECT_CREATE";
	public static final String PROJECT_UPDATE_TYPE = "PROJECT_UPDATE";
	public static final String PROJECT_DEL_TYPE = "PROJECT_DEL";
	
	//项目审批类型
	public static final String AUDIT_SUBMIT_TYPE = "AUDIT_SUBMIT_TYPE";
	public static final String AUDIT_3_TYPE = "AUDIT_3_TYPE"; //审核通过
	public static final String AUDIT_9_TYPE = "AUDIT_9_TYPE"; //审核不通过
	
	//财务入账类型
	public static final String ACCOUNT_MONEY_TYPE = "ACCOUNT_MONEY_TYPE";
	
	/** LOG LEVEL 级别定义**/
	//危险
	public static final String LOG_LEVEL_DANGER = "LOG_LEVEL_DANGER";
	//错误
	public static final String LOG_LEVEL_ERROR = "LOG_LEVEL_ERROR";
	//警告
	public static final String LOG_LEVEL_WARN = "LOG_LEVEL_WARN";
	//日志
	public static final String LOG_LEVEL_INFO = "LOG_LEVEL_INFO";
	//调试
	public static final String LOG_LEVEL_DEBUG = "LOG_LEVEL_DEBUG";
	
}
