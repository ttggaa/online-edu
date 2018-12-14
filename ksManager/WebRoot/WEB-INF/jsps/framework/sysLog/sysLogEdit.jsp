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
<body class="page-header-fixed page-sidebar-closed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							访问日志管理
						</h3>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> 基本信息
								</div>
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}system/sysLogs/${action}" method="post" >
									<input type="hidden" name="id" value="${sysLogs.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       日志类型
									   </label> 
									   <div class="controls"> 
									       <input name="logType" class="span6 m-wrap required" type="text" value="${sysLogs.logType}" readonly="readonly"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       日志内容
									   </label> 
									   <div class="controls"> 
									       <input name="logContent" class="span6 m-wrap required" type="text" value="${sysLogs.logContent}" readonly="readonly"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       创建人
									   </label> 
									   <div class="controls"> 
									       <input name="createUser" class="span6 m-wrap required" type="text" value="${sysLogs.createUser}" readonly="readonly"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       创建时间
									   </label> 
									   <div class="controls"> 
									       <input name="createTime" class="span6 m-wrap required" type="text" value="${sysLogs.createTime}" readonly="readonly"> 
									   </div> 
									</div> 
									<div class="form-actions clearfix">
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
		function cancle(){
			window.location = "${basePath}framework/sysLog";
		}
	</script>
</body>
</html>
