<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="dict" uri="/dict"%>
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
	<div class="page-container row-fluid">
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							组卷设定
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
									<i class="icon-reorder"></i> 组卷策略信息
								</div>
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}paper/buildPaperSave" method="post" >
									<input type="hidden" id="quesCount" name="quesCount" value="0"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       课程ID<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="courseId" name="courseId" class="span6 m-wrap required">
									       		<option value="" ></option>
										   		<c:forEach items="${courseList }" var="courseList">
										   			<option value="${courseList.id }" ${courseList.id == courseId?'selected=selected':'' }>${courseList.courseName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       试卷名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="paperName" class="span6 m-wrap required" type="text" value="${paper.paperName }"> 
									   </div> 
									</div> 
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       单场考试总时间<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examSumTime" class="span6 m-wrap required" type="text" value="${paper.pracmain.examSumTime }"> 
									   </div> 
									</div> 
									-->
									<input type="hidden" name="businessId" value="1"/>
									<input type="hidden" name="levelId" value="1"/>
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       运营商<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="businessId" name="businessId" class="span2 m-wrap required" style="width: 300px;">
										   		<c:forEach items="${businessList }" var="businessList">
										   			<option value="${businessList.id }" ${businessList.id == businessId?'selected=selected':'' }>${businessList.businessName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       试卷等级<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="levelId" name="levelId" class="span2 m-wrap required" style="width: 300px;">
										   		<c:forEach items="${levelList }" var="levelList">
										   			<option value="${levelList.id }" ${levelList.id == levelId?'selected=selected':'' }>${levelList.levelName }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									 -->
									<div class="control-group"> 
									   <label class="control-label"> 
									       题集范围<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									   		<c:forEach items="${quesSourceList}" var="quesSource" varStatus="status">
									       		<input type="checkbox" name="sourceIds" value="${quesSource.id }" />${quesSource.sourceName }
									        </c:forEach>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       状态<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									   		<div class="danger-toggle-button">
												<input type="checkbox" class="toggle" checked=checked name="state" value="1"/>
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
									       <input type="checkbox" name="kindCs" value="1" checked="checked"/>支持C/S客户端卷
									       <input type="checkbox" name="kindMobile" value="1" checked="checked"/>支持移动端
									   </div> 
									</div>  -->
									<div class="control-group"> 
										<label class="control-label"> 
									       	题型分值<span class="required">*</span> 
									    </label>
										<div class="controls">
											<span class="help-inline">单选:</span><input id="danxScore" name="pracmain.pracmainSubMap['danx'].typeScore" class="m-wrap span1" type="text" value="1" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">多选:</span><input id="duoxScore" name="pracmain.pracmainSubMap['duox'].typeScore" class="m-wrap span1" type="text" value="2" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">判断:</span><input id="pandScore" name="pracmain.pracmainSubMap['pand'].typeScore" class="m-wrap span1" type="text" value="1" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">计分:</span><input id="jfScore" name="pracmain.pracmainSubMap['jf'].typeScore" class="m-wrap span1" type="text" value="10" />
											<span class="help-inline">分</span>
											
										</div>
										<div class="controls">
											<span class="help-inline">案例:</span><input id="alScore" name="pracmain.pracmainSubMap['al'].typeScore" class="m-wrap span1" type="text" value="10" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">实操:</span><input id="scScore" name="pracmain.pracmainSubMap['sc'].typeScore" class="m-wrap span1" type="text" value="4" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">不定向选择:</span><input id="bdxxzScore" name="pracmain.pracmainSubMap['bdxxz'].typeScore" class="m-wrap span1" type="text" value="10" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">简答:</span><input id="jdScore" name="pracmain.pracmainSubMap['jd'].typeScore" class="m-wrap span1" type="text" value="6" />
											<span class="help-inline">分</span>
										</div>
										<div class="controls">
											<span class="help-inline">计分(主):</span><input id="jf2Score" name="pracmain.pracmainSubMap['jf2'].typeScore" class="m-wrap span1" type="text" value="10" />
											<span class="help-inline">分</span>
											&nbsp;
											<span class="help-inline">综合:</span><input id="zhScore" name="pracmain.pracmainSubMap['zh'].typeScore" class="m-wrap span1" type="text" value="15" />
											<span class="help-inline">分</span>
										</div>
								       <table class="table table-striped table-bordered table-hover" id="sample_1">
											<thead>
												<tr>
													<th style="width:40px;">序号</th>
													<th>易</th> 
													<th>中</th> 
													<th>难</th> 
													<th>重复抽题</th> 
												</tr>
											</thead>
											<tr>
												<td>单选题</td>
												<td><input id="danx1" name="pracmain.pracmainSubMap['danx'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'danx','1');">题</td>
												<td><input id="danx2" name="pracmain.pracmainSubMap['danx'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'danx','2');">题</td>
												<td><input id="danx3" name="pracmain.pracmainSubMap['danx'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'danx','3');">题</td>
												<td>
													<select id="allowDuplicateByDanx" name="allowDuplicateByDanx" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>多选题</td>
												<td><input id="duox1" name="pracmain.pracmainSubMap['duox'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'duox','1');">题</td>
												<td><input id="duox2" name="pracmain.pracmainSubMap['duox'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'duox','2');">题</td>
												<td><input id="duox3" name="pracmain.pracmainSubMap['duox'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'duox','3');">题</td>
												<td>
													<select id="allowDuplicateByDuox" name="allowDuplicateByDuox" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>判断题</td>
												<td><input id="pand1" name="pracmain.pracmainSubMap['pand'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'pand','1');">题</td>
												<td><input id="pand2" name="pracmain.pracmainSubMap['pand'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'pand','2');">题</td>
												<td><input id="pand3" name="pracmain.pracmainSubMap['pand'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'pand','3');">题</td>
												<td>
													<select id="allowDuplicateByPand" name="allowDuplicateByPand" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>计分题</td>
												<td><input id="jf1" name="pracmain.pracmainSubMap['jf'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf','1');">题</td>
												<td><input id="jf2" name="pracmain.pracmainSubMap['jf'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf','2');">题</td>
												<td><input id="jf3" name="pracmain.pracmainSubMap['jf'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf','3');">题</td>
												<td>
													<select id="allowDuplicateByJf" name="allowDuplicateByJf" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>案例题</td>
												<td><input id="al1" name="pracmain.pracmainSubMap['al'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'al','1');">题</td>
												<td><input id="al2" name="pracmain.pracmainSubMap['al'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'al','2');">题</td>
												<td><input id="al3" name="pracmain.pracmainSubMap['al'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'al','3');">题</td>
												<td>
													<select id="allowDuplicateByAl" name="allowDuplicateByAl" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>实操题</td>
												<td><input id="sc1" name="pracmain.pracmainSubMap['sc'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'sc','1');">题</td>
												<td><input id="sc2" name="pracmain.pracmainSubMap['sc'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'sc','2');">题</td>
												<td><input id="sc3" name="pracmain.pracmainSubMap['sc'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'sc','3');">题</td>
												<td>
													<select id="allowDuplicateSc" name="allowDuplicateSc" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="1" empty="false"/>
											   		</select>
											   	</td>
											</tr>
											<tr>
												<td>不定向选择题<br/>(主观)</td>
												<td><input id="bdxxz1" name="pracmain.pracmainSubMap['bdxxz'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'bdxxz','1');">题</td>
												<td><input id="bdxxz2" name="pracmain.pracmainSubMap['bdxxz'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'bdxxz','2');">题</td>
												<td><input id="bdxxz3" name="pracmain.pracmainSubMap['bdxxz'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'bdxxz','3');">题</td>
												<td>
													<select id="allowDuplicateByBdxxz" name="allowDuplicateByBdxxz" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="1" empty="false"/>
											   		</select>
											   	</td>
											</tr>
											<tr>
												<td>简答题<br/>(主观)</td>
												<td><input id="jd1" name="pracmain.pracmainSubMap['jd'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jd','1');">题</td>
												<td><input id="jd2" name="pracmain.pracmainSubMap['jd'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jd','2');">题</td>
												<td><input id="jd3" name="pracmain.pracmainSubMap['jd'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jd','3');">题</td>
												<td>
													<select id="allowDuplicateByJd" name="allowDuplicateByJd" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="1" empty="false"/>
											   		</select>
											   	</td>
											</tr>
											<tr>
												<td>计分题<br/>(主观)</td>
												<td><input id="jf21" name="pracmain.pracmainSubMap['jf2'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf2','1');">题</td>
												<td><input id="jf22" name="pracmain.pracmainSubMap['jf2'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf2','2');">题</td>
												<td><input id="jf23" name="pracmain.pracmainSubMap['jf2'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'jf2','3');">题</td>
												<td>
													<select id="allowDuplicateByJf2" name="allowDuplicateByJf2" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="0" empty="false"/>
											   		</select> 
												</td>
											</tr>
											<tr>
												<td>综合题<br/>(主观)</td>
												<td><input id="zh1" name="pracmain.pracmainSubMap['zh'].difid1" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'zh','1');">题</td>
												<td><input id="zh2" name="pracmain.pracmainSubMap['zh'].difid2" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'zh','2');">题</td>
												<td><input id="zh3" name="pracmain.pracmainSubMap['zh'].difid3" class="span3 m-wrap digits" type="text" value="0" onblur="checkQC(this,'zh','3');">题</td>
												<td>
													<select id="allowDuplicateByZh" name="allowDuplicateByZh" class="m-wrap required" style="width:100px;">
											       		<dict:selectOption field="YES_NO" selCode="1" empty="false"/>
											   		</select>
											   	</td>
											</tr>
										</table>
									</div>
									<div class="control-group"> 
									   <label class="control-label"> 
									       总分
									   </label> 
									   <div class="controls"> 
									       <input id="totalScore" name="totalScore" class="span6 required m-wrap digits" type="text" value="" /> 
									   </div> 
									</div>
									<!-- 
									<div class="control-group"> 
									   <label class="control-label"> 
									       及格分数<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="passScore" class="span6 m-wrap required digits" type="text" value="60"> 
									   </div> 
									</div> -->
									<div class="control-group"> 
									   <label class="control-label"> 
									       生成试卷数量<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="paperCount" class="span6 m-wrap required" type="text" value=""> 
									       <button id="chacBtn" class="btn blue" type="button"><i class="icon-ok"></i>数量检查</button>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       备注
									   </label> 
									   <div class="controls"> 
									       <input name="remark" class="span6 m-wrap" type="text" value="${pracmain.remark}"> 
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
		 	//ajax抽卷量校验  
		    $("#chacBtn").click(function(){  
		        $.ajax({  
		            type: "post",  
		            url: "${basePath}paper/checkBuildNumPaperAjax",  
		            data: $("#inputForm").serialize(),      
		            success: function(data) { 
		            	if(data != ""){
			                alert(data);  
		            	}else{
		            		alert("验证成功，可进行试卷分配！");
		            	}
		            },  
		            error: function(data) {  
		                alert("error:" + data);  
		            }  
		        }) ;
		    });  
		});
		function formSubmit(){
			$("#inputForm").submit();
		}
		function checkQC(obj,typeCode,diff){
			if(obj.value == "" || obj.value == "0") return ;
			var cid = $("#courseId").val();
			var quesSourceIds = "";
			var lp = "";
			$('input[type="checkbox"][name="sourceIds"]:checked').each(
				function() {
					quesSourceIds += lp + $(this).val();
					lp = ",";
				}
			);
			$.ajax( {    
			    url:'${basePath}paper/findQuesCountAjax',
			    data:{    
			    	cid : cid,    
			    	quescode : typeCode,    
			    	diff : diff,
			    	quesSourceIds : quesSourceIds
			    },    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {    
			        var c = parseInt(data);
			        var objCount = parseInt(obj.value);
			        if(c < objCount){
			        	alert("题量不足，请补充题库后重试！您最多可选择题量为："+c);
			        }
			        calSumScore(); //合算总分
			     },    
			     error : function() {    
			          // view("异常！");    
			          alert("异常！");    
			     }    
			});  
		}
		
		function calSumScore(){
			var s = 0;
			s += (pInt($("#danx1").val()) + pInt($("#danx2").val()) + pInt($("#danx3").val())) * parseFloat($("#danxScore").val());
			s += (pInt($("#duox1").val()) + pInt($("#duox2").val()) + pInt($("#duox3").val())) * parseFloat($("#duoxScore").val());
			s += (pInt($("#pand1").val()) + pInt($("#pand2").val()) + pInt($("#pand3").val())) * parseFloat($("#pandScore").val());
			s += (pInt($("#jf1").val()) + pInt($("#jf2").val()) + pInt($("#jf3").val())) * parseFloat($("#jfScore").val());
			s += (pInt($("#al1").val()) + pInt($("#al2").val()) + pInt($("#al3").val())) * parseFloat($("#alScore").val());
			s += (pInt($("#sc1").val()) + pInt($("#sc2").val()) + pInt($("#sc3").val())) * parseFloat($("#scScore").val());
			s += (pInt($("#bdxxz1").val()) + pInt($("#bdxxz2").val()) + pInt($("#bdxxz3").val())) * parseFloat($("#bdxxzScore").val());
			s += (pInt($("#jd1").val()) + pInt($("#jd2").val()) + pInt($("#jd3").val())) * parseFloat($("#jdScore").val());
			s += (pInt($("#jf21").val()) + pInt($("#jf22").val()) + pInt($("#jf23").val())) * parseFloat($("#jf2Score").val());
			s += (pInt($("#zh1").val()) + pInt($("#zh2").val()) + pInt($("#zh3").val())) * parseFloat($("#zhScore").val());
			$("#totalScore").val(s);
			
			var c = 0;
			c += (pInt($("#danx1").val()) + pInt($("#danx2").val()) + pInt($("#danx3").val()));
			c += (pInt($("#duox1").val()) + pInt($("#duox2").val()) + pInt($("#duox3").val()));
			c += (pInt($("#pand1").val()) + pInt($("#pand2").val()) + pInt($("#pand3").val()));
			c += (pInt($("#jf1").val()) + pInt($("#jf2").val()) + pInt($("#jf3").val()));
			c += (pInt($("#al1").val()) + pInt($("#al2").val()) + pInt($("#al3").val()));
			c += (pInt($("#sc1").val()) + pInt($("#sc2").val()) + pInt($("#sc3").val()));
			c += (pInt($("#bdxxz1").val()) + pInt($("#bdxxz2").val()) + pInt($("#bdxxz3").val()));
			c += (pInt($("#jd1").val()) + pInt($("#jd2").val()) + pInt($("#jd3").val()));
			c += (pInt($("#jf21").val()) + pInt($("#jf22").val()) + pInt($("#jf23").val()));
			c += (pInt($("#zh1").val()) + pInt($("#zh2").val()) + pInt($("#zh3").val()));
			$("#quesCount").val(c);
		}
		function pInt(p){
			if(p == "") return 0;
			return parseInt(p);
		}
		
	</script>
</body>
</html>