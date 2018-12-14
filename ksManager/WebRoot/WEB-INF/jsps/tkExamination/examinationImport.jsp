<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib prefix="dict" uri="/dict"%>

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
							试题管理
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
									<i class="icon-reorder"></i> 批量导入
								</div>
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}tkExamination/imp" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${tkExamination.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>
											<c:forEach items="${errorlist}" var="list"  >
												第${list.rowNum}行 ,${list.retrunMessage} 
												<br/>
											</c:forEach>

										</span>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       课程<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="courseId" name="courseId" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${courseList }" var="courseList">
										   			<option value="${courseList.id }" ${courseList.id==tkExamination.courseId?'selected=selected':'' }>${courseList.courseName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									<div class="control-group" id="accountDiv" style="display: none;"> 
									   <label class="control-label"> 
									      	实操账套<br /><span class="required">导入实务操作题时需选择账套</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="accountCode" name="accountCode" class="span6 m-wrap">
										   		<dict:selectOption field="account" selCode="${tkExamination.accountCode}" empty="true"/>
									   		</select>
									   </div> 
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       题库集<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="sourceId" name="sourceId" class="span2 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${quesSourceList }" var="quesSourceList">
										   			<option value="${quesSourceList.id }">${quesSourceList.sourceName }</option>
										   		</c:forEach>
									   		</select> 
									   </div> 
									</div> 
									<div class="control-group" id="accountDiv" > 
									   <label class="control-label"> 
									      	导入文件<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input type="file" name="file"/>
									   </div> 
									</div>
									<div class="control-group" id="accountDiv" > 
									   <label class="control-label"> 
									   </label> 
									   <div class="controls"> 
									       <a href="${resPath}template/questionImpTemplate.xls" target="_blank">导入excel模板下载...</a>
									   </div> 
									</div>
									<div class="form-actions clearfix">
										<button class="btn blue" type="submit"><i class="icon-ok"></i>导入</button>
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
			window.location = "${basePath}tkExamination";
		}
	</script>
</body>
</html>
