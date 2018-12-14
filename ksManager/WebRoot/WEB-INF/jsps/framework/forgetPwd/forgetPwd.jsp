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
			<h5 class="form-title">请输入你需要找回登录密码的账户名</h5>
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
				<label class="control-label visible-ie8 visible-ie9">验证码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input type="text" class="m-wrap placeholder-no-fix" id="randCode" name="randCode" placeholder="验证码" style="width:55%;"/>
						<img type="image" src="${basePath}rand?d=new Date();" id="imgCode" onclick="chageCode()" title="图片看不清？点击重新得到验证码" style="cursor:pointer;"/>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" class="btn green pull-right" onclick="theSubmit();">
				确定 <i class="m-icon-swapright m-icon-white"></i>
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

	function theSubmit(){
		if($("#loginname").val() == ""){
			alert("账号不能为空!");
			$("#loginname").focus();
			return false;
		}else if($("#randCode").val() == ""){
			alert("验证码不能为空!");
			$("#randCode").focus();
			return false;
		}
		
		$("#myForm").submit();
		return true;
	}
	
	function chageCode(){
		var timenow = new Date().getTime();
		$("#imgCode").attr("src","${basePath}rand?d="+timenow);

	}
</script>
</html>