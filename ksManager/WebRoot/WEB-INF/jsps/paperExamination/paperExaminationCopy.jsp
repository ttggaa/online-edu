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
							试题复制
						</h3>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">

					<div class="span12">
						<c:if test="${quesTypeAnalyseList != null}">
							<div class="control-group">
								<c:set var="quesCountInit" value="0"></c:set>
								<c:set var="sumScoreInit" value="0"></c:set>
								<c:forEach items="${quesTypeAnalyseList}" var="quesTypeAnalyse" varStatus="status">
									<c:set var="quesCountInit" value="${quesCountInit + quesTypeAnalyse.quesCount}"></c:set>
									<c:set var="sumScoreInit" value="${sumScoreInit + quesTypeAnalyse.quesSumScore}"></c:set>
									<span class="label label-warning">
										${quesTypeAnalyse.quesTypeName }:${quesTypeAnalyse.quesCount }题,共${quesTypeAnalyse.quesSumScore}分
									</span>
								</c:forEach>
								<span class="label label-success">
									总量：<c:out value="${quesCountInit}"></c:out>题&nbsp;/&nbsp;总分：<c:out value="${sumScoreInit}"></c:out>分
								</span>
							</div>
						</c:if>
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> 基本信息
								</div>
								<div class="tools">
									<a class="btn blue" onclick="submit();"><i class="icon-copy"></i>复制</a>
										&nbsp;&nbsp;
									<a class="btn" onclick="cancle();">取消</a>
								</div>
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}paperExamination/paste" method="post" >
									<input type="hidden" name="id" value="${paperExamination.id }"/>
									<input type="hidden" id="dshTypeCode" name="dshTypeCode" value="${tkExamination.dshTypeCode }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									     	  试卷<span class="required">*</span> 
									     	  <br />选择目标方式卷进行复制
									   </label> 
									   <div class="controls"> 
									       <select id="paperId" name="paperId" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${paperList }" var="paperList">
										   			<option value="${paperList.id }" ${paperList.id==paperExamination.paperId?'selected=selected':'' }>${paperList.paperName }</option>
										   		</c:forEach>
								   		   </select>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       题型<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="typeCode" name="typeCode" class="span6 m-wrap required" >
									       		<option value="" ></option>
										   		<c:forEach items="${typeList }" var="typeList">
										   			<option value="${typeList.typeCode }" ${typeList.typeCode==paperExamination.typeCode?'selected=selected':'' }>${typeList.typename }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div>
									<div class="control-group" id="accountDiv" style="display: none;"> 
									   <label class="control-label"> 
									      	实操账套<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="accountCode" name="accountCode" class="span6 m-wrap">
										   		<dict:selectOption field="account" selCode="${paperExamination.accountCode}" empty="true"/>
									   		</select>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       试题内容<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <textarea name="examinationContent" class="span10 m-wrap required" rows="5" >${paperExamination.examinationContent}</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       标准答案<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <textarea id="answer" name="answer" class="span10 m-wrap required" rows="5" >${paperExamination.answer}</textarea>
									   </div> 
									   <div class="controls"> 
									   	   <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('a');" class="btn mini">a</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('b');" class="btn mini">b</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('c');" class="btn mini">c</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('d');" class="btn mini">d</a>
									       </div>
									       <div class="btn-group">
												<button class="btn mini purple dropdown-toggle" data-toggle="dropdown">多选 <i class="icon-angle-down"></i>
												</button>
												<ul class="dropdown-menu">
													<li><a href="javascript:void();" onclick="setAnswer('a;b;c;d');" >a;b;c;d</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;b;c');" >a;b;c</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;b;d');" >a;b;d</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;c;d');" >a;c;d</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;b');" >a;b</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;c');" >a;c</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('a;d');" >a;d</a></li>
													<li class="divider"></li>
													<li><a href="javascript:void();" onclick="setAnswer('b;c;d');" >b;c;d</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('b;c');" >b;c</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('b;d');" >b;d</a></li>
													<li class="divider"></li>
													<li><a href="javascript:void();" onclick="setAnswer('c;d');" >c;d</a></li>
												</ul>
										   </div>
										   <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('true');" class="btn mini">true</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('false');" class="btn mini">false</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="buildAlAnswer();" class="btn mini">案例</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="buildJfAnswer();" class="btn mini">计算分析</a>
									       </div>
									       <div class="btn-group">
									       		<a href="#myModal1" role="button" class="btn mini" data-toggle="modal" onclick="initModal();">实操</a>
									       </div>
									        <!-- Modal -->
											<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
													<h3 id="myModalLabel1">会计电算化-实务操作题答案生成器</h3>
												</div>
												<div class="modal-body" style="height: 400px;">
													<%@ include file="/WEB-INF/jsps/dsh/buildDshAnswer.jsp"%>
												</div>
												<div class="modal-footer">
													<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
												</div>
											</div>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       分数<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input id="defaultPoint" name="defaultPoint" class="span6 m-wrap required" type="text" value="${paperExamination.defaultPoint}"> 
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
									       试题描述
									   </label> 
									   <div class="controls"> 
									       <textarea name="examinationDescription" class="span10 m-wrap" rows="5" >${paperExamination.examinationDescription}</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       审核状态<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="auditState" name="auditState" class="span2 m-wrap required">
									       		<dict:selectOption field="auditState" selCode="${paperExamination.auditState}" empty="false"/>
									   		</select> 
									   </div> 
									</div>
									<div class="form-actions clearfix">
										
										<a class="btn blue" onclick="submit();"><i class="icon-copy"></i>复制</a>
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
			$("#typeCode").change(
			  function(){ 
				  typeCodeChange();
			  }	   
		   );
		   
		   if($("#defaultPoint").val() == ""){
			   typeCodeChange();
		   }
		   
		   if($("#typeCode").val() == 'sc'){
			   $("#accountDiv").show();
		   }else{
			   $("#accountDiv").hide();
		   }
		   initAnswer();
		   $("#inputForm").validate();
		   App.init();
		});
		
		function typeCodeChange(){	
			if($("#typeCode").val() == 'sc'){
			  $("#defaultPoint").val('4');
			  $("#accountDiv").show();
		    }else if($("#typeCode").val() == 'pand' || $("#typeCode").val() == 'danx'){
		       $("#defaultPoint").val('1');
		    }else if($("#typeCode").val() == 'jf' || $("#typeCode").val() == 'al'){
		      $("#defaultPoint").val('10');
		    }else{
		    	$("#defaultPoint").val('2');
		    	$("#accountDiv").hide();
		    }
		}
		
		function setAnswer(s){
			$("#answer").val(s);
		}
		function submit(){
			if($("#typeCode").val() == 'sc' && $("#accountCode").val()==""){
			   alert("账套不能为空，请选择实务操作题账套！");
			   return false;
		    }
			$("#inputForm").submit();
		}
		
		function cancle(){
			window.location = "${basePath}paperExamination?paperId=" + $("#paperId").val() ;
		}
		function buildAlAnswer(){
			var dg;
			dg = $.dialog({
			    lock: true,
			    content: 'url:${basePath}/paperExamination/buildAlAnswer',
			    icon: 'error.gif',
			    init:function(){
			    	dg.content.init($("#answer").val());
			    },
			    ok: function () {
			    	var r = dg.content.ok();
			    	setAnswer(r);
			        return true;
			    },
			    cancel: true
			});
		}
		function buildJfAnswer(){
			var dg;
			dg = $.dialog({
			    lock: true,
			    title:' 计算分析题答案生成器工具',
			    content: 'url:${basePath}/paperExamination/buildJfAnswer',
			    icon: 'error.gif',
			    width: '700px',
			    height: 500,
			    init:function(){
			    	dg.content.init($("#answer").val());
			    },
			    ok: function () {
			    	var r = dg.content.ok();
			    	setAnswer(r);
			        return true;
			    },
			    cancel: true
			});
		}
	</script>
</body>
</html>
