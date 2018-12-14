package com.education.contants;

import java.util.Comparator;

import com.education.framework.util.CommonTools;

public class ModelConstants {

	/**
	 * 客户表属性
	 * @author yangchao
	 *
	 */
	public static class CustomerConst{
		public static final String[][] viewCols = {
			{"name","客户名称","1" ,"",  "width:8%;", "group1","","",""},
			{"kindView","客户类型","1", "hidden-480" ,"", "group1","","",""},
			{"province","省","1", "hidden-480" ,"", "group1","","",""},
			{"city","市","1", "hidden-480" ,"", "group1","","",""},
			{"district","区","0", "hidden-480" ,"", "group1","","",""},
			{"address","地址","1", "hidden-480" ,"", "group1","","",""},
			{"contactsUser","联系人","1", "hidden-480", "" ,"group1","","",""},
			{"cooperationCount","项目合作次数","0", "hidden-480", "" ,"group1","","",""}
		} ; 
	}
	
	/**
	 * 项目表属性
	 * @author yangchao
	 * 0： 属性名 ，1： 显示名称， 2： 默认列表显示， 3: class, 4: style, 5: 分组, 6: ext1(审批状态) ,7: ext2, 8: ext3 
	 */
	public static class ProjectConst{
		public static final String[][] viewCols = {
			{"code","项目编号","1", "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"name","项目名称","1", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"1","",""},
			{"ptypeView","项目类型","1", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"busiKindView","项目条线","1" , "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"stateView","项目状态","1", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"headMasterView","班主任","1", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView","副班主任","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView2","助管","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"trainUserCount","人数","1", "hidden-480" , "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"updateUserView","修改人","1", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"updateTime","修改时间","1", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"customerNameView","合作单位","0",  "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"contactsUser","联络人","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"pxObject","培训对象","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditFlagView","项目审批状态","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"coursePlan","上课地点","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"accountUnitView","进账单位","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"remark","备注","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			
			{"registerDateView","报到","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classBeginDateView","开班","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classEndDateView","结束","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"returnDateView","返程","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"trainDayCount","天数","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""}
			
//			,{"coursePlan","上课安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"logisticsPlan","后勤安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"projectReport","预算-决算","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"student","学员","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""}
		} ;
	}
	
	/**
	 * 项目审核表属性
	 * @author yangchao
	 * 0： 属性名 ，1： 显示名称， 2： 默认列表显示， 3: class, 4: style, 5: 分组, 6: ext1(审批状态) ,7: ext2（字典转换）, 8: ext3 
	 */
	public static class ProjectAuditConst{
		public static final String[][] viewCols = {
			{"kindView","审批类型","1", "" ,"width:10%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"code","项目编号","1", "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"name","项目名称","1", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"ptypeView","项目类型","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"busiKindView","项目条线","1" , "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"stateView","项目状态","1", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"headMasterView","班主任","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView","副班主任","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView2","助管","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"trainUserCount","人数","1", "hidden-480" , "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"updateUserView","修改人","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"updateTime","修改时间","0", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"customerNameView","合作单位","0",  "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"contactsUser","联络人","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"pxObject","培训对象","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditFlagView","项目审批状态","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"accountUnitView","进账单位","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"remark","备注","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditCreateTime","提交时间","1", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditCreateUserView","提交人","1", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditProcess","进度状态","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","1","",""},
			
			{"registerDateView","报到","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classBeginDateView","开班","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classEndDateView","结束","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"returnDateView","返程","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"trainDayCount","天数","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""}
			
//			,{"coursePlan","上课安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"logisticsPlan","后勤安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"projectReport","预算-决算","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"student","学员","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""}
		} ;
	}
	
	/**
	 * 周报表属性
	 * @author yangchao
	 * 0： 属性名 ，1： 显示名称， 2： 默认列表显示， 3: class, 4: style, 5: 分组, 6: ext1(审批状态) ,7: ext2, 8: ext3 
	 */
	public static class ProjectWeekReportConst{
		public static final String[][] viewCols = {
			{"stateView","项目状态","1", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"coursePlan","上课地点","1", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"code","项目编号","0", "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"name","项目名称","1", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"1","",""},
			
			{"registerDateView","报到","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classBeginDateView","开班","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classEndDateView","结束","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"returnDateView","返程","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"trainDayCount","天数","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			
			{"ptypeView","项目类型","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"busiKindView","项目条线","0" , "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"trainUserCount","人数","1", "hidden-480" , "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"headMasterView","班主任","1", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"subMaster","副班主任","1", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"updateUserView","修改人","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"updateTime","修改时间","0", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"customerNameView","合作单位","0",  "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"contactsUser","联络人","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"pxObject","培训对象","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditFlagView","项目审批状态","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"accountUnitView","进账单位","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"remark","备注","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""}
			
			
//			,{"coursePlan","上课安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"logisticsPlan","后勤安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"projectReport","预算-决算","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"student","学员","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""}
		} ;
	}
	
	/**
	 * 财务管理属性
	 * @author yangchao
	 * 0： 属性名 ，1： 显示名称， 2： 默认列表显示， 3: class, 4: style, 5: 分组, 6: ext1(审批状态) ,7: ext2, 8: ext3 
	 */
	public static class AccountConst{
		public static final String[][] viewCols = {
			{"code","项目编号","1", "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"name","项目名称","0", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"1","",""},
			{"ptypeView","项目类型","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"busiKindView","项目条线","0" , "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"stateView","项目状态","1", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"headMasterView","班主任","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"trainUserCount","人数","0", "hidden-480" , "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"updateUserView","修改人","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"updateTime","修改时间","0", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"customerNameView","合作单位","0",  "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"contactsUser","联络人","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"pxObject","培训对象","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"auditFlagView","项目审批状态","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"coursePlan","上课地点","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"remark","备注","0", "hidden-480" ,"width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			
			{"registerDateView","报到","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classBeginDateView","开班","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"classEndDateView","结束","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"returnDateView","返程","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"trainDayCount","天数","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"receMoney","预计收入","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"receJsMoney","应入账","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"paymentStateView","收款进展","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"netreceMoney","已入账(元)","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""},
			{"accountUnitView","进账单位","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group2","","",""}
			
//			,{"coursePlan","上课安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"logisticsPlan","后勤安排","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"projectReport","预算-决算","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""},
//			{"student","学员","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group-othersheet","","",""}
		} ;
	}
	
	public static class StudentConst{
		public static final String[][] viewCols = {
			{"truename","姓名","1", "" ,"width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"gender","性别","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","GENDER",""},
			{"certificateType","证件类型","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","CERT_TYPE",""},
			{"certificateNum","证件号码","1", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"telephone","联系电话","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"email","电子邮箱","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"company","所属单位","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"post","职务","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"birthdateView","生日","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""}
			
		} ;
	}
	
	public static class ProjectStudentConst{
		public static final String[][] viewCols = {
			{"truename","姓名","1", "" ,"width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;", "group1","","",""},
			{"gender","性别","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","GENDER",""},
			{"certificateType","证件类型","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","CERT_TYPE",""},
			{"certificateNum","证件号码","1", "hidden-480", "width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"telephone","联系电话","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"email","电子邮箱","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"company","所属单位","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"post","职务","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"birthdateView","生日","1", "hidden-480", "width:4%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""},
			{"reportNum","证书编号","1", "hidden-480", "width:5%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"","",""}
			
		} ;
	}
	
	/**
	 * 项目信息统计表属性
	 * @author yangchao
	 * 0： 属性名 ，1： 显示名称， 2： 默认列表显示， 3: class, 4: style, 5: 分组, 6: ext1(审批状态) ,7: ext2, 8: ext3(排序) 
	 */
	public static class ProjectInfoStatisticsConst{
		public static final String[][] viewCols = {
			{"busiKindView","项目条线","1" , "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"customerNameView","合作单位","0",  "hidden-480" ,"width:8%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"code","项目编号","1", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"1","",""},
			{"name","项目名称","1", "", "width:15%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1" ,"1","",""},
			{"stateView","项目状态","0", "hidden-480" , "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"registerDateView","报到","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"classBeginDateView","开班","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"classEndDateView","结束","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"returnDateView","返程","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"trainDayCount","天数","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"trainUserCount","人数","1", "hidden-480" , "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;","group1","","",""},
			{"headMasterView","班主任/负责","1", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView","副班主任","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"projectSubheadMasterView2","助管","0", "hidden-480", "width:7%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"receMoney","合同金额(万)","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"receJsMoney","应收金额(万)","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"netreceMoney","实收金额(万)","1", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"accountDate","入账日期","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"accountUnit","入账单位","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"planClassroomName","教室","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"dinnerAddress","就餐","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"dinnerType","就餐形式","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"hotelNameView","入住酒店","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"hotelBeginDateView","入住日期","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"hotelEndDateView","离店日期","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""},
			{"remark","备注","0", "hidden-480", "width:6%;table-layout:fixed;word-break: break-all; word-wrap: break-word;" ,"group1","","",""}
		} ;
	}
	
	public static String[][] getViewCols(String[][] viewCols){
		String[][] viewColsTemp = new String[viewCols.length][];
		for(int i = 0 ; i< viewCols.length ;i++){
			viewColsTemp[i] = viewCols[i].clone();
		}
//		Arrays.sort(viewColsTemp, new MyComprator());
		return viewColsTemp;
	}
	
	static class MyComprator implements Comparator {
	    public int compare(Object arg0, Object arg1) {
	        String t1=(String)arg0;
	        String t2=(String)arg1;
	        if(CommonTools.parseInt(t1) < CommonTools.parseInt(t2))
	            return 1;
	        else
	            return -1;
	    }
	}
}
