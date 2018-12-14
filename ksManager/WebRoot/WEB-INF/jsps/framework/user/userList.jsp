<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
<%@ taglib uri="/data" prefix="d"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>后台管理系统</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<%@ include file="/WEB-INF/includes/common-component-list.jsp"%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->	
	<div class="page-container row-fluid">
		<!-- BEGIN SIDEBAR -->
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							用户账号管理
						</h3>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet box light-grey">
							<div class="portlet-title"></div>
							<div class="portlet-body">
								<div class="portlet-body form">
									<form id="searchForm" class="horizontal-form" action="${basePath }sysUser" method="post">
										<div class="row-fluid">
											<div class="span3">
												<div class="control-group">
													<label class="control-label" for="loginname">账号</label>
													<div class="controls">
														<input class="m-wrap span12" type="text" placeholder="按登录账号查询" id="loginname" name="map['loginname']" value="${searchParams.map['loginname'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="span3">
												<div class="control-group">
													<label class="control-label" for="truename">姓名</label>
													<div class="controls">
														<input class="m-wrap span12" type="text" placeholder="按姓名查询" id="truename" name="map['truename']" value="${searchParams.map['truename'] }"/>													
													</div>
												</div>
											</div>
											
											<div class="span3">
												<div class="control-group">
													<label class="control-label" for="orgId">部门</label>
													<div class="controls">
														<select id="orgId" name="map['orgId']">
															<option value="">全部</option>
													   		<c:forEach items="${orgList }" var="org">
													   			<option value="${org.id }" ${org.id==searchParams.map['orgId']?'selected=selected':'' }>${org.orgName }</option>
													   		</c:forEach>
												   		</select>												
													</div>
												</div>
											</div>
											<div class="span3">
												<div class="control-group">
													<label class="control-label" for="roleId">角色</label>
													<div class="controls">
														<select id="roleId" name="map['roleId']">
															<option value="">全部</option>
													   		<c:forEach items="${listRole }" var="rList">
													   			<option value="${rList.id }" ${rList.id==searchParams.map['roleId']?'selected=selected':'' }>${rList.roleName }</option>
													   		</c:forEach>
												   		</select>												
													</div>
												</div>
											</div>
											<!--/span-->
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
								<!-- BEGIN EXAMPLE TABLE PORTLET-->
								<div class="portlet box light-grey">
									<div class="clearfix">
										<div class="btn-group">
											<a id="sample_editable_1_new" class="btn green" href="${basePath }sysUser/create">
												新增 <i class="icon-plus"></i>
											</a>
										</div>
									</div>
									<table class="table table-striped table-bordered table-hover" id="sample_1">
										<thead>
											<tr>
												<th class="hidden-480" style="width:40px;">序号</th>
												<th>账号</th>
												<th>姓名</th>
												<th class="hidden-480">部门</th>
												<th class="hidden-480 span4">角色</th>
												<th class="hidden-480">邮箱</th>
												<th class="hidden-480">联系电话</th>
												<th style="width:200px;">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="list" varStatus="status">
												<tr class="odd gradeX">
													<td class="hidden-480">${status.index+1 }</td>
													<td>${list.loginname }</td>
													<td>${list.truename }</td>
													<td class="hidden-480"><d:view tableName="sys_org" field="org_name" primaryValue="${list.orgId }"/></td>
													<td class="hidden-480">${list.roles }</td>
													<td class="hidden-480"><a href="mailto:${list.email }">${list.email }</a></td>
													<td class="hidden-480">${list.telephone }</td>
													<td>
														<a href="${basePath}sysUser/update/${list.id}" class="btn mini purple"><i class="icon-edit"></i> 修改</a>
														<a href="javascript:void();" onclick="delData('${basePath}sysUser/delete/${list.id}');" class="btn mini black"><i class="icon-trash"></i> 删除</a>
														<a href="javascript:void();" onclick="resetPwd('${list.id}','${list.truename }');" class="btn mini black"><i class="icon-share-alt"></i> 密码重置</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<p:page url="sysUser" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }" formId="searchForm"/>
								</div>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
	<!-- END FOOTER -->
</body>
<script type="text/javascript">	
	function resetPwd(uid,truename){
		if(confirm("您确认要重置" + truename + "的密码吗？")){
			var url = "${basePath}sysUser/resetPwd/" + uid;
			window.location=url;
		}
	}
</script>
<!-- END BODY -->
</html>