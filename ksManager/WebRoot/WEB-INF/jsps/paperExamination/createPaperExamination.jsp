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
							试卷管理管理
						</h3>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<form id="inputForm" class="form-horizontal" action="${basePath}paperExamination/${action}" method="post" >
				<input type="hidden" name="id" value="${paperExamination.id }"/>
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					
					<div class="span12">
						
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> 试题
								</div>
							</div>

							<div class="portlet-body form">
								
									<div class="control-group"> 
									   <label class="control-label"> 
									       题型<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="typeCode" name="typeCode" class="span6 m-wrap required" onchange="typeCodeChange();">
									       		<option value="" ></option>
										   		<c:forEach items="${typeList }" var="typeList">
										   			<option value="${typeList.typeCode }" ${typeList.typeCode==paperExamination.typeCode?'selected=selected':'' }>${typeList.typename }</option>
										   		</c:forEach>
									   		</select>
									   		<input id="typeName" name="typeName" type="hidden" value="${paperExamination.typeName}">
									   </div> 
									</div>  
									<div class="control-group"> 
									   <label class="control-label"> 
									       试卷内容<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <textarea name="examinationContent" class="span10 m-wrap required" rows="3">${paperExamination.examinationContent}</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       试题描述
									   </label> 
									   <div class="controls"> 
									       <textarea name="examinationDescription" class="span10 m-wrap" rows="3">${paperExamination.examinationDescription}</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       标准答案<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="answer" class="span6 m-wrap required" type="text" value="${paperExamination.answer}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       分数<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="defaultPoint" class="span6 m-wrap required" type="text" value="${paperExamination.defaultPoint}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       难易度<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="difficulty" name="difficulty" class="span2 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${difficultyList }" var="difficultyList">
										   			<option value="${difficultyList.code }" ${difficultyList.code==paperExamination.difficulty?'selected=selected':'' }>${difficultyList.label }</option>
										   		</c:forEach>
									   		</select> 
									   </div> 
									</div> 
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       章节<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="chapterId" name="chapterId" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${chapterList }" var="chapterList">
										   			<option value="${chapterList.id }" ${chapterList.id==paperExamination.chapterId?'selected=selected':'' }>${chapterList.chapterName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									
									<div class="form-actions clearfix">
										
										<button class="btn blue" type="submit"><i class="icon-ok"></i>完成</button>
										&nbsp;&nbsp;
										<a class="btn" onclick="cancle();">取消</a>
			
									</div>
							</div>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${resPath}media/js/app.js"></script>   
	<script>
		function cancle(){
			window.location = "${basePath}paperExamination";
		}
		
		function typeCodeChange(){
			var checkText=$("#typeCode").find("option:selected").text();  	
			$("#typeName").val(checkText);
		}
		
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		   
		   $("#courseId").change(function(){
			  var courseId = $("#courseId").val();
			  $.get("${basePath}resCourse/findChapterAjax/"+courseId,function(data,status){
			     var json = eval(data);
				 $(json).each(function(index,element){
					$("#chapterId").append("<option value="+element.id+">"+element.chapterName+"</option>");
				 });
				 $("#chapterId").val(${paperExamination.chapterId});
			  });
		   });
		});
		
	</script>
</body>
</html>
