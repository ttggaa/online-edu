<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
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
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<div class="page-container">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							菜单管理
							<small>${action=='create'?'新增菜单信息':'修改菜单信息'}</small>
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
								<form id="inputForm" class="form-horizontal" action="${basePath}framework/sysMenu/${action}" method="post" >
									<input type="hidden" name="id" value="${sysMenu.id }"/>
									<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       菜单名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="name" class="span6 m-wrap required" type="text" value="${sysMenu.name}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       连接URL
									   </label> 
									   <div class="controls"> 
									       <input name="linkUrl" class="span6 m-wrap" type="text" value="${sysMenu.linkUrl}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       菜单说明
									   </label> 
									   <div class="controls"> 
									       <input name="remark" class="span6 m-wrap" type="text" value="${sysMenu.remark}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       上级菜单<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <select id="pid" name="pid" class="span6 m-wrap required">
									       		<option value="-1" >根</option>
										   		<c:forEach items="${menuList }" var="menuList">
										   			<option value="${menuList.id }" ${menuList.id==sysMenu.pid?'selected=selected':'' }>${menuList.name }</option>
										   		</c:forEach>
									   		</select>
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       菜单图标<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input id="icon" name="icon" class="span6 m-wrap" type="text" value="${sysMenu.icon}"> 
									       <div class="btn-group">
												<a class="btn green" href="#" data-toggle="dropdown">
													选择图标
													<i class="icon-angle-down"></i>
												</a>
												<ul class="dropdown-menu">
													<li><a href="#" onclick="$('#icon').val('icon-user');"><i class="icon-user"></i> User</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-pencil');"><i class="icon-pencil"></i> Edit</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-trash');"><i class="icon-trash"></i> Delete</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-ban-circle');"><i class="icon-ban-circle"></i> Ban</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-wrench');"><i class="icon-wrench"></i> icon-wrench</a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="$('#icon').val('icon-compass');"><i class="icon-compass"></i> icon-compass</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-eur');"><i class="icon-eur"></i> icon-eur</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-file-text');"><i class="icon-file-text"></i> icon-file-text</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-windows');"><i class="icon-windows"></i> icon-windows</a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="$('#icon').val('icon-male');"><i class="icon-male"></i> icon-male</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-bug');"><i class="icon-bug"></i> icon-bug</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-foursquare');"><i class="icon-foursquare"></i> icon-foursquare</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-asterisk');"><i class="icon-asterisk"></i> icon-asterisk</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-qrcode');"><i class="icon-qrcode"></i> icon-qrcode</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-hospital');"><i class="icon-hospital"></i> icon-hospital</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-cogs');"><i class="icon-cogs"></i> icon-cogs</a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="$('#icon').val('icon-list-alt');"><i class="icon-list-alt"></i> icon-list-alt</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-cloud');"><i class="icon-cloud"></i> icon-cloud</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-search');"><i class="icon-search"></i> icon-search</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-random');"><i class="icon-random"></i> icon-random</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-columns');"><i class="icon-columns"></i> icon-columns</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-th-large');"><i class="icon-th-large"></i> icon-th-large</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-hdd');"><i class="icon-hdd"></i> icon-hdd</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-retweet');"><i class="icon-retweet"></i> icon-retweet</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-h-sign');"><i class="icon-h-sign"></i> icon-h-sign</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-table');"><i class="icon-table"></i> icon-table</a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="$('#icon').val('icon-bar-chart');"><i class="icon-bar-chart"></i> icon-bar-chart</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-group');"><i class="icon-group"></i> icon-group</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-globe');"><i class="icon-globe"></i> icon-globe</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-paper-clip');"><i class="icon-paper-clip"></i> icon-paper-clip</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-maxcdn');"><i class="icon-maxcdn"></i> icon-maxcdn</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-jpy');"><i class="icon-jpy"></i> icon-jpy</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-dropbox');"><i class="icon-dropbox"></i> icon-dropbox</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-css3');"><i class="icon-css3"></i> icon-css3</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-map-marker');"><i class="icon-map-marker"></i> icon-map-marker</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-expand');"><i class="icon-expand"></i> icon-expand</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-beaker');"><i class="icon-beaker"></i> icon-beaker</a></li>
													<li><a href="#" onclick="$('#icon').val('icon-trello');"><i class="icon-trello"></i> icon-trello</a></li>
												</ul>
											</div> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       排序号<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="orderIndex" class="span6 m-wrap required" type="text" value="${action=='create'?0:sysMenu.orderIndex}"> 
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
	<script>
		function cancle(){
			window.location = "${basePath}framework/sysMenu";
		}
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		});
	</script>
</body>
</html>
