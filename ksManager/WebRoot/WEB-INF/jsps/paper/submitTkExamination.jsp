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
							向总题库提交试题
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
									<i class="icon-reorder"></i> 以下为本总题库中不存在的试题，点击确定，即可提交导入至总题库!
								</div>
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}paper/submitTkExaminationSave" method="post" >
									<input type="hidden" name="id" value="${paper.id }"/>
									<input type="hidden" name="courseId" value="${paper.courseId }"/>
									<input type="hidden" name="businessId" value="${paper.businessId }"/>
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
									       题库集<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="sourceId" name="sourceId" class="span2 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${quesSourceList }" var="quesSourceList">
										   			<option value="${quesSourceList.id }" >${quesSourceList.sourceName }</option>
										   		</c:forEach>
									   		</select> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <table class="table table-striped table-bordered table-hover" id="sample_1">
											<thead>
												<tr>
													<th style="width:40px;">序号</th>
													<th>试题类型</th> 
													<th style="width:220px;">试题内容</th> 
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${questionList}" var="list" varStatus="status">
													<tr>
														<td>${list.id }</td>
														<td>${list.typeCode }</td>
														<td>${list.examinationContent }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div> 
									
									
									<div class="form-actions clearfix">
										
										<button class="btn blue" type="submit"><i class="icon-ok"></i>确定提交至总题库</button>
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
