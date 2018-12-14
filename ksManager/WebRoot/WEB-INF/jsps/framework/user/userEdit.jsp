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
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/datepicker.css" />
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/bootstrap-toggle-buttons.css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="${resPath}media/css/select2_metro.css" />
	<link rel="stylesheet" href="${resPath}media/css/DT_bootstrap.css" />
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />
	
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
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							用户账号管理
							 <small>${action=='create'?'新增用户账号信息':'修改用户账号信息'}</small>
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
								
							</div>

							<div class="portlet-body form">

								<form id="inputForm" class="form-horizontal" action="${basePath}sysUser/${action}" method="post" >
									<input type="hidden" name="id" value="${user.id}" />
									<div class="form-wizard">
										<div class="tab-pane active" id="tab1">
											<div class="alert alert-error ${empty MESSAGE ?'hide':'' }">
												<button class="close" data-dismiss="alert"></button>
												<span>${MESSAGE }</span>
											</div>
											
											<div class="control-group">
												<label class="control-label">
													账号<span class="required">*</span>
												</label>
												<div class="controls">
													<input name="loginname" class="span6 m-wrap required" type="text" 
														placeholder="20位半角英文字符以内有效..." value="${user.loginname}" maxlength="20" />
													<span class="help-inline">系统登录唯一标识符,必添</span>
												</div>
											</div>
											<c:if test="${action == 'create'}">
												<div class="control-group">
													<label class="control-label">密码<span class="required">*</span></label>
													<div class="controls">
														<input id="passwd" name="passwd" class="m-wrap span6" type="password" placeholder="" maxlength="50" />
														<span class="help-inline">系统登录验证口令,必添</span>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">确认密码<span class="required">*</span></label>
													<div class="controls">
														<input id="rePasswd" class="m-wrap span6" equalTo="#passwd" type="password" placeholder="" maxlength="50" />
														<span class="help-inline">必添,与密码添写一致</span>
													</div>
												</div>
											</c:if>
											<div class="control-group">
												<label class="control-label">姓名</label>
												<div class="controls">
													<input name="truename" class="m-wrap span6" type="text" placeholder="" value="${user.truename}" maxlength="20"/>
												</div>
											</div>
											<div class="control-group"> 
											   <label class="control-label">部门</label> 
											   <div class="controls"> 
													<select data-placeholder="部门" class="chosen span6" tabindex="-1" id="selS0V" name="orgId" style="width:150px;">
														<option value=""></option>
														<c:forEach items="${orgList }" var="org">
															<optgroup label="${org.orgName }">
																<c:forEach items="${org.subList }" var="sub">
																	<option value="${sub.id }" ${user.orgId==sub.id?'selected=selected':'' } >${sub.orgName }</option>
																</c:forEach>
															</optgroup>
														</c:forEach>
													</select>
											   </div> 
											</div> 
											<div class="control-group">
												<label class="control-label">角色</label>
												<div class="controls">
													<c:forEach items="${listRole }" var="rList">
											   			<label class="checkbox">
															<input type="checkbox" name="roleIds" value="${rList.id }" ${rList.select != null && rList.select ==1?'checked=checked':'' }/> ${rList.roleName }
														</label>
											   		</c:forEach>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">性别${user.gender}</label>
												<div class="controls">
													<label class="radio">
															<span><input name="gender" type="radio" value="男" data-title="男" ${user.gender=='男'?'checked':'' } /></span>
														男
													</label>
													<label class="radio">
															<span><input name="gender" type="radio" value="女" data-title="女" ${user.gender=='女'?'checked':'' } /></span>
														女
													</label>  
													<div id="form_gender_error"></div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">联系电话</label>
												<div class="controls">
													<input name="telephone" class="m-wrap span6" type="text" placeholder="" value="${user.telephone}" maxlength="50" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">邮箱</label>
												<div class="controls">
													<input name="email" class="span6 m-wrap" type="text" value="${user.email}" maxlength="40"/>
													<span class="help-inline">常用电子邮箱地址</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">入职时间</label>
												<div class="controls">
													<div class="input-append date date-picker ">
														<input name="entryTime" size="16" type="text" value="${user.entryTime}" readonly class="m-wrap span8">
														<span class="add-on"><i class="icon-calendar"></i></span>
													</div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">启用状态</label>
												<div class="controls">
													<div class="danger-toggle-button">
														<input type="checkbox" class="toggle" ${user.state=='1'?'checked=checked':''} name="state" value="1"/>
													</div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">备注</label>
												<div class="controls">
													<textarea name="remark" class="span6 m-wrap" rows="3">${user.remark}</textarea>
												</div>
											</div>
										</div>

										<div class="form-actions clearfix">
											
											<button class="btn blue" type="submit"><i class="icon-ok"></i>完成</button>
											&nbsp;&nbsp;
											<a class="btn" onclick="cancle();">取消</a>
				
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
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
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
	
	<script type="text/javascript" src="${resPath}media/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${resPath}media/js/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${resPath}media/js/jquery.toggle.buttons.js"></script>
	<script>
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();
		   App.init();
           
           $('.date-picker').datepicker({
              autoclose: true,
              todayHighlight: true,
              language:"zh-CN", 
              format:"yyyy-mm-dd",
              clearBtn: true
           });
           
		   $('.danger-toggle-button').toggleButtons({
	            style: {
	                enabled: "info",
	                disabled: ""
	            }
	       });
			
		});
		
		function cancle(){
			window.location = "${basePath}sysUser";
		}
	</script>
</body>
<!-- END BODY -->
</html>