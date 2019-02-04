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
    <style type="text/css">
    	.form-horizontal .control-label {
		    float: left;
		    width: 80px;
		    padding-top: 5px;
		    text-align: right;
		}
		.form-horizontal .controls {
		    margin-left: 110px;
		}
    </style>
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
							题库管理
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
									<input type="hidden" id="courseId" name="courseId" value="${tkExamination.courseId }"/>
									<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<div class="row-fluid">
										<div class="span6">
											<div class="control-group"> 
											   <label class="control-label"> 
											       题型<span class="">*</span> 
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
										</div>
									</div>
									<div class="row-fluid">
										<div class="control-group"> 
										   <label class="control-label"> 
										       试题内容<span class="">*</span>
										   </label> 
										   <div class="controls">
										   		<textarea id="examinationContent" name="examinationContent" class="span11 wysihtml5 m-wrap required" rows="8" >${tkExamination.examinationContent}</textarea>
											    <span id="examinationContent_msgspan"></span>
										   </div> 
										</div> 
										
										<div class="control-group">
										   <label class="control-label">
										       <c:if test="${tkExamination.typeCode=='jiand' }">自动判分关键字<span class="">*</span></c:if>
										       <c:if test="${tkExamination.typeCode!='jiand' }">选项/答案<span class="">*</span></c:if>
										       <div>
										            <c:if test="${tkExamination.typeCode == 'danx' || tkExamination.typeCode == 'duox' || tkExamination.typeCode == 'tiank'}">
														<select id="optionNum" name="optionNum" class="span10" data-placeholder="选项个数" tabindex="1" onchange="optionNumChange();">
															<option value="1" ${tkExamination.optionNum==1?'selected=selected':'' }>1个</option>
															<option value="2" ${tkExamination.optionNum==2?'selected=selected':'' }>2个</option>
															<option value="3" ${tkExamination.optionNum==3?'selected=selected':'' }>3个</option>
															<option value="4" ${tkExamination.optionNum==4?'selected=selected':'' }>4个</option>
															<option value="5" ${tkExamination.optionNum==5?'selected=selected':'' }>5个</option>
															<option value="6" ${tkExamination.optionNum==6?'selected=selected':'' }>6个</option>
														</select>
													</c:if>
												</div>
										   </label> 
										   <div class="controls">
											   <c:if test="${tkExamination.typeCode=='danx' }">
											       <label class="radio line" id="op1">
											           <input type="radio" name="answer" value="a" ${ tkExamination.answer == 'a'?'checked=checked':'' }/>
											             A <input type="text" id="optionA" name="optionA" class="span10" value="${tkExamination.optionA}"/></label>
											       <label class="radio line" id="op2">
											           <input type="radio" name="answer" value="b" ${ tkExamination.answer == 'b'?'checked=checked':'' }/>
											             B <input type="text" id="optionB" name="optionB" class="span10" value="${tkExamination.optionB}"/></label>
											       <label class="radio line" id="op3">
											           <input type="radio" name="answer" value="c" ${ tkExamination.answer == 'c'?'checked=checked':'' }/>
											             C <input type="text" id="optionA" name="optionC" class="span10" value="${tkExamination.optionC}"/></label>
											       <label class="radio line" id="op4">
											           <input type="radio" name="answer" value="d" ${ tkExamination.answer == 'd'?'checked=checked':'' }/>
											             D <input type="text" id="optionA" name="optionD" class="span10" value="${tkExamination.optionD}"/></label>
											       <label class="radio line" id="op5">
											           <input type="radio" name="answer" value="e" ${ tkExamination.answer == 'e'?'checked=checked':'' }/>
											             E <input type="text" id="optionA" name="optionE" class="span10" value="${tkExamination.optionE}"/></label>
											       <label class="radio line" id="op6">
											           <input type="radio" name="answer" value="f" ${ tkExamination.answer == 'f'?'checked=checked':'' }/>
											             F <input type="text" id="optionA" name="optionF" class="span10" value="${tkExamination.optionF}"/></label>
											   </c:if>
											   <c:if test="${tkExamination.typeCode=='duox' }">
											       <input type="hidden" id="answer" name="answer" value="${tkExamination.answer}"/>
											       <label class="checkbox line" id="op1">
											           <input type="checkbox" name="duoxOptionsRadios" value="a" ${fn:contains(tkExamination.answer, 'a')?'checked=checked':'' }/>
											             A <input type="text" id="optionA" name="optionA" class="span10" value="${tkExamination.optionA}"/></label>
											       <label class="checkbox line" id="op2">
											           <input type="checkbox" name="duoxOptionsRadios" value="b" ${fn:contains(tkExamination.answer, 'b')?'checked=checked':'' }/>
											             B <input type="text" id="optionB" name="optionB" class="span10" value="${tkExamination.optionB}"/></label>
											       <label class="checkbox line" id="op3">
											           <input type="checkbox" name="duoxOptionsRadios" value="c" ${fn:contains(tkExamination.answer, 'c')?'checked=checked':'' }/>
											             C <input type="text" id="optionC" name="optionC" class="span10" value="${tkExamination.optionC}"/></label>
											       <label class="checkbox line" id="op4">
											           <input type="checkbox" name="duoxOptionsRadios" value="d" ${fn:contains(tkExamination.answer, 'd')?'checked=checked':'' }/>
											             D <input type="text" id="optionD" name="optionD" class="span10" value="${tkExamination.optionD}"/></label>
											       <label class="checkbox line" id="op5">
											           <input type="checkbox" name="duoxOptionsRadios" value="e" ${fn:contains(tkExamination.answer, 'e')?'checked=checked':'' }/>
											             E <input type="text" id="optionE" name="optionE" class="span10" value="${tkExamination.optionE}"/></label>
											       <label class="checkbox line" id="op6">
											           <input type="checkbox" name="duoxOptionsRadios" value="f" ${fn:contains(tkExamination.answer, 'f')?'checked=checked':'' }/>
											             F <input type="text" id="optionF" name="optionF" class="span10" value="${tkExamination.optionF}"/></label>
											       <script>
											           $('input[name="duoxOptionsRadios"]').click(
														  function(){ 
															  duoxOptionsRadiosChange();
															  console.log("duox answer", $("#answer").val());
															 
														  }	   
													    );
													    
													    function duoxOptionsRadiosChange(){
														    var answerTemp = "";
															$.each($('input[name="duoxOptionsRadios"]'),function(){
												                if(this.checked){
												                	answerTemp += $(this).val();
												                }
												            });
												            $("#answer").val(answerTemp);
														}
											       </script>
												</c:if>
												
												<c:if test="${tkExamination.typeCode=='pand' }">
											   	   <div class="control-group"> 
												   	   <div class="controls span11">
														   <label class="radio"><input type="radio" name="answer" value="正确" ${ tkExamination.answer == '正确'?'checked=checked':'' }/> 正确</label>
														   <label class="radio"><input type="radio" name="answer" value="错误" ${ tkExamination.answer == '错误'?'checked=checked':'' }/> 错误</label>
													   </div>
												   </div>
												</c:if>
												
												<c:if test="${tkExamination.typeCode=='tiank' }">
												   <input type="hidden" id="answer" name="answer" value="${tkExamination.answer}"/>
											       <label class="radio line" id="op1">(1) <input type="text" id="optionA" name="optionA" class="span10" value="${tkExamination.optionA}"/></label>
											       <label class="radio line" id="op2">(2) <input type="text" id="optionB" name="optionB" class="span10" value="${tkExamination.optionB}"/></label>
											       <label class="radio line" id="op3">(3) <input type="text" id="optionC" name="optionC" class="span10" value="${tkExamination.optionC}"/></label>
											       <label class="radio line" id="op4">(4) <input type="text" id="optionD" name="optionD" class="span10" value="${tkExamination.optionD}"/></label>
											       <label class="radio line" id="op5">(5) <input type="text" id="optionE" name="optionE" class="span10" value="${tkExamination.optionE}"/></label>
											       <label class="radio line" id="op6">(6) <input type="text" id="optionF" name="optionF" class="span10" value="${tkExamination.optionF}"/></label>
											   </c:if>
											   
											   <c:if test="${tkExamination.typeCode=='jiand' }">
											       <input id="answerTags" name="answer" type="text" class="m-wra tags" value="${tkExamination.answer }"/>
											       <div>请输入自动判分关键字，支持多个关键字，按Enter回车键录入完成。</div>
											       <script>
											            $('#answerTags').tagsInput({
												            width: '90%',
												            'onAddTag': function () {
												                //alert(1);
												            },
												        });
											       </script>
											   </c:if>
											</div>
												
											<div class="row-fluid" style="margin-top: 15px;">
												<div class="control-group span6"> 
												   <label class="control-label"> 
												       知识点
												   </label> 
												   <div class="controls"> 
												       <input id="knowledgePoint" name="knowledgePoint" class="span8 m-wrap" type="text" value="${tkExamination.knowledgePoint}" /> 
												   </div> 
												</div>
												<div class="control-group span6">
													<label class="control-label">难易度</label>
													<div class="controls">
														<c:forEach items="${difficultyList }" var="difficultyList">
															<label class="radio">
																<input type="radio" name="difficulty" value="${difficultyList.code }" ${difficultyList.code==tkExamination.difficulty?'checked':'' }/>
																${difficultyList.label }
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
											
											<div class="control-group"> 
											   <label class="control-label"> 
											       试题解析
											   </label> 
											   <div class="controls"> 
											       <textarea id="examinationDescription" name="examinationDescription" class="span11 wysihtml5 m-wrap" rows="4" >${tkExamination.examinationDescription}</textarea>
											   </div> 
											</div>
										</div>
									</div>
									<div class="row-fluid " style="text-align: center;">
										<div class="control-group form-actions clearfix" style="background-color: #fff;">
											
											<a class="btn blue" onclick="submit();" href="javascript:void();"><i class="icon-ok"></i>完成</a>
											&nbsp;&nbsp;
											<a class="btn" onclick="cancle();">取消</a>
				
										</div>
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
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/jquery.tagsinput.css" />
	<script type="text/javascript" src="${resPath}media/js/jquery.tagsinput.min.js"></script>
	<script>
		jQuery(document).ready(function() { 
		   $("#inputForm").validate();
		   App.init();
		   if (!jQuery().wysihtml5) {
	            return;
	        }

	        if ($('.wysihtml5').size() > 0) {
	            $('.wysihtml5').wysihtml5({
	                "stylesheets": ["assets/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
	            });
	        }
	        $("#typeCode").change(
			  function(){ 
				  typeCodeChange();
			  }	   
		    );
		    
		    optionNumChange();
		    
		});
		
		function cancle(){
			window.location = "${basePath}tkExamination/${tkExamination.courseId}";
		}
		
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
		
		function typeCodeChange(){
			$("#inputForm").attr('action','${basePath}tkExamination/changeTypeCode/${action}');
			$("#inputForm").submit();
		}
		
		function optionNumChange(){
		    var optionNum = parseInt($("#optionNum").val());
			for(var i=1;i<=6;i++){
				if(i <= optionNum){
					$("#op" +i).show();
				}else{
					$("#op" +i).hide();
				}
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
			if($("#examinationContent").val() == ""){
				alert("提示：请先录入试题内容！");
				return false;
			}
		
			if($("#typeCode").val() == 'tiank'){
			    var answerStr = addAnswerStr(1,$("#optionA").val()) + addAnswerStr(2,$("#optionB").val())  + addAnswerStr(3,$("#optionC").val())  + addAnswerStr(4,$("#optionD").val()) 
			    	+ addAnswerStr(5,$("#optionE").val()) + addAnswerStr(6,$("#optionF").val())
				$("#answer").val(answerStr);
		    }
			
			if($("#typeCode").val() == 'danx' || $("#typeCode").val() == 'duox' || $("#typeCode").val() == 'pand'){
				var v=$('input:radio[name="answer"]:checked').val();
				if(typeof(v) == "undefined" || v == ""){
					alert("提示：请先设置本题的答案！");
					return false;
				}
			}
			$("#inputForm").submit();
		}
		
		function addAnswerStr(index, s){
		    if(s == '') return "";
		    return " (" + index + ") " + s;
		}
	</script>
</body>
</html>