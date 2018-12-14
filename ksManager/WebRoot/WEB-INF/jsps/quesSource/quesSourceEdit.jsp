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
							题集管理
							<small>${action=='create'?'新增信息':'修改信息'}</small>
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

								<form id="inputForm" class="form-horizontal" action="${basePath}quesSource/${action}" method="post" >
									<input type="hidden" name="id" value="${quesSource.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       题集名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="sourceName" class="span6 m-wrap required" type="text" value="${quesSource.sourceName}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       备注
									   </label> 
									   <div class="controls"> 
									       <input name="remark" class="span6 m-wrap" type="text" value="${quesSource.remark}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       可用状态
									   </label> 
									   <div class="controls"> 
									       <div class="danger-toggle-button">
												<input type="checkbox" class="toggle" ${quesSource.state=='1'?'checked=checked':''} name="state" value="1"/>
											</div>
									   </div> 
									</div> 
									
									<c:if test="${!empty quesSourceList}">
										<div class="control-group"> 
										   <label class="control-label"> 
										       题集题量统计分析
										   </label> 
										   <div class="controls"> 
										       <table class="table table-striped table-bordered table-hover" id="sample_1">
													<thead>
														<tr>
															<th>科目名称</th>
															<th>题型</th> 
															<th>题量</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${quesSourceList}" var="list" varStatus="status">
															<tr class="odd gradeX">
																<td>${list.courseName }</td>											
																<td>${list.typeCodeName }</td>											
																<td>${list.num }</td>											
																<td>
																	<c:if test="${list.typeCode != 'sc' }">
																		<a class="edit" href="javascript:void();" onclick="quesCountChange('${list.sourceId}','${list.courseId}','${list.typeCode}');">题量调整</a>
																	</c:if>
																</td>											
															</tr>
														</c:forEach>
													</tbody>
												</table>
										   </div> 
										</div> 
									</c:if>
																		
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
	<script type="text/javascript" src="${resPath}media/js/jquery.toggle.buttons.js"></script>
	<script>
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		   
		   $('.danger-toggle-button').toggleButtons({
	            style: {
	                enabled: "info",
	                disabled: ""
	            }
	       });
		});
		function cancle(){
			window.location = "${basePath}quesSource";
		}
	</script>
</body>
</html>
