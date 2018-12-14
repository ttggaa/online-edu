<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
<%@ taglib uri="/data" prefix="d"%>
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
							试卷明细管理
						</h3>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<form id="searchForm" class="form-horizontal" action="${basePath }paperExamination" method="post">
							<div class="portlet">
								<div class="portlet-title">
								   <div class="control-group">
										试卷：
										<select id="paperId" name="map['paperId']" class="span4 m-wrap">
								       		<option value="" ></option>
									   		<c:forEach items="${paperList }" var="paperList">
									   			<option value="${paperList.id }" ${paperList.id==searchParams.map['paperId']?'selected=selected':'' }>${paperList.paperName }</option>
									   		</c:forEach>
								   		</select>
										试题内容：
										<input class="m-wrap placeholder-no-fix" type="text" id="examinationContent" name="map['examinationContent']" value="${searchParams.map['examinationContent'] }"/>
								   		
								   		题型：
								   		<select id="typeCode" name="map['typeCode']" class="span2 m-wrap required" >
								       		<option value="" ></option>
									   		<c:forEach items="${typeList }" var="typeList">
									   			<option value="${typeList.typeCode }" ${typeList.typeCode==searchParams.map['typeCode']?'selected=selected':'' }>${typeList.typename }</option>
									   		</c:forEach>
								   		</select>
										<button type="submit" class="btn green" >查询</button> &nbsp;
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
							</div>
						
							<div class="alert alert-success ${empty MESSAGE ?'hide':'' }">
								<button class="close" data-dismiss="alert"></button>
								<span>${MESSAGE }</span>
							</div>
							<div class="portlet box light-grey">
								<div class="clearfix">
									<div class="btn-group">
										<a id="sample_editable_1_new" class="btn green" href="${basePath }paperExamination/create?paperId=${searchParams.map['paperId']}">
											新增 <i class="icon-plus"></i>
										</a>
									</div>
									<div class="btn-group">
										<button class="btn info purple dropdown-toggle" data-toggle="dropdown">更多操作<i class="icon-angle-down"></i>
										</button>
										<ul class="dropdown-menu">
											<li>
												<a href="javascript:void();" onclick="batchDelete();" data-role="leaf" id="nut">批量删除</a>
											</li>
											<li>
												<a href="javascript:void();" onclick="selectQues();" data-role="leaf" id="nut">题库选题</a>
											</li>
										</ul>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover" id="sample_1" style="table-layout: fixed;">
									<thead>
										<tr>
											<th style="width:40px;">序号</th>
											<th style="width:100px;">试卷</th>
											<th >题目内容</th> 
											<th style="width:40px;">题型</th> 
											<th style="width:80px;">答案</th> 
											<th style="width:40px;">分数</th> 
											<th style="width:40px;">源题号</th> 
											<th style="width:40px;">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" name="ids" id="ck_${status.index }" value="${list.id }" />
													${status.index+1 }
												</td>
												<td>${list.paper.paperName }<br/>
													<c:if test="${list.examinationDescription != ''}">
														<span class="label label-info label-mini">解析</span>
													</c:if>
													<c:if test="${list.auditState == '1'}">
														<span class="label label-mini">已审核</span>
													</c:if>
												</td>
												<td><pre style="line-height:100%;">${list.examinationContent }</pre></td> 
												<td>${list.quesType.typename }</td> 
												<td><d:titleLimit maxCount="8" str="${list.answer }"/></td> 
												<td>${list.defaultPoint }</td> 
												<td>${list.qid }</td> 
												<td >
													<a class="edit" href="${basePath}paperExamination/update/${list.id}">修改</a>&nbsp;
													<a class="edit" href="${basePath}paperExamination/copy/${list.id}">复制</a>&nbsp;
													<a class="edit" href="javascript:void();" onclick="theDel('${list.id}');">删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<p:page url="paperExamination" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
  function theDel(id){
	  if(id == "") return false;
	  if(confirm("确认要进行删除吗？")){
		  var url = "${basePath}paperExamination/delete/" + id;
		  location.href = url;
	  }
  }
  function batchDelete(){
	  if(confirm("确认要进行批量删除吗？")){
		  var url = "${basePath}paperExamination/batchDelete";
		  $("#searchForm").attr("action",url);
		  $("#searchForm").submit();	
	  }
  }
  function selectQues(){
	  var paperId = $("#paperId").val();
	  if(paperId == "") {
		  alert("请先选择要加入试题的试卷后再试！");
		  return ;
	  }
	  var url = "${basePath}paperExamination/selectQues?paperId=" + paperId;
	  $("#searchForm").attr("action",url);
	  $("#searchForm").submit();	
  }
</script>
</html>
