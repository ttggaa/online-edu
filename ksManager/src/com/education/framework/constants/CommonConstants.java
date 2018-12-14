package com.education.framework.constants;

public class CommonConstants {

	//导出类型定义
	public static final String DATE_TYPE = "DATE_TYPE";
	public static final String INT_TYPE = "INT_TYPE";
	public static final String STRING_TYPE = "STRING_TYPE";
	
	//可用状态
	public static final Integer USE_OR_UNUSE = 1;
	
	/**
	 * 密码重试次数
	 */
	public static final Integer RETTRY_COUNT = 3;
	
	//系统根节点定义
	public static final Integer SYS_ROOT = -1;
	
	/*命令参数分隔符*/
	public static final String CMD_SPLIT = ",";
	/*命令常量*/
	public static final String SHUTDOWN = "shutdown";
	public static final String STARTUP = "startup";
	public static final String REMOVE_DEPLOY_FILE = "removeDeployFile";  //删除临时部署文件命令
	public static final String DEPLOY = "deploy";  //部署

	public class WebInterFaceConst{
		public static final String SUCCESS = "SUCCESS";  //成功
		public static final String FAIL = "FAIL";  //成功
		public static final String TOKEN_AUTH_FAIL = "TOKEN_AUTH_FAIL";  //令牌认证失败
		public static final String PARAM_AUTH_FAIL = "PARAM_AUTH_FAIL";  //md5key认证失败
	}
	
	//选择状态颜色码
	public static final String SEL_BACKGROUND_COLOR_0 = "#93c47d";  //可用
	public static final String SEL_BACKGROUND_COLOR_1 = "#A52A2A";  //占用
	public static final String SEL_BACKGROUND_COLOR_2 = "#F08080";  //预选
	public static final String SEL_BACKGROUND_COLOR_3 = "#D0D0D0";  //不可用
	public static final String SEL_BACKGROUND_COLOR_4 = "#00BFFF";  //申请
	public static final String SEL_BACKGROUND_COLOR_5 = "#4169E1";  //合班
}
