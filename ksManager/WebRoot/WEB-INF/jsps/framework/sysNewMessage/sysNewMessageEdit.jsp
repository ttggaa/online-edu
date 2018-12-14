<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/dict" prefix="dict"%>
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
							查看消息动态
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

								<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
									<button class="close" data-dismiss="alert"></button>
									<span>${MESSAGE }</span>
								</div>
								<div class="control-group"> 
								   <label class="control-label"> 
								       消息动态
								   </label> 
								   <div class="controls"> 
								      &nbsp;&nbsp;&nbsp;&nbsp; ${sysLogs.msgContent} 
								   </div> 
								</div> 
								<div class="control-group"> 
								   <label class="control-label"> 
								       读取状态
								   </label> 
								   <div class="controls"> 
								   	   &nbsp;&nbsp;&nbsp;&nbsp;<dict:label code="${sysLogs.readFlag }" field="MSG_READ_STATE"/>
								   </div> 
								</div> 
								<div class="control-group"> 
								   <label class="control-label"> 
								       创建时间
								   </label> 
								   <div class="controls"> 
								       &nbsp;&nbsp;&nbsp;&nbsp;${sysLogs.createTime}
								   </div> 
								</div> 
								<div class="form-actions clearfix">
									<a class="btn" onclick="cancle();">返回</a>
								</div>
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
		   $("#inputForm").validate();
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}framework/sysNewMessage";
		}
	</script>
</body>
</html>
