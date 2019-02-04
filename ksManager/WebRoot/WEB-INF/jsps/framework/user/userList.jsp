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
												<th class="hidden-480">邮箱</th>
												<th class="hidden-480">联系电话</th>
												<th class="span4">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="list" varStatus="status">
												<tr class="odd gradeX">
													<td class="hidden-480">${status.index+1 }</td>
													<td>${list.loginname }</td>
													<td>${list.truename }</td>
													<td class="hidden-480"><a href="mailto:${list.email }">${list.email }</a></td>
													<td class="hidden-480">${list.telephone }</td>
													<td>
														<div class="btn-group">
															<a class="btn purple" href="#" data-toggle="dropdown">
															<i class="icon-user"></i> 设置
															<i class="icon-angle-down"></i>
															</a>
															<ul class="dropdown-menu">
																<li><a href="${basePath}sysUser/update/${list.id}"><i class="icon-trash"></i> 修改</a></li>
																<li class="divider"></li>
																<li><a href="javascript:void();" onclick="delData('${basePath}sysUser/delete/${list.id}');"><i class="icon-remove"></i> 删除</a></li>
																<li class="divider"></li>
																<li><a href="javascript:void();" onclick="resetPwd('${list.id}','${list.truename }');"><i class="icon-share-alt"></i> 重置密码</a></li>
															</ul>
														</div>
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