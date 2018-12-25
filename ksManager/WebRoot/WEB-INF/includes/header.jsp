<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- BEGIN LOGO -->
			<a class="brand" href="${basePath}console" style="width:265px;">
				<img src="${resPath}media/image/logo.png" style="width:265px;color: white;"/>
			</a>
			
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="${resPath }media/image/menu-toggler.png" alt="" />
			</a>  
			<!-- END RESPONSIVE MENU TOGGLER -->            
			<!-- BEGIN TOP NAVIGATION MENU -->              
			<ul class="nav "  style="float:right;">
				<!-- BEGIN INBOX DROPDOWN -->
				<li class="dropdown" id="header_inbox_bar" style="dleft:-15px;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-envelope"></i>
						<span class="badge" id="msgBadgeSpan"></span>
					</a>
					<ul class="dropdown-menu extended inbox" style="margin-right: 15px;">
						<li>
							<p>您有<span id="msgCountSpan"></span>待处理消息</p>
						</li>
						<div id="msgListDiv">
							
						</div>
					</ul>
				</li>

				<!-- END INBOX DROPDOWN -->

				<!-- BEGIN USER LOGIN DROPDOWN -->
					
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="margin-top: 6px !important;">
						<span class="username">${sessionScope['LOGIN_USER'].loginname }(${sessionScope['LOGIN_USER'].truename })</span>
						<i class="icon-angle-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${basePath}sysUser/editUserPasswd"><i class="icon-user"></i> 密码修改</a></li>
						<li class="divider"></li>
						<li><a href="${basePath}sysUser/logout"><i class="icon-key"></i> 退出</a></li>

					</ul>
				</li>
				
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU --> 
		</div>
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<script type="text/javascript">
	jQuery(document).ready(function() { 
		findMsg();
		setInterval(findMsg,30000);
	});
	
	function findMsg(){
		$.ajax({
			type:"GET", 
			url:"${basePath}sysUserMessage/findMsg",
			dataType:"json",
			success:function(data){
				var json = eval(data);
				$("#header_inbox_bar").hide();
				$("#msgListDiv").html("");
				if(json.length == 0){
				}else{
					$("#msgBadgeSpan").html(json.length);
					$("#msgCountSpan").html(json.length);
					$(json).each(function(index,element){
						var s ="<li>";
							s+="<a href='${basePath}sysUserMessage/toUrl/"+element.id+"?url=" + element.url + "'>";
							s+="	<span class='message'>";
							s+=element.msgContent;
							s+="	</span>";
							s+="</a>";
							s+="</li>";
						$("#msgListDiv").append(s);
					});
					$("#header_inbox_bar").show();
				}
			}
		});
	}
	
</script>