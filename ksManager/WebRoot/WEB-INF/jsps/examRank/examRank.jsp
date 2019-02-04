<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
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
	<link rel="stylesheet" href="${resPath}js/jquery-ui/jquery-ui.css">
	<script src="${resPath}js/jquery-ui/jquery-1.12.4.js"></script>
	<script src="${resPath}js/jquery-ui/jquery-ui.js"></script>
	<script src="${resPath}js/uniteTable.js"></script>
	<%@ include file="/WEB-INF/includes/common-component-list.jsp"%>
	<script type="text/javascript">
		jQuery(document).ready(function() { 
			$( "#examname" ).autocomplete({
			  source: "${basePath}exam/findExamListAjax"
		    });
			
			uniteTable(document.getElementById("sample_1"),2);
		});
	</script>
</head>
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							我的考试
						</h3>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="tabbable tabbable-custom boxless">
						<ul class="nav nav-tabs">
							<li class=""><a href="${basePath }exam/update/${searchParams.map['eid'] }?tab=1">基础信息</a></li>
							<li><a href="${basePath }exam/update/${searchParams.map['eid'] }?tab=2">高级配置</a></li>
							<li><a href="${basePath }examStu/${searchParams.map['eid'] }">考生信息</a></li>
							<li class="active"><a href="#tab_3"  data-toggle="tab">考试数据</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_3">
								<div class="row-fluid">
									<div class="span9">
										<form id="searchForm" class="form-horizontal" action="${basePath }examRank/${searchParams.map['eid'] }" method="post">
											<div class="portlet-body form">
												<div class="row-fluid">
													<div class="span6">
														<div class="control-group">
															<label class="control-label span3" for="truename">考生姓名</label>
															<div class="controls span4">
																<input class="m-wrap" type="text" placeholder="考生姓名..." id="truename" name="map['truename']" value="${searchParams.map['truename'] }"/>													
															</div>
														</div>
													</div>
													<div class="span6">
														<div class="control-group">
															<label class="control-label span3" for="idcard">准考证号</label>
															<div class="controls span4">
																<input class="m-wrap" type="text" placeholder="准考证号..." id="idcard" name="map['idcard']" value="${searchParams.map['idcard'] }"/>													
															</div>
														</div>
													</div>
												</div>
												<div style="text-align:center;">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> 查询</button>
													<button type="button" class="btn" onclick="resetForm('searchForm');">重置</button>
												</div>
											</div>
											<div class="portlet box light-grey">
												<div class="clearfix">
													<div class="btn-group pull-right">
														<c:if test="${business.account >= '0'}">
															<button class="btn dropdown-toggle" data-toggle="dropdown">更多 <i class="icon-angle-down"></i>
															</button>
															<ul class="dropdown-menu pull-right">
																<li><a onclick="javascript:$('#responsiveExport').modal('show');" data-toggle="modal" href="javascript:void();">导出至Excel</a></li>
																<!-- <li><a onclick="submitForm('${basePath }examRank/export/${searchParams.map['eid'] }','searchForm');" href="javascript:void();">导出至Excel</a></li> -->
															</ul>
														</c:if>
													</div>
													<div class="btn-group pull-right">
														<select id="pageItem" name="map['pageItem']" class="span16 m-wrap">
												       		<option value="10" ${searchParams.map['pageItem'] == 10 ? 'selected=selected':''}>每页10条</option>
													   		<option value="20" ${searchParams.map['pageItem'] == 20 ? 'selected=selected':''}>每页20条</option>
													   		<option value="30" ${searchParams.map['pageItem'] == 30 ? 'selected=selected':''}>每页30条</option>
													   		<option value="50" ${searchParams.map['pageItem'] == 50 ? 'selected=selected':''}>每页50条</option>
													   		<option value="100" ${searchParams.map['pageItem'] == 100 ? 'selected=selected':''}>每页100条</option>
													   		<option value="200" ${searchParams.map['pageItem'] == 200 ? 'selected=selected':''}>每页200条</option>
												   		</select>
												   	</div>
												</div>
												<div id="responsiveExport" class="modal hide fade" tabindex="-1" data-width="760">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h3>数据导出</h3>
													</div>
													<div class="modal-body">
														<div class="row-fluid">
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="rankIndex" checked=checked /> 排名
																</label>
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="idcard" checked=checked /> 准考证号
																</label>	
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="truename" checked=checked /> 姓名
																</label>
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="score" checked=checked /> 分数
																</label>
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="ip" checked=checked /> IP地址
																</label>
																<label class="checkbox">
																	<input type="checkbox" name="expColsCheckbox" value="submitTime" /> 交卷时间
																</label>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" data-dismiss="modal" class="btn">关闭</button>
														<button type="button" class="btn blue" onclick="submitForm('${basePath }examRank/export/${searchParams.map['eid'] }','searchForm');">确定</button>
													</div>
												</div>
											</form>
											<table class="table table-striped table-bordered table-hover" id="sample_1">
												<thead>
													<tr>
														<th style="text-align: center;" class="span3">考试科目</th>
														<th style="text-align: center;" class="span2">排名</th> 
														<th style="text-align: center;" class="span3">准考证号</th> 
														<th style="text-align: center;" class="span2">姓名</th>
														<th style="text-align: center;" class="span2">分数</th>
														<th style="text-align: center;" class="span2">Ip地址</th>
														<th class="span4">操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${list}" var="list" varStatus="status">
														<tr class="odd gradeX">
															<td style="text-align: center;">${list.examCourse.courseName }</td>
															<td style="text-align: center;">${list.examCourse.rankIndex}</td> 
															<td>${list.idcard }</td> 
															<td>${list.truename }
																<c:if test="${list.testFlag == '1'}"><span class="badge badge-warning">测试</span></c:if>
															</td> 
															<td>${list.examCourse.score }</td>
															<td>${list.loginip }</td>
															<td>
																<c:choose>
																	<c:when test="${list.examCourse.submitFlag == '0' && not empty list.examCourse.endTime}">
																		<a href="javascript:void();" class="btn mini black"
																			onclick="timeDelay('${list.id}','${list.examId}','${list.examCourse.courseId}',
																				'${list.truename }','${list.examCourse.endTime}');">延时</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void();" class="btn mini disabled" title="开考期间可延时">延时</a>
																	</c:otherwise>
																</c:choose>
																
																<c:choose>
																	<c:when test="${list.examCourse.submitFlag == '1'}">
																		<a href="javascript:void();" class="btn mini black"
																			onclick="reexamine('${list.id}','${list.examId}',
																				'${list.examCourse.courseId}');">重考</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void();" class="btn mini disabled" title="试卷提交后允许重考">重考</a>
																	</c:otherwise>
																</c:choose>
																
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<p:page url="examRank/${searchParams.map['eid'] }" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }"/>
										</div>
									</div>
									
									<div class="span3">
										<div class="portlet solid bordered light-grey">
											<div class="portlet-title">
												<div class="caption"><i class="icon-bar-chart"></i>总体概况</div>
											</div>
											<div class="portlet-body">
												<div class="control-group"> 
												   <label class="control-label"> 
												     考生总数 <span class="badge badge-info">${erb.sumCount }</span>人
												   </label> 
												</div> 
												<div class="control-group"> 
												   <label class="control-label"> 
												     登录 <span class="badge badge-info">${erb.loginCount }</span>人, 未登录 <span class="badge badge-info">${erb.noLoginCount }</span>人
												   </label> 
												</div>  
											</div>
										</div>
										<c:forEach items="${ercList}" var="erc">
											<div class="portlet solid bordered light-grey">
												<div class="portlet-title">
													<div class="caption"><i class="icon-list-ol"></i>${erc.courseName }</div>
												</div>
												<div class="portlet-body">
													<div class="control-group"> 
													   <label class="control-label"> 
													     开考人数 <span class="badge badge-info">${erc.examingCount }</span>人
													   </label> 
													</div> 
													<div class="control-group"> 
													   <label class="control-label"> 
													     交卷人数 <span class="badge badge-info">${erc.submitCount }</span>人
													   </label> 
													</div> 
													<div class="control-group"> 
													   <label class="control-label"> 
													     通过率 <span class="badge badge-info">${erc.passRate }%</span>
													   </label> 
													</div> 
													<div class="control-group"> 
													   <label class="control-label"> 
													     最高分 <span class="badge badge-info">${erc.maxScore }分 ↑</span>
													     <c:if test="${not empty erc.maxScoreUserInfo }">
													     	<span class="badge badge-info">${erc.maxScoreUserInfo }</span>
													     </c:if>
													   </label> 
													</div> 
												</div>
											</div>
										</c:forEach>
										<!-- 
										<div class="portlet solid bordered light-grey">
											<div class="portlet-title">
												<div class="caption"><i class="icon-bar-chart"></i>考点统计</div>
												<div class="tools">
													<div class="btn-group pull-right" data-toggle="buttons-radio">
														<a href="" class="btn mini">Users</a>
														<a href="" class="btn mini active">Feedbacks</a>
													</div>
												</div>
											</div>
											<div class="portlet-body">
												<div id="site_statistics_loading">
													<img src="media/image/loading.gif" alt="loading" />
												</div>
												<div id="site_statistics_content" class="hide">
													<div id="site_statistics" class="chart"></div>
												</div>
											</div>
										</div>
										 -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<div id="responsiveTimeDelay" class="modal hide fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3>延时</h3>
	</div>
	<div class="modal-body">
		<input type="hidden" id="ysuid" />
		<input type="hidden" id="yseid" />
		<input type="hidden" id="yscid" />
		<div class="row-fluid">
			<div class="controls">
				<div class="control-group"> 
				   <label class="control-label"> 
				       考生姓名
				   </label> 
				   <div class="controls"> 
				   		<input id="ysname" class="span6 m-wrap" type="text" value="" readonly="readonly">
				   </div> 
				</div> 
				<div class="control-group"> 
				   <label class="control-label"> 
				       交卷截止时间 
				   </label> 
				   <div class="controls"> 
				   		<input id="ysendtime" class="span6 m-wrap" type="text" value="" readonly="readonly">
				   </div> 
				</div> 
				<div class="control-group"> 
				   <label class="control-label"> 
				       延时时间
				   </label> 
				   <div class="controls"> 
				   		<input id="ysmin" class="span6 m-wrap" type="text" value="">分钟
				   </div> 
				</div> 
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn blue" onclick="timeDelaySubmit();">确定</button>
		<button type="button" data-dismiss="modal" class="btn">关闭</button>
	</div>
</div>

<script>
    function reexamine(uid,examId,cid){
    	if(confirm("确定要进行重考吗？\n\n 注：您确定重考后，考生重新登录时，系统会重新扣除考试费用！")){
    		var url = "${basePath}examRank/reexamine/" + uid + "/" + examId + "/" + cid;
    		window.location = url;
    	}
    	
    }
    
    function timeDelay(uid,eid,cid,tname,jjdateTime){
    	$("#ysuid").val(uid);
    	$("#yseid").val(eid);
    	$("#yscid").val(cid);
    	$("#ysname").val(tname);
    	$("#ysendtime").val(jjdateTime);
    	$('#responsiveTimeDelay').modal('show');
    }
    
    function timeDelaySubmit(){
    	if($("#ysmin").val() == ""){
    		alert("请输入需要延时的分钟！");
    		return false;
    	}
    	var url = "${basePath}examRank/delayed/" + $("#ysuid").val() + "/" + $("#yseid").val() + "/" + $("#yscid").val() + "/" + $("#ysmin").val();
    	window.location = url;
    }
</script>
</html>
