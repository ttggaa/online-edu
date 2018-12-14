<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<!DOCTYPE html>
<head>
	<title>${systemName }</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="${resPath}media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${resPath}media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${resPath}media/css/login.css" rel="stylesheet" type="text/css"/>
	<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />
</head>
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img src="${resPath }images/logo/edplogo.png" alt="" /> 
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form id="myForm" class="form-vertical login-form" action="${basePath }forgetPwd/submitForgetPwd" method="post">
			<h5 class="form-title">密码重置成功！</h5>
			<div class="control-group">
				请妥善保管您的密码。
			</div>
			<div class="form-actions">
				<button type="button" class="btn green pull-right" onclick="theBack();">
				返回登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			<c:if test="${not empty MESSAGE}">
		        <div class="form-group" style="color: red;">
		        	${MESSAGE}
		        </div>
	        </c:if>
		</form>
		<!-- END REGISTRATION FORM -->
	</div>
</body>
<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script> 
<script src="${resPath}media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<script src="${resPath}media/js/app.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {     
	  App.init();
	});
	function theBack(){
		window.location = "${basePath}";
	}
</script>
</html>