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
	<script type="text/javascript" charset="utf-8" src="${resPath}ueditor1_4_3_2-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${resPath}ueditor1_4_3_2-utf8-jsp/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${resPath}ueditor1_4_3_2-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
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
									<i class="icon-reorder"></i> 试题管理
								</div>
								<div style="float: right;">
									<a class="btn blue" onclick="submit();" href="javascript:void();"><i class="icon-ok"></i>完成</a>
									&nbsp;&nbsp;
									<a class="btn" onclick="cancle();">取消</a>
								</div>
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}tkExamination/${action}" method="post" >
									<input type="hidden" id="qid" name="id" value="${tkExamination.id }"/>
									<input type="hidden" id="dshTypeCode" name="dshTypeCode" value="${tkExamination.dshTypeCode }"/>
									
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<c:if test="${tkExamination.id > 0 }">
										<div class="control-group"> 
										   <label class="control-label"> 
										       试题ID 
										   </label> 
										   <div class="controls"> 
										       ${tkExamination.id }
										   </div> 
										</div>
									</c:if>
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
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       知识点
									   </label> 
									   <div class="controls"> 
									       <input id="knowledgePoint" name="knowledgePoint" class="span2 m-wrap" type="text" value="${tkExamination.knowledgePoint}" /> 
									   </div> 
									</div> -->
									<div class="control-group"> 
									   <label class="control-label"> 
									       题型<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="typeCode" name="typeCode" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${typeList }" var="typeList">
										   			<option value="${typeList.typeCode }" ${typeList.typeCode==tkExamination.typeCode?'selected=selected':'' }>${typeList.typename }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									<!-- 
									<div class="control-group" id="accountDiv" style="display: none;"> 
									   <label class="control-label"> 
									      	实操账套<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="accountCode" name="accountCode" class="span6 m-wrap">
										   		<dict:selectOption field="account" selCode="${tkExamination.accountCode}" empty="true"/>
									   		</select>
									   </div> 
									</div>   -->
									<div class="control-group"> 
									   <label class="control-label"> 
									       试题内容<span class="required">*</span>
									   </label> 
									   <div class="controls"> 
									   		<div class="tabbable tabbable-custom boxless">
												<ul class="nav nav-tabs">
													<li id="tab_1_li" class="${tkExamination.examinationContentHtml ==null?'active':''}"><a href="#tab_1" data-toggle="tab">纯文本</a></li>
													<li id="tab_2_li" class="${tkExamination.examinationContentHtml ==null?'':'active'}"><a href="#tab_2" data-toggle="tab">富文本</a></li>
												</ul>
											</div>
											<div class="tab-content">
												<div class="tab-pane ${tkExamination.examinationContentHtml ==null?'active':''}" id="tab_1">
											        <textarea id="examinationContent" name="examinationContent" class="span10 m-wrap" rows="7" >${tkExamination.examinationContent}</textarea>
											        <span id="examinationContent_msgspan"></span>
												</div>
												<div class="tab-pane ${tkExamination.examinationContentHtml ==null?'':'active'}" id="tab_2">
													<script id="editor" name="examinationContentHtml" type="text/plain" style="width:100%;height:200px;">${tkExamination.examinationContentHtml}</script>
												</div>
											</div>
									   </div> 
									</div> 
									<div class="control-group" id="optionDiv" style="display: none;"> 
									   <label class="control-label"> 
									       选项 A
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionA" name="optionA" class="span5 m-wrap" rows="2">${tkExamination.optionA}</textarea>
									   </div>
									   <label class="control-label"> 
									       选项 B
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionB" name="optionB" class="span5 m-wrap" rows="2">${tkExamination.optionB}</textarea>
									   </div>
									   <label class="control-label"> 
									       选项 C
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionC" name="optionC" class="span5 m-wrap" rows="2">${tkExamination.optionC}</textarea>
									   </div>
									   <label class="control-label"> 
									       选项 D
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionD" name="optionD" class="span5 m-wrap" rows="2">${tkExamination.optionD}</textarea>
									   </div>
									   <label class="control-label"> 
									       选项 E
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionE" name="optionE" class="span5 m-wrap" rows="2">${tkExamination.optionE}</textarea>
									   </div>
									   <label class="control-label"> 
									       选项 F
									   </label> 
									   <div class="controls"> 
									       <textarea id="optionF" name="optionF" class="span5 m-wrap" rows="2">${tkExamination.optionF}</textarea>
									   </div>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       答案<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <textarea id="answer" name="answer" class="span10 m-wrap" rows="2">${tkExamination.answer}</textarea>
									   </div>
									   <div class="controls"> 
									   	   <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('a','danx');" class="btn mini">a</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('b','danx');" class="btn mini">b</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('c','danx');" class="btn mini">c</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('d','danx');" class="btn mini">d</a>
									       </div>
									       <div class="btn-group">
												<button class="btn mini purple dropdown-toggle" data-toggle="dropdown">多选 <i class="icon-angle-down"></i>
												</button>
												<ul class="dropdown-menu">
													<li><a href="javascript:void();" onclick="setAnswer('abcd','duox');" >abcd</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('abc','duox');" >abc</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('abd','duox');" >abd</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('acd','duox');" >acd</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('ab','duox');" >ab</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('ac','duox');" >ac</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('ad','duox');" >ad</a></li>
													<li class="divider"></li>
													<li><a href="javascript:void();" onclick="setAnswer('bcd','duox');" >bcd</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('bc','duox');" >bc</a></li>
													<li><a href="javascript:void();" onclick="setAnswer('bd','duox');" >bd</a></li>
													<li class="divider"></li>
													<li><a href="javascript:void();" onclick="setAnswer('cd','duox');" >cd</a></li>
												</ul>
										   </div>
										   <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('正确','pand');" class="btn mini">正确</a>
									       </div>
									       <div class="btn-group">
									       		<a href="javascript:void();" onclick="setAnswer('错误','pand');" class="btn mini">错误</a>
									       </div>
									   </div>  
									</div> 
									<!-- 
									<div class="control-group" style="display: none;"> 
									   <label class="control-label social-icons "> 
									       试题解析描述
									       <li><a href="javascript:void();" onclick="setDefaultRemark();" data-original-title="amazon" class="amazon"></a></li>
									   </label> 
									   <div class="controls"> 
									   		<div class="tabbable tabbable-custom boxless">
												<ul class="nav nav-tabs">
													<li id="tabDesc_1_li" class="active"><a href="#tabDesc_1" data-toggle="tab">纯文本</a></li>
													<li id="tabDesc_2_li" class=""><a href="#tabDesc_2" data-toggle="tab">富文本</a></li>
												</ul>
											</div>
											<div class="tab-content">
												<div class="tab-pane active" id="tabDesc_1">
											        <textarea id="examinationDescription" name="examinationDescription" class="span10 m-wrap" rows="4">${tkExamination.examinationDescription}</textarea>
												</div>
												<div class="tab-pane" id="tabDesc_2">
													<script id="editor2" type="text/plain" style="width:100%;height:200px;">${tkExamination.examinationDescription}</script>
												</div>
											</div>
									   </div> 
									</div>  
									<div class="control-group"> 
									   <label class="control-label"> 
									       分数<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input id="defaultPoint" name="defaultPoint" class="span2 m-wrap required number" type="text" value="${tkExamination.defaultPoint}"> 
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
										   			<option value="${difficultyList.code }" ${difficultyList.code==tkExamination.difficulty?'selected=selected':'' }>${difficultyList.label }</option>
										   		</c:forEach>
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
										   			<option value="${quesSourceList.id }" ${quesSourceList.id==tkExamination.sourceId?'selected=selected':'' }>${quesSourceList.sourceName }</option>
										   		</c:forEach>
									   		</select> 
									   </div> 
									</div>  -->
									<!--  
									<div class="control-group"> 
									   <label class="control-label"> 
									       Html试题内容
									   </label> 
									   <div class="controls"> 
									       <script id="editor" name="examinationContentHtml" type="text/plain" style="width:100%;height:200px;">${tkExamination.examinationContentHtml}</script>
									   </div> 
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       审核状态
									   </label> 
									   <div class="controls"> 
									       <select id="auditState" name="auditState" class="span2 m-wrap">
									       		<dict:selectOption field="auditState" selCode="${tkExamination.auditState}" empty="false"/>
									   		</select> 
									   </div> 
									</div> -->
									<div class="form-actions clearfix">
										
										<a class="btn blue" onclick="submit();" href="javascript:void();"><i class="icon-ok"></i>完成</a>
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
		var option = {
	        initialContent: '',
	        initialFrameWidth:"100%",  //初始化编辑器宽度,默认1000，可以设置成百分比
	        initialFrameHeight:300,  //初始化编辑器高度,默认320
	        autoClearinitialContent:false  //是否自动清除编辑器初始内容
	     };
		UE.getEditor('editor',option);
		UE.getEditor('editor2',option);
		
		jQuery(document).ready(function() { 
			var v = $("#examinationDescription").val().replace(/[\r\n]/g,"");
			if(v.indexOf("<p>") >= 0){
				$("#tabDesc_1_li").removeClass("active");
				$("#tabDesc_2_li").addClass("active");
				$("#tabDesc_1").removeClass("active");
				$("#tabDesc_2").addClass("active");
			}
		   $("#inputForm").validate();
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}tkExamination";
		}
	</script>
	
	<script>
		jQuery(document).ready(function() { 
		   typeCodeChange();
		   $("#typeCode").change(
			  function(){ 
				  typeCodeChange();
			  }	   
		   );
		   
		   function checkChar(s){
			   var m = s.toUpperCase().replace("．",".").trim();
			   m = m.replace("　"," ");
			   if(m.length > 2) return false;
			   var temp = m.substr(0, 2);
			   if(temp == "A." || temp == "B." || temp == "C." || temp == "D."){
				  return true;
			   }else{
				  return false;
			   }
		   }
		   
		   $("#examinationContent").blur(
			  function(){ 
				  $("#examinationContent_msgspan").html("请稍候...");
				  var cid = $("#courseId").val();
				  var qid = $("#qid").val();
				  var content = $.trim($("#examinationContent").val());
				  content = content.replace("查看参考答案： 【我要提问】", "");
				  var ctTempArr = content.split("【您的答案】");
				  if(ctTempArr.length > 1) {
					  content = ctTempArr[0].trim();
					  $("#examinationDescription").val(ctTempArr[1].trim());
					  var edArr = ctTempArr[1].trim().split("\n");
					  if(edArr.length > 1){
						  var eAnswerContent = edArr[0].replace("【正确答案】","").toUpperCase();
						  if($("#typeCode").val() == "danx"){
							  if(eAnswerContent == "A" || eAnswerContent == "B" || eAnswerContent == "C" || eAnswerContent == "D"){
								  $("#answer").val(eAnswerContent.toLocaleLowerCase());
							  }
						  }else if($("#typeCode").val() == "duox"){
							  var answerBuff = "";
							  var alp = "";
							  for(var j=0;j<eAnswerContent.length;j++){
								  answerBuff += alp + eAnswerContent.charAt(j);
								  alp = ";";
							  }
							  $("#answer").val(answerBuff);
						  }else if($("#typeCode").val() == "pand"){
							  if(eAnswerContent == "Y"){
								  $("#answer").val("正确");
							  }else{
								  $("#answer").val("错误");
							  }
						  }
					  }
				  }
				  var contentArr = content.split("\n");
				  var contentTemp = "";
				  var lp = "";
				  var hbFlag = false;
				  for(var i=0;i<contentArr.length;i++){
					  if(i>1){
						  hbFlag = checkChar(contentArr[i-1]);
					  }
					  //var currTemp = contentArr[i].toUpperCase().replace("．",".");
					  if(i>0 && hbFlag && !checkChar(contentArr[i])){
						  var temp = contentArr[i].replace(/[\r\n]/g,""); 
						  contentTemp = contentTemp + temp;
					  }else{
						  contentTemp = contentTemp + lp + contentArr[i];
					  }
					  lp = "\r\n";
				  }
				  if(contentTemp != "") content = contentTemp;
				  if(content == "") {
					  $("#examinationContent_msgspan").html("");
					  return ;
				  }
				  $("#examinationContent").val(content);
				  $.ajax( {    
					    url:'${basePath}tkExamination/checkQuesDup',
					    data:{
					    	qid : qid,
					    	cid : cid,
					    	content : content
					    },    
					    type:'post',    
					    cache:false,    
					    dataType:'json',    
					    success:function(data) {    
					        var c = parseInt(data);
					        if(c > 0){
					        	$("#examinationContent_msgspan").html("试题已存在，重复！");
					        	$("#examinationContent").val("");
					        }else{
					        	$("#examinationContent_msgspan").html("");
					        }
					     },    
					     error : function() {    
					          // view("异常！");    
					    	 $("#examinationContent_msgspan").html("异常"); 
					     }    
					});
			  }	   
		   );
		   
		   $("#examinationDescription").blur(
			  function(){ 
				  var content = $.trim($("#examinationDescription").val());
				  $("#examinationDescription").val(content);
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
		
			if($("#typeCode").val() == 'danx' || $("#typeCode").val() == 'duox'){
				$("#optionDiv").show();
			}else{
				$("#optionDiv").hide();
			}
			
			if($("#typeCode").val() == 'sc'){
			  $("#defaultPoint").val('4');
			  $("#accountDiv").show();
		    }else if($("#typeCode").val() == 'pand' || $("#typeCode").val() == 'danx'){
		       $("#defaultPoint").val('1');
		    }else if($("#typeCode").val() == 'jf' || $("#typeCode").val() == 'al'){
		      $("#defaultPoint").val('10');
		    }else if($("#typeCode").val() == 'jd'){
		      $("#defaultPoint").val('6');
		    }else if($("#typeCode").val() == 'zh'){
		      $("#defaultPoint").val('12');
		    }else{
		    	$("#defaultPoint").val('2');
		    	$("#accountDiv").hide();
		    }
		}
		
		function setAnswer(s,qtype){
			var tc = $("#typeCode").val();
			if(tc == qtype){
				$("#answer").val(s);
				setDefaultRemark();
			}else{
				alert("当前试题题型与答案不匹配！");				
			}
		}
		function submit(){
			if($("#typeCode").val() == 'sc' && $("#accountCode").val()==""){
			   alert("账套不能为空，请选择实务操作题账套！");
			   return false;
		    }
			if(!UE.getEditor('editor').hasContents() && $("#examinationContent").val()==""){
				alert("试题内容不能为空，请输入试题内容！");
				return false;
			}
			if($("#typeCode").val() != 'zh' && $("#typeCode").val() != 'jd' && $("#typeCode").val() != 'jf2'){
				if($("#answer").val()==""){
				   alert("答案不能为空，请添写完整后再提交！");
				   return false;
				}
		    }
			
			if($("#tab_2_li").hasClass("active")){
				var v = UE.getEditor('editor').getContentTxt();
				$("#examinationContent").val(v);
			}
			if($("#tabDesc_2_li").hasClass("active")){
				var v = UE.getEditor('editor2').getContent();
				$("#examinationDescription").val(v);
			}
			$("#inputForm").submit();
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
			    	setAnswer(r,"al");
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
			    	dg.content.init($("#examinationContent").val(),$("#answer").val(), $("#examinationDescription").val());
			    },
			    ok: function () {
			    	var r = dg.content.ok();
			    	setAnswer(r,"jf");
			        return true;
			    },
			    cancel: true
			});
		}
		function setDefaultRemark(){
			var description = $("#examinationDescription").val();
			var descriptionArr = description.split("\n");
			if(descriptionArr.length == 1) {
				var answer = $("#answer").val();
				answer = answer.toUpperCase();
				$("#examinationDescription").val("答案：" + answer);  
			}
		}
	</script>
</body>
</html>
