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
							考生信息批量导入
							<button type="button" class="btn" onclick="cancle();" style="float:right;margin-right: 2px;"><i class="icon-arrow-left"></i> 返回</button>
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
								<form id="inputForm" class="form-horizontal" action="${basePath}examStu/imp" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${exam.id }"/>
									<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
										<div>
											<span>
												<c:forEach items="${errorlist}" var="list"  >
													${list} 
													<br/>
												</c:forEach>
											</span>
										</div>
									</div>
									<div class="control-group" id="accountDiv" > 
									   <label class="control-label"> 
									      	导入文件<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input type="file" name="file" class="required"/>
									   </div> 
									</div>
									<div class="control-group" id="accountDiv" > 
									   <label class="control-label"> 
									   </label> 
									   <div class="controls">
									   		<a href="${resPath}template/examStuTemplate.xls" target="_blank">导入excel模板下载...</a>
									   </div> 
									</div>
									<div class="form-actions clearfix">
										<button class="btn blue" type="submit"><i class="icon-ok"></i>导入</button>
										&nbsp;&nbsp;
										<a class="btn" onclick="cancle();">返回</a>
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
	<script type="text/javascript" src="${resPath}media/js/chosen.jquery.min.js"></script> 
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/chosen.css" />
	<script>
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}examStu/${exam.id }";
		}
	</script>
</body>
</html>
