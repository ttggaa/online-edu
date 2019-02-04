<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
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
	<%@ include file="/WEB-INF/includes/common-component-list.jsp"%>
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
							${course.courseName }<small>${course.remark }</small>
						</h3>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<form id="searchForm" class="form-horizontal" action="${basePath }tkExamination/${cid}" method="post">
							<input type="hidden" id="typeCode" name="map['typeCode']" value="${searchParams.map['typeCode'] }" />
							<input type="hidden" id="sourceId" name="map['sourceId']" value="${searchParams.map['sourceId'] }" />
							<input type="hidden" id="sort" name="map['sort']" value="${searchParams.map['sort'] }" />
							<div class="portlet">
								<div class="portlet-title">
									试题内容：
									<input class="m-wrap placeholder-no-fix span8" type="text" id="examinationContent" name="map['examinationContent']" value="${searchParams.map['examinationContent'] }"/>
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
							</div>
						</form>
						<div class="alert alert-success ${empty MESSAGE ?'hide':'' }">
							<button class="close" data-dismiss="alert"></button>
							<span>${MESSAGE }</span>
						</div>
						<div class="portlet box light-grey">
							<div class="clearfix">
									<div class="btn-group">
										<a id="sample_editable_1_new" class="btn green" href="${basePath }tkExamination/create/${cid}">
											新增 <i class="icon-plus"></i>
										</a>
									</div>
									<div class="btn-group">
										<a href="${basePath }tkExamination/examinationImport/${cid}" class="btn btn-info">导入数据</a>
									</div>
									<div class="btn-group">
										<a href="javascript:void();" onclick="exportExcel();" class="btn btn-info">导出数据</a>
									</div>
									<div class="btn-group">
										<a href="javascript:void();" onclick="batchDel();" class="btn btn-info">批量删除</a>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="sample_1">
									<thead>
										<tr role="row">
											<th style="text-align: center;width:100px;">序号</th>
											<th>
												<img src="${resPath}media/image/sort_both.png" onclick="theSort('EXAMINATION_CONTENT');"/>试题内容
											</th> 
											<th style="width:160px;text-align: center;">答案</th> 
											<th style="width:100px;text-align: center;">题型</th> 
											<th style="width:120px;text-align: center;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="cTemp" value=""></c:set>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr class="odd gradeX">
												<td style="text-align: center;">${status.index + 1 }</td> 
												<td title="${list.examinationContent }">
													<d:titleLimit maxCount="200" str="${list.examinationContent }"/>
												</td> 
												<td title="${list.answer }" style="text-align: center;">
													<d:titleLimit maxCount="100" str="${list.answer }"/>
												</td> 
												<td style="text-align: center;">${list.type.typename }</td> 
												<td style="text-align: center;">
													<a class="edit" href="${basePath}tkExamination/update/${list.id}">修改</a>&nbsp;
													<a class="edit" href="javascript:void();" onclick="delData('${basePath}tkExamination/delete/${list.id}');">删除</a>
												</td>
											</tr>
											
											<c:set var="cTemp" value="${fn:substring(list.examinationContent, 0, 30)}"></c:set>
										</c:forEach>
									</tbody>
								</table>
								<p:page url="tkExamination/${cid}" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function queryByTypeCode(typeCode){
		$("#typeCode").val(typeCode);
		$("#searchForm").submit();
	}
	function queryBySourceId(sourceId){
		$("#sourceId").val(sourceId);
		$("#searchForm").submit();
	}
	function exportExcel(){
		var path = "${basePath }tkExamination/examinationExport";
		$('#searchForm').attr("action", path).submit();  
	}
	function search(){
		var path = "${basePath }tkExamination/${cid}";
		$('#searchForm').attr("action", path).submit();  
	}
	function batchDel(){
		if(confirm("您确定要进行批量删除吗？")){
			var path = "${basePath }tkExamination/batchDel/${cid}";
			$('#searchForm').attr("action", path).submit();  
		}
	}
	function theSort(f){
		$("#sort").val(f);
		$("#searchForm").submit();
	}
</script>
</html>
													