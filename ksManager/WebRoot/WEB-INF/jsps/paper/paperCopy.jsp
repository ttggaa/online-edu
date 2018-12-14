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
							试卷复制
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
									<i class="icon-reorder"></i> 基本信息,以下信息为复制粘贴后的信息，请仔细添写!
								</div>
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}paper/pastePaper" method="post" >
									<input type="hidden" name="id" value="${paper.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       试卷名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="paperName" class="span6 m-wrap required" type="text" value="${paper.paperName}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       课程<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="courseId" name="courseId" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${courseList }" var="courseList">
										   			<option value="${courseList.id }" ${courseList.id==paper.courseId?'selected=selected':'' }>${courseList.courseName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       运营商<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="businessId" name="businessId" class="span2 m-wrap required" style="width: 300px;">
										   		<c:forEach items="${businessList }" var="businessList">
										   			<option value="${businessList.id }" ${businessList.id==paper.businessId?'selected=selected':'' }>${businessList.businessName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div>  -->
									<input type="hidden" name="businessId" value="1"/>
									<div class="control-group"> 
									   <label class="control-label"> 
									       及格分<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="passScore" class="span6 m-wrap required digits" type="text" value="${paper.passScore}"> 
									   </div> 
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       类型<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="opterType" name="opterType" class="span2 m-wrap required" style="width: 300px;">
										   		<option value="copy" selected="selected">复制</option>
										   		<option value="cut">剪切</option>
									   		</select>
									   </div> 
									</div>
									<div class="form-actions clearfix">
										
										<button class="btn blue" type="submit"><i class="icon-ok"></i>复制</button>
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
		   $("#inputForm").validate();
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}paper";
		}
	</script>
</body>
</html>
