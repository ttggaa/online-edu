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
							部门管理
						</h3>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span2">
						<div class="portlet box grey">
							<div class="portlet-title">
								<div class="caption"><i class="icon-comments"></i>部门信息</div>
							</div>
							<div class="portlet-body fuelux">
								<ul class="tree" id="tree_2">
									<li>
										<a href="javascript:void();" onclick="theSubmit('');" 
											data-role="branch" class="tree-toggle" data-toggle="branch" data-value="Bootstrap_Tree">顶级部门
										</a>
										<ul class="branch in">
											<c:forEach items="${parentOrgList}" var="list" varStatus="status">
												<li title="${list.orgName }">
													<a href="javascript:void();" onclick="theSubmit('${list.id }');" data-role="leaf" id="nut">${list.orgName }</a>
												</li>
											</c:forEach>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="span10">
						<div class="portlet box light-grey">
							<div class="portlet-title"></div>
							<div class="portlet-body">
								<div class="portlet-body form">
									<form id="searchForm" class="horizontal-form" action="${basePath }framework/sysOrg" method="post">
										<input type="hidden" id="pidHid" name="map['pid']" value="${searchParams.map['pid'] }" />
										<div class="row-fluid">
											<div class="span4">
												<div class="control-group">
													<label class="control-label" for="orgName">部门名称</label>
													<div class="controls">
														<input class="m-wrap span12" type="text" placeholder="部门名称..." id="orgName" name="map['orgName']" value="${searchParams.map['orgName'] }"/>													
													</div>
												</div>
											</div>
										</div>
										<div style="text-align:center;">
											<button type="submit" class="btn blue"><i class="icon-ok"></i> 查询</button>
											<button type="button" class="btn" onclick="resetForm('searchForm');">重置</button>
										</div>
									</form>
								</div>
								<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
									<button class="close" data-dismiss="alert"></button>
									<span>${MESSAGE }</span>
								</div>
								<div class="portlet box light-grey">
									<div class="clearfix">
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn green" href="${basePath }framework/sysOrg/create">
												新增 <i class="icon-plus"></i>
											</a>
										</div>
									</div>
									<table class="table table-striped table-bordered table-hover" id="sample_1">
										<thead>
											<tr>
												<th style="width:40px;">序号</th>
												<th>部门名称</th> 
												<th class="hidden-480">部门总监</th>
												<th class="hidden-480">上级部门</th>
												<th class="hidden-480">简介</th> 
												<th style="width:130px;">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="list" varStatus="status">
												<tr class="odd gradeX">
													<td>${status.index+1 }</td>
													<td>
														${list.orgName }
													</td>
													<td class="hidden-480">${list.usernameView }</td> 
													<td class="hidden-480">
														<c:if test="${list.pid==0 }">
															<span class="label label-info">顶级部门</span>
														</c:if>
														<c:if test="${list.pid>0 }">
															<span class="label label-important">${list.parentOrgName }</span>
														</c:if>
													</td>
													<td class="hidden-480">${list.remark }</td> 
													<td>
														<a href="${basePath}framework/sysOrg/update/${list.id}" class="btn mini purple"><i class="icon-edit"></i> 修改</a>
														<a href="javascript:void();" onclick="delData('${basePath}framework/sysOrg/delete/${list.id}');" class="btn mini black"><i class="icon-trash"></i> 删除</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<p:page url="framework/sysOrg" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }" formId="searchForm"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">  
	function theSubmit(pid){
		$("#pidHid").val(pid);
		$("#searchForm").submit();		
	}
</script>
</html>
