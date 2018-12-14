<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
<%@ taglib prefix="dict" uri="/dict"%>
<%@ taglib prefix="data" uri="/data"%>
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
							试卷管理
						</h3>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<form id="searchForm" class="form-horizontal" action="${basePath }paper" method="post">
							<input type="hidden" id="businessId" name="map['businessId']" value="${searchParams.map['businessId'] }"/>
							<div class="portlet">
								<div class="portlet-title">
									<div class="input-prepend input-append">
										<span class="add-on"><i class="icon-search"></i></span>
										<input class="m-wrap placeholder-no-fix" type="text" 
											placeholder="按编号/名称查询..." id="paperIdOrName" name="map['paperIdOrName']" value="${searchParams.map['paperIdOrName'] }"/>
										<button type="submit" class="btn green pull-right" >查询</button> 
									</div>
								</div>
								<div class="portlet-title">
									<span>状态:</span> 
								    <select id="state" name="map['state']" class="span2 m-wrap">
							       		<dict:selectOption field="STATE" selCode="${searchParams.map['state']}" empty="true"/>
							   		</select>
							   		<span style="padding-left: 10px;">课程:</span> 
							   		<select id="courseId" name="map['courseId']" class="span2 m-wrap">
							       		<option value="" ></option>
								   		<c:forEach items="${courseList }" var="courseList">
								   			<option value="${courseList.id }" ${courseList.id==searchParams.map['courseId']?'selected=selected':'' }>${courseList.courseName }</option>
								   		</c:forEach>
							   		</select>
								</div>
							</div>
							<div class="alert alert-success ${empty MESSAGE ?'hide':'' }">
								<button class="close" data-dismiss="alert"></button>
								<span>${MESSAGE }</span>
							</div>
							<div class="portlet box light-grey">
								<div class="clearfix">
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn green" href="${basePath }paper/create">
												新增 <i class="icon-plus"></i>
											</a>
										</div>
										<div class="btn-group">
											<button class="btn green purple dropdown-toggle" data-toggle="dropdown">更新状态<i class="icon-angle-down"></i>
											</button>
											<ul class="dropdown-menu">
												<li><a href="javascript:void();" onclick="updateState('1');" >可用</a></li>
												<li class="divider"></li>
												<li><a href="javascript:void();" onclick="updateState('0');" >不可用</a></li>
											</ul>
										</div>
										
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn red" href="javascript:void()" onclick="delPaper();">
												删除 <i class="icon-trash"></i>
											</a>
										</div>
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn green" href="javascript:void()" onclick="buildPaper();">
												组卷 <i class="icon-plus"></i>
											</a>
										</div>
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn green" href="${basePath}paper/submitTkExaminationAll" >
												题库同步 <i class="icon-plus"></i>
											</a>
										</div>
									</div>
									<table class="table table-striped table-bordered table-hover" id="sample_1">
										<thead>
											<tr>
												<th style="width:80px;">
													<input type="checkbox" id="allChk" alt="全选" />
													编号
												</th>
												<th>试卷名称</th> 
												<th>课程</th> 
												<th>题量</th> 
												<th>总分</th> 
												<th>创建时间</th> 
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="list" varStatus="status">
												<tr class="odd gradeX">
													<td>
														<input type="checkbox" name="paperIds" id="ck_${status.index }" value="${list.id }" />
														${list.id }
													</td>
													<td>
														<a href="${basePath }paperExamination?paperId=${list.id }" >${list.paperName }</a>
														<c:if test="${list.state == '1'}">
															<span class="label label-info label-mini">可用</span>
														</c:if>
														<c:if test="${list.state == '0'}">
															<span class="label label-warning label-mini">不可用</span>
														</c:if>
													</td> 
													<td>${list.resCourse.courseName }</td> 
													<td>${list.quesCount }</td> 
													<td>${list.totalScore }</td> 
													<td>
														<fmt:parseDate value="${list.createTime}" var="createTime" pattern="yyyy-MM-dd HH:mm:ss" />
														<fmt:formatDate value="${createTime}" type="both" pattern="yyyy-MM-dd"/>
													</td> 
													<td >
														<a class="edit" href="${basePath}paper/update/${list.id}">修改</a>
														<a class="edit" href="javascript:void();" onclick="del('${list.id}');">删除</a>
														<div class="btn-group">
															<button class="btn mini purple dropdown-toggle" data-toggle="dropdown">更多 <i class="icon-angle-down"></i>
															</button>
															<ul class="dropdown-menu">
																<li><a href="${basePath}paper/copy/${list.id}" >复制</a></li>
																<li class="divider"></li>
																<li><a href="${basePath}paper/submitTkExamination/${list.id}" >提交总题库</a></li>
															</ul>
													   </div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<p:page url="paper" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">  
	
	jQuery(document).ready(function() { 		   
		$("#allChk").click(function(){
		});
	});
	function theSubmit(id){
		$("#businessId").val(id);
		$("#searchForm").submit();		
	}
	function buildPaper(){
		var url = "${basePath }paper/buildPaper";
		url = url + "?courseId=" + $("#courseId").val();
		url = url + "&businessId=" + $("#businessId").val();
		url = url + "&levelId=" + $("#levelId").val();
		var dg;
		dg = $.dialog({
		    lock: true,
		    title:'组卷设定',
		    content: 'url:' + url,
		    icon: 'error.gif',
		    width: 820,
		    height: 550,
		    init:function(){
		    },
		    ok: function () {
		    	dg.content.formSubmit();
		        return false;
		    },
		    cancel: true
		});
	}
	function del(id){
		var url = "${basePath}paper/delete/" + id;
		if(confirm("确定要删除试卷吗？")){
			//window.location = url;
			$("#searchForm").attr("action",url);
			$("#searchForm").submit();
		}
	}
	function updateState(state){
		var url = "${basePath}paper/updateState/" + state;
		var msg = "";
		if(state == '0'){
			msg = "确定要批量更新试卷为'不可用状态'吗？";
		}else{
			msg = "确定要批量更新试卷为'可用状态'吗？";
		}
		if(confirm(msg)){
			$("#searchForm").attr("action",url);
			$("#searchForm").submit();	
		}
	}
	function updateBusiness(bId,bName){
		if(bId == "") return false;
		var url = "${basePath}paper/updateBusiness/" + bId;
		var msg = "您确定要更新所选试卷的运营商为[" + bName + "]吗？";
		if(confirm(msg)){
			$("#searchForm").attr("action",url);
			$("#searchForm").submit();	
		}
	}
	function batchCopyBusiness(bId,bName){
		if(bId == "") return false;
		var url = "${basePath}paper/batchCopyBusiness/" + bId;
		var msg = "您确定要复制所选试卷并粘贴至[" + bName + "]运营商吗？";
		if(confirm(msg)){
			$("#searchForm").attr("action",url);
			$("#searchForm").submit();	
		}
	}
	function delPaper(){
		var url = "${basePath}paper/batchDelPaper";
		if(confirm("您确定要删除选中试卷吗？")){
			$("#searchForm").attr("action",url);
			$("#searchForm").submit();	
		}
	}
</script>
</html>
