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
							我的考试
							<small>${action=='create'?'新增信息':'修改信息'}</small>
						</h3>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
							<div class="tabbable tabbable-custom boxless">
							<ul class="nav nav-tabs">
								<li class="${tab=='1'?'active':'' }"><a href="#tab_1" data-toggle="tab">基础信息</a></li>
								<li class="${tab=='2'?'active':'' }"><a href="#tab_2" data-toggle="tab">高级配置</a></li>
								<c:if test="${action == 'update'}">
									<li><a class="" href="${basePath }examStu?map['eid']=${exam.id }">考生信息</a></li>
									<li><a class="" href="${basePath }examRank?map['eid']=${exam.id }">考试数据</a></li>
								</c:if>
							</ul>
							<form id="inputForm" class="form-horizontal" action="${basePath}exam/${action}" method="post" >
								<input type="hidden" name="id" value="${exam.id }"/>
								<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
									<button class="close" data-dismiss="alert"></button>
									<span>${MESSAGE }</span>
								</div>
								<div class="tab-content">
									<div class="tab-pane ${tab=='1'?'active':'' }" id="tab_1">
										<div class="portlet box blue">
											<div class="portlet-title" style="padding:0px;"></div>
											<div class="portlet-body form">
												<div class="control-group"> 
												   <label class="control-label"> 
												       考试名称<span class="required">*</span> 
												   </label> 
												   <div class="controls"> 
												       <input id="examName" name="examName" class="span6 m-wrap required" type="text" value="${exam.examName}" size="200"> 
												   </div> 
												</div> 
												<div class="control-group"> 
												   <label class="control-label"> 
												       考试开始时间<span class="required">*</span> 
												   </label> 
												   <div class="controls"> 
												   		<input id="examBegintime" name="examBegintime" class="datetimepicker span6 m-wrap required" type="text" value="${exam.examBegintime}" readonly="readonly">
												   </div> 
												</div> 
												
												<div class="control-group"> 
												   <label class="control-label"> 
												       考试结束时间<span class="required">*</span> 
												   </label> 
												   <div class="controls"> 
												       <input id="examEndtime" name="examEndtime" class="datetimepicker span6 m-wrap required" type="text" value="${exam.examEndtime}" readonly="readonly"> 
												   </div> 
												</div> 
												<div class="control-group"> 
												   <label class="control-label"> 
												       考试须知
												   </label> 
												   <div class="controls"> 
												       <textarea id="introduce" name="introduce" class="span6 wysihtml5 m-wrap" rows="5" 
												       		onchange="keypressEvent();"
												       		onkeypress="">${exam.introduce }</textarea>
												       <div>（限300字以内）</div>
												   </div> 
												</div> 
												<div class="control-group">
												   <label class="control-label">组卷设置</label> 
												   <div class="controls"> 
												      <div style="color: red;margin-bottom: 10px;margin-left: 10px;">*根据考试具体要求自行设定，试卷配置前，需先将对应考试科目"题库"创建完成！</div>
												      <div class="row-fluid">
												        <div class="input-prepend input-append" style="margin-right: 10px;">
															<span class="add-on">单选<input type="hidden" name="pracList[0].typeCode" value="danx"/></span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[0].count" 
																value="${exam.pracList[0].count}"><span class="add-on">题，每题</span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[0].score" 
																value="${exam.pracList[0].score}"><span class="add-on">分</span>
													    </div>
														<div class="input-prepend input-append">
															<span class="add-on">多选<input type="hidden" name="pracList[1].typeCode" value="duox"/></span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[1].count" 
																value="${exam.pracList[1].count}"><span class="add-on">题，每题</span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[1].score" 
																value="${exam.pracList[1].score}"><span class="add-on">分</span>
														</div>
													  </div>
													  <div class="row-fluid">
														<div class="input-prepend input-append" style="margin-right: 10px;">
															<span class="add-on">判断<input type="hidden" name="pracList[2].typeCode" value="pand"/></span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[2].count" 
																value="${exam.pracList[2].count}"><span class="add-on">题，每题</span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[2].score" 
																value="${exam.pracList[2].score}"><span class="add-on">分</span>
														</div>
														<div class="input-prepend input-append">
															<span class="add-on">填空<input type="hidden" name="pracList[3].typeCode" value="tiank"/></span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[3].count" 
																value="${exam.pracList[3].count}"><span class="add-on">题，每题</span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[3].score" 
																value="${exam.pracList[3].score}"><span class="add-on">分</span>
														</div>
													  </div>
													  <div class="row-fluid">
														<div class="input-prepend input-append" style="margin-right: 10px;">
															<span class="add-on">简答<input type="hidden" name="pracList[4].typeCode" value="jiand"/></span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[4].count" 
																value="${exam.pracList[4].count}"><span class="add-on">题，每题</span>
															<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[4].score" 
																value="${exam.pracList[4].score}"><span class="add-on">分</span>
														</div>
													  </div>
													  <c:if test="${not empty exam.msg}">
													  	<div class="row-fluid span6" style="margin-top: 10px;">
														  <div class="alert">
															<button class="close" data-dismiss="alert"></button>
															<strong>警告!</strong> ${exam.msg }
														  </div>
														</div>
													  </c:if>
													  <!-- 
													  <label class="control-label" title="系统随机生成指定数量的试卷，考生同样以随机方式获取试卷">试卷生成套数</label>
													  <div class="controls" style="margin-top: 10px;"> 
													        <input name="paperBuildCount" class="span2 m-wrap required number" type="text" value="${exam.paperBuildCount}"> 
													  </div> 
													  
													  <label class="control-label">试卷构成</label>
													  <div class="controls" style="margin-top: 10px;"> 
													        总题数：* 题 ， 总分：*分
													  </div> 
													   -->
												   </div> 
												</div>
											</div>
										</div>
									</div>
									<div class="tab-pane ${tab=='2'?'active':'' }" id="tab_2">
										<div class="portlet box blue">
											<div class="portlet-title" style="padding:0px;"></div>
											<div class="portlet-body form">
												<div class="control-group"> 
												   <label class="control-label"> 
													  多科连考配置
												   </label> 
												   <div class="controls"> 
												   	  <!-- 
												      <select class="span20 chosen" data-placeholder="请选择考试科目" tabindex="1" id="selCourseArr" name="selCourseArr" multiple="multiple">
														<option value=""></option>
														<c:forEach items="${courseList }" var="course">
												   			<option value="${course.id }" ${course.selCourseFlag == '1'?'selected=selected':'' }>${course.courseName }</option>
												   		</c:forEach>
													  </select> -->
													  <a class="btn green" onclick="javascript:$('#courseName').val('');$('#responsiveAddCourse').modal('show');">创建新的考试科目</a>
													  <a class="btn" onclick="javascript:$('#responsiveSelCourse').modal('show');">选择已有考试科目</a>
													  <table class="table table-striped table-bordered table-hover" id="courseTable">
														 <thead>
															<tr>
																<th class="span6">科目名称</th> 
																<th class="span3">限时（分钟）</th> 
																<th class="span3">自定义顺序</th>
															</tr>
														 </thead>
														 <tbody id="courseListTbody">
														 </tbody>
													  </table>
													  
												   </div> 
												</div>
												
												<div class="control-group"> 
												   <label class="control-label">及格分数</label> 
												   <div class="controls"> 
												        <input name="passScore" class="span1 m-wrap required number" type="text" value="${exam.passScore}"> 
												   </div> 
												</div>
											</div>
										</div>
									</div>
									<!-- 
									<div class="tab-pane" id="tab_3">
										<div class="portlet box blue">
											<div class="portlet-title" style="padding:0px;"></div>
											<div class="portlet-body form">
												asdfasdf
											</div>
										</div>
									</div> -->
									<div class="form-actions clearfix">
										<button class="btn blue" type="button" onclick="save();"><i class="icon-ok"></i>完成</button>
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
	
	<!-- BEGIN FOOTER -->
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
	<!-- END FOOTER -->
	</div>
	
	<div id="responsiveAddCourse" class="modal hide fade" tabindex="-1" data-width="760">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h3>增加考试科目</h3>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<div class="row-fluid">
					<div class="control-group"> 
					   <label class="control-label"> 
					       考试科目名称
					   </label> 
					   <div class="controls"> 
					       <input id="courseName" class="span12 m-wrap" type="text"/> 
					   </div> 
					</div> 
					<div class="control-group"> 
					   <label class="control-label"> 
					       考试用时
					   </label> 
					   <div class="controls"> 
					       <input id="examSumTime" class="span12 m-wrap number" type="text" value="60"/>分钟 
					   </div> 
					</div> 
					<div class="control-group"> 
					   <label class="control-label"> 
					     考试科目显示顺序
					   </label> 
					   <div class="controls"> 
					       <input id="indexno" class="span12 m-wrap number" type="text" value="0"/> 
					   </div> 
					</div> 
					
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn blue" onclick="addCourse();">增加</button>
			<button type="button" data-dismiss="modal" class="btn">关闭</button>
		</div>
	</div>
	
	<div id="responsiveSelCourse" class="modal hide fade" tabindex="-1" data-width="760">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h3>选择已有的考试科目</h3>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<div class="row-fluid">
					  <table class="table table-striped table-bordered table-hover" id="courseTableSel"></table>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn blue" onclick="selCourse();">确定</button>
			<button type="button" data-dismiss="modal" class="btn">关闭</button>
		</div>
	</div>
	
	<script>
		$.fn.datetimepicker.dates['zh-CN'] = {  
	            days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],  
	            daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],  
	            daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],  
	            months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],  
	            monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],  
	            meridiem:    ["上午", "下午"],  
	            //suffix:      ["st", "nd", "rd", "th"],  
	            today:       "今天"  
	    };  
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		   $('.datetimepicker').datetimepicker({
               format: 'yyyy-mm-dd hh:ii',      /*此属性是显示顺序，还有显示顺序是mm-dd-yyyy*/
               autoclose:true,minuteStep:1,
               minView:0,
               language:"zh-CN",
               todayHighlight: true,
               clearBtn: true
           });
		   
		   if (!jQuery().wysihtml5) {
	            return;
	        }

	        if ($('.wysihtml5').size() > 0) {
	            $('.wysihtml5').wysihtml5({
	                "stylesheets": ["assets/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
	            });
	        }
	        
		});
		function cancle(){
			window.location = "${basePath}exam";
		}
	</script>
	
	<script type="text/javascript">
	  	var courseListJson = '${courseListJson}';
	  	var json = $.parseJSON(courseListJson);
	  	function viewCourseDom(){
	  		$("#courseTable").html("");
	  		$("#courseTableSel").html("");
	  		var idx0 = 0;
	  		var idx1 = 0;
	  		
			$.each(json, function(idx, obj) {
				if(obj.selCourseFlag == '1'){
					var t = "";
					if(idx0 == 0) t = '<thead><tr><th class="span6">科目名称</th><th class="span3">限时（分钟）</th><th class="span3">自定义顺序</th></tr></thead><tbody>';
				    t += '<tr class="odd gradeX">';
				    t += '<td>'+obj.courseName;
				    t += '<input name="selCourseArr['+idx0+'].id" type="hidden" value="' + obj.id +'"/>';
				    t += '  <a href="javascript:void();" onclick="removeCourseDom('+idx+');" class="btn mini black"><i class="icon-trash"></i> 删除</a></td>';
				    t += '<td><input name="selCourseArr['+idx0+'].examSumTime" type="text" class="span3 m-wrap number" value="' + obj.examSumTime +'" onchange="updateCourseDom(this, '+idx+',\'examSumTime\');"/>(-1不限时)</td>';
				    t += '<td><input name="selCourseArr['+idx0+'].indexno" class="span3 m-wrap number" type="text" value="' + obj.indexno +'" onchange="updateCourseDom(this, '+idx+',\'indexno\');"></td>';
				    t += '</tr>';
				    if(idx0 == 0) t += "</tbody>";
				    $("#courseTable").append(t);
				    idx0 ++;
			    }else{
			    	var t = "";
			    	if(idx1 == 0) t = '<thead><tr><th class="span2">选择</th><th class="span6">科目名称</th><th class="span3">限时（分钟）</th><th class="span3">自定义顺序</th></tr></thead><tbody>';
				    t += '<tr class="odd gradeX">';
				    t += '<td class="span2"><input type="checkbox" name="selChk" value="'+idx+'"></td>';
				    t += '<td>'+obj.courseName+'</td>';
				    t += '<td><input type="text" class="span5 m-wrap number" value="' + obj.examSumTime +'" onchange="updateCourseDom(this, '+idx+',\'examSumTime\');"/></td>';
				    t += '<td><input class="span5 m-wrap number" type="text" value="' + obj.indexno +'" onchange="updateCourseDom(this, '+idx+',\'indexno\');"></td>';
				    t += '</tr>';
				    if(idx1 == 0) t += "</tbody>";
				    $("#courseTableSel").append(t);
				    idx1 ++;
			    }
			});
	  	}
	  	function removeCourseDom(idx){
	  		if(confirm("确定要删除该考试科目吗？")){
	  			json[idx].selCourseFlag = '0';
	  		}
	  		viewCourseDom();
	  	}
	  	
	  	function updateCourseDom(t, idx, key){
	  		json[idx][key] = t.value;
	  		viewCourseDom();
	  	}
	  	
	  	function addCourse(){
			if($("#courseName").val() == ""){
				alert("操作失败,考试科目名称不能为空！");
				return false;
			}
			var examSumTime = $("#examSumTime").val();
			var iExamSumTime = parseInt(examSumTime);
			if(isNaN(iExamSumTime)){
		        alert("考试时长必须为数值！");
		        return false;
		    }
			$.ajax({
				type:"GET", 
				url:"${basePath}resCourse/addCourse",
				dataType:"json",
				data:{
					courseName : $("#courseName").val(),
					examSumTime : examSumTime,
					indexno: $("#indexno").val()
				},
				success:function(data){
					if(data.code==1){
						//$("#selCourseArr").append("<option value='"+data.obj.id+"' selected=selected>"+data.obj.courseName+"</option>");
						json.push(data.obj);
						viewCourseDom();
			            $('#responsiveAddCourse').modal('hide');
					}
				}
			});
		}
		
		function selCourse(){
			$('input[type="checkbox"][name="selChk"]:checked').each(
                function() {
                       json[$(this).val()].selCourseFlag="1";   
                }
            );
            $('#responsiveSelCourse').modal('hide');
            viewCourseDom();
		}
	  	viewCourseDom();
	  	
	  	function save(){
	  		if($("#examName").val() == ""){
	  			alert("考试名称不能为空！");
	  			return false;
	  		} 
	  		if($("#examBegintime").val() == ""){
	  			alert("考试开始时间不能为空！");
	  			return false;
	  		}
	  		if($("#examEndtime").val() == ""){
	  			alert("考试结束时间不能为空！");
	  			return false;
	  		}
	  		
	  		if($("#examBegintime").val().substring(0,15) >= $("#examEndtime").val().substring(0,15)){
	  			alert("考试结束时间要求大于开始时间！");
	  			return false;
	  		}
	  		
	  		if($('#introduce').val().length > 300){
	  			alert("考试须知内容不能大于300字符！");
	  			return false;
	  		}
	  		$("#inputForm").submit();
	  	}
	</script>
</body>
</html>
