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
							菜单管理
						</h3>
					</div>
				</div>
				
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box light-grey">
							<div class="portlet-title"></div>
							<div class="portlet-body">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<form id="searchForm" class="horizontal-form" action="${basePath }framework/sysMenu" method="post">
										<div class="row-fluid">
											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="name">菜单名称</label>
													<div class="controls">
														<input class="m-wrap span10" type="text" placeholder="菜单名称..." id="name" name="map['name']" value="${searchParams.map['name'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="linkUrl">连接URL</label>
													<div class="controls">
														<input class="m-wrap span14" type="text" placeholder="连接URL..." id="linkUrl" name="map['linkUrl']" value="${searchParams.map['linkUrl'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->

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
								<div class="clearfix">
									<div class="btn-group">
										<button class="btn green" onclick="javascript:window.location='${basePath }framework/sysMenu/create';">
										新增 <i class="icon-plus"></i>
										</button>
									</div>
								</div>
								
								<table class="table table-striped table-bordered table-hover" id="sample_1">
									<thead>
										<tr>
											<th style="width:40px;" class="hidden-480">序号</th>
											<th>菜单名称</th> 
											<th class="hidden-480">连接URL</th> 
											<th class="hidden-480">菜单说明</th> 
											<th class="hidden-480">上级菜单</th> 
											<th class="hidden-480">菜单图标</th> 
											<th class="hidden-480">更新时间</th> 
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr class="odd gradeX">
												<td class="hidden-480">${status.index+1 }</td>
												<td>${list.name }</td> 
												<td class="hidden-480">${list.linkUrl }</td> 
												<td class="hidden-480">${list.remark }</td> 
												<td class="hidden-480">${list.pid }</td> 
												<td class="hidden-480">${list.icon }</td> 
												<td class="hidden-480">${list.updateTime }</td> 
												<td>
													<a href="${basePath}framework/sysMenu/update/${list.id}" class="btn mini purple"><i class="icon-edit"></i> 修改</a>
													<a href="javascript:void();" onclick="delData('${basePath}framework/sysMenu/delete/${list.id}');" class="btn mini black"><i class="icon-trash"></i> 删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<p:page url="framework/sysMenu" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }" formId="searchForm" />
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BEGIN FOOTER -->
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
	<!-- END FOOTER -->
</body>
</html>
