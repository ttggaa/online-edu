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
	<link href="${resPath}media/css/profile.css" rel="stylesheet" type="text/css" />
	<link href="${resPath}media/css/timeline.css" rel="stylesheet" type="text/css"/>
</head>
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							控制台 <small>${business.businessName }</small>
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-bullhorn"></i>
								<a href="#">欢迎使用，新年活动期间，充值更加优惠！</a> 
							</li>
							<li style="float: right;color: blue;">
								<div class="pull-left">
									<span>账户余额 ${business.account } 元</span> &nbsp;&nbsp;<a href="javascript:void();" onclick="alert('暂未开通线上支付，请联系客服人员进行购买，谢谢支持!');"><立即充值></a>&nbsp;&nbsp; 
								</div>
							</li>
						</ul>
					</div>
					
				</div>
				<div class="row-fluid">
					<div class="span2">
						<a href="http://${business.domain }.exam.linghang-tech.com" class="btn icn-only blue" target="_blank">打开考试系统 <i class="m-icon-swapright m-icon-white"></i></a>
						<div class="blog-tag-data">
							<img src="${resPath }images/lc_back.png" alt="" style="width:130px;height:70px;">
						</div>
					</div>
					<div class="span4"> 
					    <div class="control-group">
							<div class="controls">
								<div class="input-prepend"><span class="add-on">系统名称</span><input id="sysName" class="m-wrap" type="text" placeholder="考试登录页面显示" value="${business.businessName }"/></div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend"><span class="add-on">系统摘要</span><input id="sysSummary" class="m-wrap " type="text" placeholder="考试登录页面显示" value="${business.summary }"/></div>
							</div>
						</div>
					</div>
					<div class="span1" style="margin-left:0px;"> 
						<a href="javascript:void();" class="btn icn-only green" onclick="saveSysInfo();">保存</a> 
					</div>
				</div>
				<div class="row-fluid profile">
					<div class="span12">
						<div class="row-fluid portfolio-block">
							<div class="span5 portfolio-text">
								<div class="portfolio-text-info">
									<h4>1000 元套餐仅599</h4>
									<p>活动截止2019-3-31.</p>
								</div>
							</div>
							<div class="span5" style="overflow:hidden;">
								<div class="portfolio-info">
									充值金额
									<span>1000元</span>
								</div>
								<div class="portfolio-info">
									商品价格
									<span>599元</span>
								</div>
							</div>
							<div class="span2 portfolio-btn">
								<a href="javascript:void();" onclick="alert('暂未开通线上支付，请联系客服人员进行购买，谢谢支持!');" class="btn bigicn-only"><span>购买</span></a>                      
							</div>
						</div>
						<div class="row-fluid portfolio-block">
							<div class="span5 portfolio-text">
								<div class="portfolio-text-info">
									<h4>3000 元套餐仅1200</h4>
									<p>活动截止2019-3-31.</p>
								</div>
							</div>
							<div class="span5" style="overflow:hidden;">
								<div class="portfolio-info">
									充值金额
									<span>3000元</span>
								</div>
								<div class="portfolio-info">
									商品价格
									<span>1200元</span>
								</div>
							</div>
							<div class="span2 portfolio-btn">
								<a href="javascript:void();" onclick="alert('暂未开通线上支付，请联系客服人员进行购买，谢谢支持!');" class="btn bigicn-only"><span>购买</span></a>                      
							</div>
						</div>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="span12">
						<ul class="timeline">
						    <c:forEach items="${examingList}" var="exam" varStatus="status">
								<li class="${status.index % 2 == 0?'timeline-yellow':'timeline-blue' }">
									<div class="timeline-time">
										<span class="date">${exam.viewDate }</span>
										<span class="time">${exam.viewTime }</span>
									</div>
									<div class="timeline-icon"><i class="icon-time"></i></div>
									<div class="timeline-body">
										<h2>${exam.examName }</h2>
										<div class="timeline-content">
											参考人数：${exam.examUserCount }人&nbsp;&nbsp;&nbsp;&nbsp; 考试结束时间：${exam.examEndtime }
										</div>
										<div class="timeline-footer">
											<a href="${basePath }exam/update/${exam.id}" class="nav-link pull-right">
											详情 <i class="m-icon-swapright m-icon-white"></i>                              
											</a>  
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BEGIN FOOTER -->
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
	<!-- END FOOTER -->
	
	<script type="text/javascript">
		function saveSysInfo(){
			$.ajax({
				type:"GET", 
				url:"${basePath}console/saveSysInfo",
				dataType:"json",
				data:{
					sysName: $("#sysName").val(),
					sysSummary: $("#sysSummary").val()
				},
				success:function(data){
					var json = eval(data);
					alert("保存成功");
				}
			});
		}
	</script>
</body>
</html>
