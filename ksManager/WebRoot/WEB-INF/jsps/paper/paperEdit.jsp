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
<body class="page-header-fixed page-sidebar-closed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							试卷管理
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

								<form id="inputForm" class="form-horizontal" action="${basePath}paper/${action}" method="post" >
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
									</div> 
									 -->
									<div class="control-group"> 
									   <label class="control-label"> 
									       及格分<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="passScore" class="span6 m-wrap required digits" type="text" value="${paper.passScore}"> 
									   </div> 
									</div>
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       等级<span class="required">*</span> 
									   </label> 
									   <div class="controls">
									   		<select id="levelId" name="levelId" class="span2 m-wrap">
									       		<option value="" ></option>
										   		<c:forEach items="${levelList }" var="levelList">
										   			<option value="${levelList.id }" ${levelList.id==paper.levelId?'selected=selected':'' }>${levelList.levelName }</option>
										   		</c:forEach>
									   		</select> 
									   </div> 
									</div> -->
									
									<input type="hidden" name="businessId" value="1"/>
									<input type="hidden" name="levelId" value="1"/>
									<div class="control-group"> 
									   <label class="control-label"> 
									       状态<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									   	   <div class="danger-toggle-button">
												<input type="checkbox" class="toggle" ${paper.state=='1'?'checked=checked':''} name="state" value="1"/>
											</div>
								   			可用状态时，试卷方可正式生效使用!
									   </div> 
									</div> 
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       支持类型<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input type="checkbox" name="kindCs" value="1" ${paper.kindCs=='1'?'checked=checked':''} />支持C/S客户端卷
									       <input type="checkbox" name="kindMobile" value="1" ${paper.kindMobile=='1'?'checked=checked':''}/>支持移动端
									   </div> 
									</div>  -->
									<c:if test="${action=='update'}">
										<div class="control-group"> 
										   <label class="control-label"> 
										        总题数
										   </label> 
										   <div class="controls"> 
										       <span class="label label-success">${paper.quesCount}</span>
										   </div> 
										</div>
										<div class="control-group"> 
										   <label class="control-label"> 
										        总分
										   </label> 
										   <div class="controls"> 
										       <span class="label label-success">${paper.totalScore}</span>
										   </div> 
										</div>
										<div class="control-group"> 
										   <label class="control-label"> 
										        使用率
										   </label> 
										   <div class="controls"> 
										       <span class="label label-success">${paper.useCount}</span>
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
			window.location = "${basePath}paper";
		}
	</script>
</body>
</html>
