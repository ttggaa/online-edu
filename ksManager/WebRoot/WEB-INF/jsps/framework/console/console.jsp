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
								<a href="#">*******</a> 
							</li>
						</ul>
					</div>
					
				</div>
				<a href="http://${business.domain }.exam.linghang-tech.com" class="btn icn-only blue" target="_blank">打开考试系统 <i class="m-icon-swapright m-icon-white"></i></a>   	
				<div class="row-fluid profile">
					
					<div class="span12">
						<div class="row-fluid add-portfolio">
							<div class="pull-left">
								<span>账户余额 ${business.account } 元</span>
							</div>
							<div class="pull-left">
								<a href="#" class="btn icn-only green">充值 <i class="m-icon-swapright m-icon-white"></i></a>                          
							</div>
						</div>
						<div class="row-fluid portfolio-block">
							<div class="span5 portfolio-text">
								<img src="media/image/logo_metronic.jpg" alt="" />
								<div class="portfolio-text-info">
									<h4>1000 元套餐仅499</h4>
									<p>活动截止2018-12-31.</p>
								</div>
							</div>
							<div class="span5" style="overflow:hidden;">
								<div class="portfolio-info">
									充值金额
									<span>1000元</span>
								</div>
								<div class="portfolio-info">
									商品价格
									<span>499元</span>
								</div>
							</div>
							<div class="span2 portfolio-btn">
								<a href="#" class="btn bigicn-only"><span>BUY</span></a>                      
							</div>
						</div>
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
