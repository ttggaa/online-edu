<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>${systemName}</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<%@ include file="/WEB-INF/includes/common-component-edit.jsp"%>
</head>
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							我的考试
						</h3>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}examStu/${action}" method="post" >
									<input type="hidden" name="id" value="${examStu.id }"/>
									<input type="hidden" name="examId" value="${eid }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       准考证号<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input id="idcard" name="idcard" class="span6 m-wrap" type="text" value="${examStu.idcard}" placeholder="必填项，长度4~16位"/> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       姓名<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input id="truename" name="truename" class="span6 m-wrap" type="text" value="${examStu.truename}" placeholder="必填项" size="10"/> 
									   </div> 
									</div> 
									<c:if test="${action == 'create'}">
										<div class="control-group"> 
										   <label class="control-label"> 
										      类型
										   </label> 
										   <div class="controls"> 
										   	   <c:if test="${business.account <= 0}">
										   	   		<span class="badge badge-warning">您的账户余额不足，当前仅支持创建测试类型的考生（测试考生可免费使用）</span>
										   	   		<input type="hidden" name="testFlag" value="1"/>
										   	   </c:if>
										   	   <c:if test="${business.account > 0}">
											       <label class="checkbox">
													  <input type="checkbox" value="1" name="testFlag"/> 是否为测试考生（测试考生可免费使用）
												   </label>
											   </c:if>  
										   </div> 
										</div> 
									</c:if>
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       身份证号
									   </label> 
									   <div class="controls"> 
									       <input name="identitycode" class="span6 m-wrap" type="text" value="${examStu.identitycode}"> 
									   </div> 
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       考生照片
									   </label> 
									   <div class="controls"> 
									       <input name="photo" class="span6 m-wrap" type="text" value="${examStu.photo}"> 
									   </div> 
									</div> 
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       exam_siteid<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examSiteid" class="span6 m-wrap required" type="text" value="${examStu.examSiteid}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       exam_sitename<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examSitename" class="span6 m-wrap required" type="text" value="${examStu.examSitename}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       exam_roomid<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examRoomid" class="span6 m-wrap required" type="text" value="${examStu.examRoomid}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       exam_roomname<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examRoomname" class="span6 m-wrap required" type="text" value="${examStu.examRoomname}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       loginip<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="loginip" class="span6 m-wrap required" type="text" value="${examStu.loginip}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       login_time<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="loginTime" class="span6 m-wrap required" type="text" value="${examStu.loginTime}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       seatnum<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="seatnum" class="span6 m-wrap required" type="text" value="${examStu.seatnum}"> 
									   </div> 
									</div> 
									
									 -->
									
									<div class="form-actions clearfix">
										
										<button class="btn blue" type="button" onclick="theSave();"><i class="icon-ok"></i>完成</button>
										&nbsp;&nbsp;
										<a class="btn" onclick="cancle();">取消</a>
			
									</div>
								</form>
							</div>

						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
	<script src="${resPath}media/js/app.js"></script>   
	<script>
		jQuery(document).ready(function() { 		   
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}examStu?map['eid']=${eid }";
		}
		function theSave(){
			if($("#idcard").val() == ""){
				alert("准考证号不能为空!");
				return false;
			}
			if($("#idcard").val().length < 4 || $("#idcard").val().length > 16){
				alert("准考证号必须4~16位长度范围!");
			}
			if($("#truename").val() == ""){
				alert("姓名不能为空!");
				return false;
			}
			$("#inputForm").submit();
		}
	</script>
</body>
</html>
