<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib prefix="data" uri="/data"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>${systemName }</title>
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
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${resPath}media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${resPath}media/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/app.js" type="text/javascript"></script>
	<script src="${resPath}media/js/index.js" type="text/javascript"></script>        
	<!-- END PAGE LEVEL SCRIPTS -->  
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />
	
</head>

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
		<style>
			.tiles{
				margin-top:10px;
			}
			.tile {
			  display: block;
			  letter-spacing: 0.02em;
			  float: left;
			  height: 5.9em;
			  width: 5.9em !important;
			  cursor: pointer;
			  text-decoration: none;
			  color: #ffffff;
			  position: relative;
			  font-weight: 300;
			  font-size: 12px;
			  letter-spacing: 0.02em;
			  line-height: 20px;
			  font-smooth: always;
			  overflow: hidden;
			  border: 4px solid transparent;
			  margin: 0 0 9px 7.5px;
			  border-radius: 5px !important;
			}
			
			.tile .tile-body > i {
			  margin-top: -8px;
			  display: block;
			  font-size: 40px;
			  text-align: center;
			}
			
			.tile .tile-object > .name {
			  position: absolute;
			  bottom: 0;
			  left: 0;
			  margin-bottom: 5px;
			  margin-left: 1px;
			  margin-right: 11px;
			  margin-top:5px;
			  font-weight: 400;
			  font-size: 12px;
			  font-smooth: always;
			  color: #ffffff;
			  width:100%;
			  text-align: center;
			}
			
			.tile .tile-object > .number {
			  position: absolute;
			  bottom: 0;
			  right: 0;
			  margin-bottom: 0;
			  color: #ffffff;
			  text-align: center;
			  font-weight: 600;
			  font-size: 12px;
			  letter-spacing: 0.01em;
			  line-height: 14px;
			  font-smooth: always;
			  margin-bottom: 8px;
			  margin-right: 1px;
			  float:right;
			}
			
			.tile.double {
			  width: 32em !important;
			}
			.tile2 {
				  margin: 0 0px 9px 9px;
				  width: 12.9em !important;
				  border-radius: 5px !important;
			}
			.guanggao{
				margin-bottom: 10px;
			}
			
			/* 判断iphone7plus*  */
			@media only screen and (min-device-width : 414px) and (max-device-width : 736px){
				.tile {
					  margin: 0 0 9px 9px;
					  height: 6.9em;
					  width: 6.9em !important;
					  border-radius: 5px !important;
				}
				.tile2 {
					  margin: 0 0 9px 9px;
					  height: 6.9em;
					  width: 15.2em !important;
					  border-radius: 5px !important;
				}
				.tile .tile-body > i {
				  margin-top: -2px;
				  font-size: 40px;
				}
				
			}
			/* 判断小米note3*  */
			@media only screen and (min-device-width : 393px) and (max-device-width : 393px) and (orientation : portrait){
				.tile {
					  margin: 0 0 9px 9px;
					  height: 6.45em;
					  width: 6.45em !important;
					  border-radius: 5px !important;
				}
				.tile2 {
					  margin: 0 0 9px 9px;
					  height: 6.9em;
					  width: 14.2em !important;
					  border-radius: 5px !important;
				}
				.tile .tile-body > i {
				  margin-top: -2px;
				  font-size: 40px;
				}
				
			}
			
			/* 判断PC  */
			@media only screen and (min-device-width : 700px){
				
				.tile {
					  margin: 0 0 9px 9px;
					  height: 6.45em;
					  width: 9.45em !important;
					  display: none;
					  border-radius: 5px !important;
				}
				.tile .tile-body > i {
				  margin-top: -2px;
				  font-size: 40px;
				}
				
			}
		</style>
		<!-- BEGIN PAGE -->
		<div class="page-content" style="padding:0px !important;">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<!-- BEGIN PAGE CONTENT-->
				<div class="tiles">
					<div class="tile bg-blue" onclick="javascript:window.location='${basePath }project'">
						<div class="tile-body">
							<i class="icon-search"></i>
						</div>
						<div class="tile-object">
							<div class="name">项目查询</div>
						</div>
					</div>
					<div class="tile bg-blue" onclick="javascript:window.location='${basePath }courseTeaResource'">
						<div class="tile-body">
							<i class="icon-globe"></i>
						</div>
						<div class="tile-object">
							<div class="name">课程查询</div>
						</div>
					</div>
					<div class="tile bg-blue" onclick="javascript:window.location='${basePath }hotelResourceQuery'">
						<div class="tile-body">
							<i class="icon-beaker"></i>
						</div>
						<div class="tile-object">
							<div class="name">酒店查询</div>
						</div>
					</div>
					<div class="tile bg-blue" onclick="javascript:window.location='${basePath }classroomResourceQuery'">
						<div class="corner"></div>
						<div class="tile-body">
							<i class="icon-inbox"></i>
						</div>
						<div class="tile-object">
							<div class="name">教室查询</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div class="tile bg-yellow tile2" onclick="javascript:window.location='${basePath}myProject/create'">
							<div class="tile-body">
								<i class="icon-plus"></i>
							</div>
							<div class="tile-object">
								<div class="name">创建项目</div>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div class="tile bg-green tile2 " onclick="javascript:window.location='${basePath}myProject'">
							<div class="tile-body">
								<i class="icon-comments-alt"></i>
							</div>
							<div class="tile-object">
								<div class="name">我的项目</div>
							</div>
						</div>
					</div>
				
					<d:existMenu requestMapping="projectAudit">
						<div class="tile bg-grey" onclick="javascript:window.location='${basePath}projectAudit'">
							<div class="tile-body">
								<i class="icon-retweet"></i>
							</div>
							<div class="tile-object">
								<div class="name">项目审批</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectCourseplanReq">
						<div class="tile bg-green" onclick="javascript:window.location='${basePath}projectCourseplanReq'">
							<div class="tile-body">
								<i class="icon-calendar"></i>
							</div>
							<div class="tile-object">
								<div class="name">师资安排</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectClassroomPlan">
						<div class="tile bg-red" onclick="javascript:window.location='${basePath}logistics/projectClassroomPlan'">
							<div class="tile-body">
								<i class="icon-inbox"></i>
							</div>
							<div class="tile-object">
								<div class="name">教室安排</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectStationPlan">
						<div class="tile bg-purple" onclick="javascript:window.location='${basePath}logistics/projectStationPlan'">
							<div class="tile-body">
								<i class="icon-truck"></i>
							</div>
							<div class="tile-object">
								<div class="name">接送站</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectDinnerPlan">
						<div class="tile bg-yellow" onclick="javascript:window.location='${basePath}logistics/projectDinnerPlan'">
							<div class="tile-body">
								<i class="icon-coffee"></i>
							</div>
							<div class="tile-object">
								<div class="name">用餐</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectGoodsPlan">
						<div class="tile bg-grey" onclick="javascript:window.location='${basePath}logistics/projectGoodsPlan'">
							<div class="corner"></div>
							<div class="tile-body">
								<i class="icon-th"></i>
							</div>
							<div class="tile-object">
								<div class="name">备品</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectHotelPlan">
						<div class="tile bg-blue" onclick="javascript:window.location='${basePath}logistics/projectHotelPlan'">
							<div class="tile-body">
								<i class="icon-hospital"></i>
							</div>
							<div class="tile-object">
								<div class="name">住宿</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="projectInfoStatistics">
						<div class="tile bg-grey" onclick="javascript:window.location='${basePath}projectInfoStatistics/kind'">
							<div class="tile-body">
								<i class="icon-search"></i>
							</div>
							<div class="tile-object">
								<div class="name">项目信息</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="weekReport">
						<div class="tile bg-blue" onclick="javascript:window.location='${basePath}weekReport'">
							<div class="tile-body">
								<i class="icon-tasks"></i>
							</div>
							<div class="tile-object">
								<div class="name">项目周报</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="courseReport">
						<div class="tile bg-green" onclick="javascript:window.location='${basePath}courseReport'">
							<div class="corner"></div>
							<div class="tile-body">
								<i class="icon-trello"></i>
							</div>
							<div class="tile-object">
								<div class="name">课程预报</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="classroomUseStatistics">
						<div class="tile bg-yellow" onclick="javascript:window.location='${basePath}classroomUseStatistics'">
							<div class="tile-body">
								<i class="icon-inbox"></i>
							</div>
							<div class="tile-object">
								<div class="name">教室统计</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="teacherCourseStatistics">
						<div class="tile bg-blue" onclick="javascript:window.location='${basePath}teacherCourseStatistics'">
							<div class="tile-body">
								<i class="icon-user"></i>
							</div>
							<div class="tile-object">
								<div class="name">教师统计</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="accountStatistics">
						<div class="tile bg-red" onclick="javascript:window.location='${basePath}accountStatistics'">
							<div class="tile-body">
								<i class="icon-jpy"></i>
							</div>
							<div class="tile-object">
								<div class="name">财务统计</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="hotelInfoStatistics">
						<div class="tile bg-grey" onclick="javascript:window.location='${basePath}hotelInfoStatistics'">
							<div class="corner"></div>
							<div class="tile-body">
								<i class="icon-moon"></i>
							</div>
							<div class="tile-object">
								<div class="name">住宿统计</div>
							</div>
						</div>
					</d:existMenu>
					
					<d:existMenu requestMapping="goodsStatistics">
						<div class="tile bg-green" onclick="javascript:window.location='${basePath}goodsStatistics'">
							<div class="tile-body">
								<i class="icon-th"></i>
							</div>
							<div class="tile-object">
								<div class="name">备品统计</div>
							</div>
						</div>
					</d:existMenu>
				</div>
				<!-- 
				<div class="tiles" style="clear: both;">
					<ul class="nav nav-tabs">
						<li><a href="javascript:void();" onclick="javascript:window.location='${basePath}framework/sysNewMessage'">动态</a></li>
					</ul>
					<div data-height="290px" data-always-visible="1" data-rail-visible="0">
						<ul class="feeds">
							<c:forEach items="${newMessageList }" var="msg">
								<li>
									<div class="col1">
										<div class="cont">
											<div class="cont-col1">
												<c:choose> 
      												<c:when test="${fn:contains(msg.logType,'PROJECT_')}"> 
	      												<div class="label label-info">                        
															<i class="icon-bullhorn"></i>
														</div>
      												</c:when>
      												<c:otherwise>    
												        <div class="label label-success">                        
															<i class="icon-bell"></i>
														</div>
												    </c:otherwise> 
      											</c:choose>
												
											</div>
											<div class="cont-col2" >
												<div class="desc">
													${msg.logContent }
													<c:if test="${msg.msgNewFlag=='1' }">
														<span class="label label-important label-mini">
														New
														</span>
													</c:if>
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="date">
											${msg.createTimeView }
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				 -->
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	
	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->
	<%@ include file="/WEB-INF/includes/footer.jsp"%>
	<!-- END FOOTER -->

	<script>
		jQuery(document).ready(function() {

			// initiate layout and plugins

			App.init();
				//alert(window.screen.height); 
				//alert( window.screen.width); 

		});
	</script>
</body>

<!-- END BODY -->

</html>