<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib uri="/page" prefix="p"%>
<%@ taglib uri="/dict" prefix="dict"%>
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
					</div>
					
				</div>
				<div class="row-fluid profile">
					<div class="span12">
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom tabbable-full-width">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_1_1" data-toggle="tab">概览</a></li>
								<li><a href="#tab_1_2" data-toggle="tab">系统配置</a></li>
								<li><a href="#tab_1_3" data-toggle="tab">开放API接口</a></li>
								<li><a href="#tab_1_4" data-toggle="tab">帮助</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane row-fluid active" id="tab_1_1">
									<ul class="unstyled profile-nav span3">
										<li>
											<img id="backgroundImg" src="${business.background }" alt="" /> 
											<a href="javascript:void();" onclick="javascript:$('#responsiveModifyBack').modal('show');" class="profile-edit">编辑</a>
											<input type="hidden" id="backgroundHid" value="${business.background }"/>
										</li>
									</ul>
									<div class="span9">
										<div class="row-fluid">
											<div class="span8 profile-info">
												<h1>${business.proName }</h1>
												<p>${business.summary }</p>
												<p><a href="http://${business.domain }.exam.linghang-tech.com" target="_blank">考试系统网址-> http://${business.domain }.exam.linghang-tech.com</a></p>
												<ul class="unstyled inline">
													<li style="${business.member == 1?'color:red;':''}"><i class="icon-map-marker"></i> <dict:label field="MEMBER_TYPE" code="${business.member }"/></li>
													<c:if test="${not empty business.authExpire }"><li><i class="icon-calendar"></i> 有效期至 ${business.authExpire }</li></c:if>
													<li><i class="icon-star"></i> 限${business.onlineUser }人同时在线</li>
													<li><i class="icon-btc"></i> ${business.integrate }积分</li>
												</ul>
												<div style="margin-top: 10px;"><a href="${basePath }myAccount/recharge" class="btn black">账户充值 <i class="m-icon-swapright m-icon-white"></i></a></div>
												
											</div>
											<!--end span8-->
											<div class="span4">
												<div class="portlet sale-summary">
													<div class="portlet-title">
														<div class="caption">统计数据</div>
														<div class="tools">
															<a class="reload" href="javascript:;"></a>
														</div>
													</div>
													<ul class="unstyled">
														<li>
															<span class="sale-info">账户余额</span> 
															<span class="sale-num">${business.account}元</span>
														</li>
														<li>
															<span class="sale-info">累计考试人次 <i class="icon-img-up"></i></span> 
															<span class="sale-num">${business.examUserNum }</span>
														</li>
														<li>
															<span class="sale-info">同时在线人数 <i class="icon-img-down"></i></span> 
															<span class="sale-num">${uCount}</span>
														</li>
														<li>
															<span class="sale-info">API接口日调用次数</span> 
															<span class="sale-num">${business.apiDayCount }</span>
														</li>
													</ul>
												</div>
											</div>
											<!--end span4-->
										</div>
										<!--end row-fluid-->
									</div>
									<!--end span9-->
									<div class="row-fluid" style="margin-top: 10px;">
										<div class="span12">
											<!-- BEGIN INLINE NOTIFICATIONS PORTLET-->
											<div class="portlet">
												<div class="portlet-title">
													<div class="caption"><i class="icon-globe"></i>我的考试</div>
													<div class="tools">
														<a href="javascript:;" class="collapse"></a>
														<a href="javascript:;" class="remove"></a>
													</div>
												</div>
												<div class="portlet-body">
													<div class="row-fluid" >
														<div class="span12">
															<ul class="timeline">
															    <c:forEach items="${examingList}" var="exam" varStatus="status">
																	<li class="${exam.examingFlag?'timeline-green':'timeline-grey' }">
																		<div class="timeline-time">
																			<span class="date">${exam.viewDate }</span>
																			<span class="time">${exam.viewTime }</span>
																		</div>
																		<div class="timeline-icon" style="${exam.examingFlag?'background: green;':''}"><i class="icon-time"></i></div>
																		<div class="timeline-body">
																			<h2>${exam.examName }</h2>
																			<div class="timeline-content">
																				参考人数：${exam.examUserCount }人&nbsp;&nbsp;&nbsp;&nbsp; 考试时间：${exam.examBegintime } ~ ${exam.examEndtime }
																			</div>
																			<div class="timeline-footer">
																				<a href="${basePath }examRank/${exam.id}" class="nav-link pull-right">
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
									</div>
									
									<div class="row-fluid" style="margin-top: 10px;">
										<div class="span12">
											<!-- BEGIN INLINE NOTIFICATIONS PORTLET-->
											<div class="portlet">
												<div class="portlet-title">
													<div class="caption"><i class="icon-cloud"></i>平台增值服务</div>
													<div class="tools">
														<a href="javascript:;" class="collapse"></a>
														<a href="javascript:;" class="remove"></a>
													</div>
												</div>
												<div class="portlet-body" style="padding-left: 15px;">
													<div class="row-fluid portfolio-block">
														<div class="span5 portfolio-text">
															<div class="portfolio-text-info">
																<h4>OPEN API数据接口</h4>
																<p>支持远程接口调用，与业务系统实现数据互通。</p>
															</div>
														</div>
														<div class="span5" style="overflow:hidden;">
															<div class="portfolio-info">
																每天允许调用
																<span>10000次</span>
															</div>
															<div class="portfolio-info">
																价格
																<span style="min-width: 120px;">200元</span>
															</div>
														</div>
														<div class="span2 portfolio-btn">
															<a href="javascript:void();" onclick="alert('暂未开通线上支付，请联系客服人员进行购买，谢谢支持!');" class="btn bigicn-only"><span>购买</span></a>                      
														</div>
													</div>
													<div class="row-fluid portfolio-block">
														<div class="span5 portfolio-text">
															<div class="portfolio-text-info">
																<h4>考试运营服务</h4>
																<p>考务管理、题库录入、考试人员导入</p>
															</div>
														</div>
														<div class="span5" style="overflow:hidden;">
															<div class="portfolio-info">
																按场次收费
																<span style="min-width: 120px;">&nbsp;</span>
															</div>
															<div class="portfolio-info">
																价格
																<span>1500元</span>
															</div>
														</div>
														<div class="span2 portfolio-btn">
															<a href="javascript:void();" onclick="cusServiceCall();" class="btn bigicn-only"><span>购买</span></a>                      
														</div>
													</div>
												</div>
											</div>
											<!-- END INLINE NOTIFICATIONS PORTLET-->
										</div>
									</div>
								</div>
								
								<div class="tab-pane row-fluid profile-account" id="tab_1_2">
									<div class="row-fluid">
										<div class="span12">
											<div id="tab_1-1" class="tab-pane active">
												<div style="height: auto;" id="accordion1-1" class="accordion collapse">
													<label class="control-label">系统名称</label>

													<input id="sysName" class="m-wrap span8" type="text" placeholder="考试登录页面显示" value="${business.proName }"/>

													<label class="control-label">系统摘要</label>

													<input id="sysSummary" class="m-wrap span8" type="text" placeholder="考试登录页面显示" value="${business.summary }"/>

													<label class="control-label">手机号码</label>

													<input type="text" id="telephone" placeholder="联系方式" class="m-wrap span8" value="${business.telephone }"/>
													
													<label class="control-label">备注</label>

													<textarea id="businessRemark" class="span8 m-wrap" rows="3">${business.businessRemark }</textarea>

													<label class="control-label">电子邮箱</label>

													<input type="text" id="mail" class="m-wrap span8" value="${business.mail }"/>
													<div class="submit-btn">
														<a href="javascript:void();" class="btn green" onclick="saveSysInfo();">保存</a>
													</div>
												</div>
											</div>
										</div>

									</div>

								</div>
								<!--end tab-pane-->
								
								<div class="tab-pane row-fluid" id="tab_1_3">
									<div class="row-fluid">
										<div class="span12">
											<div class="span3">
												<ul class="ver-inline-menu tabbable margin-bottom-10">
													<li class="active">
														<a data-toggle="tab" href="#tab_1">
														<i class="icon-briefcase"></i> 
														开放API总体介绍
														</a> 
														<span class="after"></span>                                    
													</li>
													<li><a data-toggle="tab" href="#tab_2"><i class="icon-group"></i> 开发接入</a></li>
												</ul>
											</div>
											<div class="span9">
												<div class="tab-content">
													<div id="tab_1" class="tab-pane active">
														<div style="height: auto;" id="accordion1" class="accordion collapse">
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_1" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle collapsed">
																	使用开放API接口都能做什么 ?
																	</a>
																</div>
																<div class="accordion-body collapse in" id="collapse_1">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div id="tab_2" class="tab-pane">
														<div style="height: auto;" id="accordion2" class="accordion collapse">
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_2_1" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
																	Cliche reprehenderit, enim eiusmod high life accusamus enim eiusmod ?
																	</a>
																</div>
																<div class="accordion-body collapse in" id="collapse_2_1">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
																	</div>
																</div>
															</div>
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_2_2" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
																	Pariatur cliche reprehenderit enim eiusmod high life non cupidatat skateboard dolor brunch ?
																	</a>
																</div>
																<div class="accordion-body collapse" id="collapse_2_2">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor.
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="tab-pane row-fluid" id="tab_1_4">
									<div class="row-fluid">
										<div class="span12">
											<div class="span3">
												<ul class="ver-inline-menu tabbable margin-bottom-10">
													<li class="active">
														<a data-toggle="tab" href="#tab_1">
														<i class="icon-briefcase"></i> 
														常见问题
														</a> 
														<span class="after"></span>                                    
													</li>
													<li><a data-toggle="tab" href="#tab_2"><i class="icon-group"></i> Membership</a></li>
												</ul>
											</div>
											<div class="span9">
												<div class="tab-content">
													<div id="tab_1" class="tab-pane active">
														<div style="height: auto;" id="accordion1" class="accordion collapse">
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_1" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle collapsed">
																	Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry ?
																	</a>
																</div>
																<div class="accordion-body collapse in" id="collapse_1">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div id="tab_2" class="tab-pane">
														<div style="height: auto;" id="accordion2" class="accordion collapse">
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_2_1" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
																	Cliche reprehenderit, enim eiusmod high life accusamus enim eiusmod ?
																	</a>
																</div>
																<div class="accordion-body collapse in" id="collapse_2_1">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
																	</div>
																</div>
															</div>
															<div class="accordion-group">
																<div class="accordion-heading">
																	<a href="#collapse_2_2" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle collapsed">
																	Pariatur cliche reprehenderit enim eiusmod high life non cupidatat skateboard dolor brunch ?
																	</a>
																</div>
																<div class="accordion-body collapse" id="collapse_2_2">
																	<div class="accordion-inner">
																		Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor.
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
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
	
	<div id="responsiveModifyBack" class="modal hide fade" tabindex="-1" data-width="760">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h3>配置考试登录页面背景图</h3>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<div class="row-fluid">
					<ul class="unstyled blog-images">
						<li>
							<a href="javascript:void();" onclick="backImgChange('http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg.jpg')">
							<img alt="" src="http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg.jpg">
							</a>
						</li>
						<li>
							<a href="javascript:void();" onclick="backImgChange('http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg01.jpg')">
							<img alt="" src="http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg01.jpg"></a>
						</li>
						<li>
							<a href="javascript:void();" onclick="backImgChange('http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg02.jpg')">
							<img alt="" src="http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg02.jpg"></a>
						</li>
						<li>
							<a href="javascript:void();" onclick="backImgChange('http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg03.jpg')">
							<img alt="" src="http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg03.jpg"></a>
						</li>
						<li>
							<a href="javascript:void();" onclick="backImgChange('http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg04.jpg')">
							<img alt="" src="http://edu-online.qiniu.linghang-tech.com/exam/logoback-template/login_bg04.jpg"></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn">关闭</button>
		</div>
	</div>
	
	<script type="text/javascript">
		function backImgChange(url){
			$('#backgroundHid').val(url);
			$('#backgroundImg').attr('src',url);
			saveBackground();
		}
		function saveSysInfo(){
			$.ajax({
				type:"GET", 
				url:"${basePath}console/saveSysInfo",
				dataType:"json",
				data:{
					sysName: $("#sysName").val(),
					sysSummary: $("#sysSummary").val(),
					telephone: $("#telephone").val(),
					businessRemark: $("#businessRemark").val(),
					mail: $("#mail").val()
				},
				success:function(data){
					var json = eval(data);
					alert("保存成功");
				}
			});
		}
		
		function saveBackground(){
			$.ajax({
				type:"GET", 
				url:"${basePath}console/saveBackground",
				dataType:"json",
				data:{
					background: $("#backgroundHid").val()
				},
				success:function(data){
					$('#responsiveModifyBack').modal('hide');
					var json = eval(data);
					alert("保存成功");
				}
			});
		}
		function cusServiceCall(){
			alert("请联系客户人员，电话：15942473307");
		}
	</script>
</body>
</html>
