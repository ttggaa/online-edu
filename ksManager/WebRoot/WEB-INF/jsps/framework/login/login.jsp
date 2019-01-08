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
	<style type="text/css">
	    html {
		    font-family: sans-serif;
		    line-height: 1.15;
		    -ms-text-size-adjust: 100%;
		    -webkit-text-size-adjust: 100%;
		}
		html, body {
		    width: 100%;
		    height: 100%;
		    overflow: hidden;
		    margin: 0;
		    padding: 0;
		}
		.login {
		    width: 100%;
		    height: 100%;
		    background-image: url(${resPath }images/login-bg.jpg);
		    background-size: cover;
		    background-position: center;
		    position: relative;
		}
		
		
	</style>
</head>
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img src="${resPath }images/logo/logo-kw.png" alt="" /> 
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form id="myForm" class="form-vertical login-form" action="${basePath }sysUser/login" method="post">
			<h3 class="form-title">${reqBusiness.businessName }</h3>
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">账号</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" id="loginname" name="loginname" value="${userName}" placeholder="账号"/>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input type="password" class="m-wrap placeholder-no-fix" id="passwd" name="passwd" placeholder="密码" value="${passwd}"/>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				    <input id="remember" name="remember" type="checkbox" value="1" ${rememberFlag=='1'?'checked=checked':''} > 记住密码
				</label>
			</div>
			<div class="form-actions">
				<!-- 
				<button type="button" class="btn pull-right" onclick="forgetPwd();">
				忘记密码
				</button>
				 -->
				<button type="button" class="btn pull-right green" onclick="login();" style="margin-right: 10px">
				登录 <i class="m-icon-swapright m-icon-white"></i>
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

	function login(){
		if($("#loginname").val() == ""){
			alert("账号不能为空!");
			$("#loginname").focus();
			return false;
		}else if($("#passwd").val() == ""){
			alert("密码不能为空!");
			$("#passwd").focus();
			return false;
		}
		$("#myForm").attr("action","${basePath }sysUser/login");
		$("#myForm").submit();
		return true;
	}
	
	function forgetPwd(){
		$("#myForm").attr("action","${basePath }forgetPwd");
		$("#myForm").submit();
	}
</script>
</html>