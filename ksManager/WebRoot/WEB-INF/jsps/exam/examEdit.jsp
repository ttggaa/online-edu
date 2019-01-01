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

						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}exam/${action}" method="post" >
									<input type="hidden" name="id" value="${exam.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       考试名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examName" class="span6 m-wrap required" type="text" value="${exam.examName}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       开始时间<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									   		<input name="examBegintime" class="datetimepicker span6 m-wrap required" type="text" value="${exam.examBegintime}" readonly="readonly">
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       结束时间<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="examEndtime" class="datetimepicker span6 m-wrap required" type="text" value="${exam.examEndtime}" readonly="readonly"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       考试须知（限300字以内）
									   </label> 
									   <div class="controls"> 
									       <textarea name="introduce" class="span6 wysihtml5 m-wrap" rows="5">${exam.introduce }</textarea>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       考试科目配置
									       <a class="btn mini green" onclick="javascript:$('#courseName').val('');$('#responsiveAddCourse').modal('show');">增加考试科目</a>
									   </label> 
									   <div class="controls"> 
									        <select class="span6 chosen" data-placeholder="请选择考试科目" tabindex="1" id="selCourseArr" name="selCourseArr" multiple="multiple">
												<option value=""></option>
												<c:forEach items="${courseList }" var="course">
										   			<option value="${course.id }" ${course.selCourseFlag == '1'?'selected=selected':'' }>${course.courseName }</option>
										   		</c:forEach>
											</select>
									   </div> 
									</div> 
									<div class="control-group">
									   <label class="control-label">试卷配置</label> 
									   <div class="controls"> 
									      <div style="color: red;margin-bottom: 10px;margin-left: 10px;">*根据考试具体要求自行设定，试卷配置前，需先将对应考试科目"题库"创建完成！</div>
									      <label class="control-label">题型构成</label>
									   	  <div class="controls">
									        <div class="input-prepend input-append">
												<span class="add-on">单选<input type="hidden" name="pracList[0].typeCode" value="danx"/></span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[0].count" value="${exam.pracList[0].count}"><span class="add-on">道</span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[0].score" value="${exam.pracList[0].score}"><span class="add-on">分</span>
										    </div>
										  </div>
										  <div class="controls">
											<div class="input-prepend input-append">
												<span class="add-on">多选<input type="hidden" name="pracList[1].typeCode" value="duox"/></span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[1].count" value="${exam.pracList[1].count}"><span class="add-on">道</span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[1].score" value="${exam.pracList[1].score}"><span class="add-on">分</span>
											</div>
										  </div>
										  <div class="controls">
											<div class="input-prepend input-append">
												<span class="add-on">判断<input type="hidden" name="pracList[2].typeCode" value="pand"/></span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[2].count" value="${exam.pracList[2].count}"><span class="add-on">道</span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[2].score" value="${exam.pracList[2].score}"><span class="add-on">分</span>
											</div>
										  </div>
										  <div class="controls">
											<div class="input-prepend input-append">
												<span class="add-on">填空<input type="hidden" name="pracList[3].typeCode" value="tiank"/></span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[3].count" value="${exam.pracList[3].count}"><span class="add-on">道</span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[3].score" value="${exam.pracList[3].score}"><span class="add-on">分</span>
											</div>
										  </div>
										  <div class="controls">
											<div class="input-prepend input-append">
												<span class="add-on">简答<input type="hidden" name="pracList[4].typeCode" value="jiand"/></span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[4].count" value="${exam.pracList[4].count}"><span class="add-on">道</span>
												<input class="m-wrap" type="text" style="width:50px;" size="6" name="pracList[4].score" value="${exam.pracList[4].score}"><span class="add-on">分</span>
											</div>
										  </div>
										  
										  <label class="control-label">及格分数线</label>
										  <div class="controls" style="margin-top: 10px;"> 
										        <input name="passScore" class="span2 m-wrap required number" type="text" value="${exam.passScore}"> 
										  </div> 
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
	<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${resPath}media/js/locales/bootstrap-datepicker.zh-CN.js"></script>  
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
						$("#selCourseArr").append("<option value='"+data.obj.id+"' selected=selected>"+data.obj.courseName+"</option>");
						$("#selCourseArr").trigger("liszt:updated");

			            $("#selCourseArr").chosen();
			            $('#responsiveAddCourse').modal('hide');
					}
				}
			});
		}
		
		function cacheRemovePaper(){
			$.ajax({
				type:"GET", 
				url:"${basePath}exam/reqRemoveCachePaper",
				dataType:"json",
				data:{
					examId : '${exam.id }'
				},
				success:function(data){
					alert("OK");
				}
			});
		}
		function cachePaper(){
			$.ajax({
				type:"GET", 
				url:"${basePath}exam/reqCachePaper",
				dataType:"json",
				data:{
					examId : '${exam.id }'
				},
				success:function(data){
					alert(data);
				}
			});
		}
	</script>
	
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
</body>
</html>
