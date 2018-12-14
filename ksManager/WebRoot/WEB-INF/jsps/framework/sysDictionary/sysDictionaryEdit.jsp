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
							数据字典管理
							<small>${action=='create'?'新增数据字典信息':'修改数据字典信息'}</small>
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

								<form id="inputForm" class="form-horizontal" action="${basePath}framework/sysDictionary/${action}" method="post" >
									<input type="hidden" name="id" value="${sysDictionary.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       分组KEY(fieldId)<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="fieldId" class="span6 m-wrap required" type="text" value="${sysDictionary.fieldId}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       分组名称(fieldName)<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="fieldName" class="span6 m-wrap required" type="text" value="${sysDictionary.fieldName}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       字典值(code)<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="code" class="span6 m-wrap required" type="text" value="${sysDictionary.code}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       字典描述(label)<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="label" class="span6 m-wrap required" type="text" value="${sysDictionary.label}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       备注
									   </label> 
									   <div class="controls"> 
									       <textarea name="remark" class="span10 m-wrap" rows="5" >${sysDictionary.remark}</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       排序编号
									   </label> 
									   <div class="controls"> 
									       <input name="orderIndex" class="span6 m-wrap required" type="text" value="${action=='create'?0:sysDictionary.orderIndex}"/> 
									   </div> 
									</div> 

									
									<div class="form-actions clearfix">
										
										<button class="btn blue" type="submit"><i class="icon-ok"></i>完成</button>
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
		function cancle(){
			window.location = "${basePath}framework/sysDictionary";
		}
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		});
	</script>
</body>
</html>
