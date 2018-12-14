<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/data" prefix="d"%>
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
							试题选择
						</h3>
					</div>
				</div>
				<form id="searchForm" class="form-horizontal" action="${basePath }paperExamination/selectQues" method="post">
					<input type="hidden" name="paperId" value="${paperId}" />
					<input type="hidden" id="typeCode" name="map['typeCode']" value="${searchParams.map['typeCode'] }" />
					<input type="hidden" id="sourceId" name="map['sourceId']" value="${searchParams.map['sourceId'] }" />
					<div class="portlet-title">
						试题内容：
						<input class="m-wrap placeholder-no-fix" type="text" id="examinationContent" name="map['examinationContent']" value="${searchParams.map['examinationContent'] }"/>
						所属课程：
						<select id="courseId" name="map['courseId']" class="span2 m-wrap">
				       		<option value="" ></option>
					   		<c:forEach items="${courseList }" var="courseList">
					   			<option value="${courseList.id }" ${courseList.id==searchParams.map['courseId']?'selected=selected':'' }>${courseList.courseName }</option>
					   		</c:forEach>
				   		</select>
				   		批次：
				   		<select id="batchInfo" name="map['batchInfo']" class="span2 m-wrap">
				       		<option value="" ></option>
					   		<c:forEach items="${batchList }" var="batchObj">
					   			<option value="${batchObj }" ${batchObj==searchParams.map['batchInfo']?'selected=selected':'' }>${batchObj}</option>
					   		</c:forEach>
				   		</select>
						<button type="button" onclick="search();" class="btn green" >查询</button>
					</div>
					<div class="control-group">
						题型->
						<div class="btn-group hidden-phone">
							<a href="javascript:;" class="btn mini ${searchParams.map['typeCode']==''?'blue':'' }" 
								style="margin-right: 5px;" onclick="queryByTypeCode('');">
								全部
							</a>
							<c:forEach items="${typeList }" var="typeList">
								<a href="javascript:;" class="btn mini ${typeList.typeCode==searchParams.map['typeCode']?'blue':'' }" 
									style="margin-right: 5px;" onclick="queryByTypeCode('${typeList.typeCode}');">
									${typeList.typename }
								</a>
							</c:forEach>
						</div>
					</div>
					<div class="control-group">
						题库集->
						<div class="btn-group hidden-phone">
							<a href="javascript:;" class="btn mini ${searchParams.map['sourceId']==''?'blue':'' }" 
								style="margin-right: 5px;" onclick="queryBySourceId('');">
								全部
							</a>
							<c:forEach items="${quesSourceList }" var="quesSourceList">
								<a href="javascript:;" class="btn mini ${quesSourceList.id==searchParams.map['sourceId']?'blue':'' }" 
									style="margin-right: 5px;" onclick="queryBySourceId('${quesSourceList.id}');">
									${quesSourceList.sourceName }
								</a>
							</c:forEach>
						</div>
					</div>
				</form>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> 注：最多显示500条记录!
								</div>
							</div>
							<div class="portlet-body form">
								<form id="inputForm" class="form-horizontal" action="${basePath}paperExamination/selectQuesSave" method="post" >
									<input type="hidden" name="paperId" value="${paperId}" />
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									<div class="control-group"> 
									   <table class="table table-striped table-bordered table-hover" id="sample_1">
											<thead>
												<tr>
													<th style="width:40px;">题号</th>
													<th style="width:100px;">试题类型</th> 
													<th>试题内容</th> 
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${questionList}" var="list" varStatus="status">
													<tr>
														<td>
															${list.id }
															<input type="checkbox" name="quesIdChk" value="${list.id }"/>
														</td>
														<td>${list.quesType.typename }</td>
														<td title="${list.examinationContent }">
															<d:titleLimit maxCount="50" str="${list.examinationContent }"/>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div> 
									<div class="form-actions clearfix">
										<button class="btn blue" type="submit"><i class="icon-ok"></i>确定</button>
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
		   $("#inputForm").validate();
		   App.init();
		});
		function cancle(){
			window.location = "${basePath}paper";
		}
	</script>
	
	<script>
		function queryByTypeCode(typeCode){
			$("#typeCode").val(typeCode);
			$("#searchForm").submit();
		}
		function queryBySourceId(sourceId){
			$("#sourceId").val(sourceId);
			$("#searchForm").submit();
		}
		function search(){
			$('#searchForm').submit();  
		}
	</script>
</body>
</html>
