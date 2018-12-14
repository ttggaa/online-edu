<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>

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
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${resPath}media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${resPath}media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/select2_metro.css" />
	<link rel="stylesheet" href="${resPath}media/css/DT_bootstrap.css" />
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />
	<script>
		var stepIndex = 1;
		function setStepIndex(index) {
			stepIndex = index;
			changeState();
		}
	</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
	<!-- BEGIN HEADER -->
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<!-- BEGIN SIDEBAR -->
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<!-- END SIDEBAR -->
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							账户管理
						</h3>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">

					<div class="span12">

						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> 修改账户密码
								</div>
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}sysUser/saveUserPasswd" method="post" >
									<input type="hidden" name="id" value="${user.id}" />
									<div class="form-wizard">
											<div class="tab-pane active" id="tab1">
												<div class="alert ${empty MESSAGE_COLOR || MESSAGE_COLOR=='1'?'alert-success':'alert-error' } ${empty MESSAGE ?'hide':'' }">
													<button class="close" data-dismiss="alert"></button>
													<span>${MESSAGE }</span>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														账号
													</label>
													<div class="controls">
														<label class="control-label" style="text-align: left;">${user.loginname}</label>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">姓名</label>
													<div class="controls">
														<label class="control-label" style="text-align: left;">${user.truename}</label>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">原密码<span class="required">*</span></label>
													<div class="controls">
														<input id="oldpasswd" name="oldpasswd" class="m-wrap span6 required" type="password" placeholder="请录入原登录密码!">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">新密码<span class="required">*</span></label>
													<div class="controls">
														<input id="passwd" name="passwd" class="m-wrap span6 required" type="password" placeholder="请录入新的登录密码!">
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">确认密码<span class="required">*</span></label>
													<div class="controls">
														<input id="rePasswd" class="m-wrap span6 required" equalTo="#passwd" type="password" placeholder="请再次录入新的登录密码!">
													</div>
												</div>

										</div>

										<div class="form-actions clearfix">
											
											<button class="btn blue" type="submit"><i class="icon-ok"></i>完成</button>
				
										</div>
									</div>
								</form>
							</div>

						</div>

					</div>

				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">
			2013 &copy; Metronic by keenthemes.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${resPath}media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${resPath}media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="${resPath}media/js/excanvas.min.js"></script>
	<script src="${resPath}media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="${resPath}media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${resPath}media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="${resPath}media/js/select2.min.js"></script>
	<script type="text/javascript" src="${resPath}media/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${resPath}media/js/DT_bootstrap.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${resPath}media/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/jquery-validate-message.js" type="text/javascript"></script>
	<script src="${resPath}media/js/app.js"></script>   
	
	<script>
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
		});
	</script>
</body>
<!-- END BODY -->
</html>