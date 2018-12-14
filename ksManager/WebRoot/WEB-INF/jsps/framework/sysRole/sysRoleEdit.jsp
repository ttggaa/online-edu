<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>
<%@ taglib prefix="data" uri="/data"%>
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
	<%@ include file="/WEB-INF/includes/common-component-edit.jsp"%>
</head>
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<div class="page-container">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							角色管理
							<small>${action=='create'?'新增角色信息':'修改角色信息'}</small>
						</h3>
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
								<form id="inputForm" class="form-horizontal" action="${basePath}framework/sysRole/${action}" method="post" >
									<input type="hidden" name="id" value="${sysRole.id }"/>
									<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
										<button class="close" data-dismiss="alert"></button>
										<span>${MESSAGE }</span>
									</div>
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       角色名称<span class="required">*</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="roleName" class="span6 m-wrap required" type="text" value="${sysRole.roleName}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       角色代码<span class="required">* 注：角色代码请慎重修改！</span> 
									   </label> 
									   <div class="controls"> 
									       <input name="roleCode" class="span6 m-wrap required" type="text" value="${sysRole.roleCode}"> 
									   </div> 
									</div> 
									<div class="control-group"> 
									   <label class="control-label"> 
									       备注
									   </label> 
									   <div class="controls"> 
									       <textarea name="remark" class="span6 m-wrap" rows="3">${sysRole.remark}</textarea>
									   </div> 
									</div> 
									
									<div class="control-group"> 
									   <label class="control-label"> 
									       角色授权
									   </label> 
									   <div class="controls"> 
									       <table class="table table-striped table-bordered table-hover" id="sample_1">
												<thead>
													<tr>
														<th style="width:40px;">序号</th>
														<th>菜单名称</th> 
														<th>菜单ID</th>
														<th>菜单权限</th> 
														<th>功能权限</th> 
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${menuList}" var="list" varStatus="status">
														<tr class="odd gradeX">
															<td>${status.index+1 }</td>
															<td><i class="icon-list-alt"></i>&nbsp;${list.name }</td>
															<td>${list.id }</td>
															<td>
																<div class="danger-toggle-button">
																	<input type="checkbox" class="toggle" ${list.menuAuth==1?'checked=checked':'' } name="menuAuth" value="${list.id }"/>
																</div>
															</td>
															<td>
																<c:forEach items="${list.menuFuncList}" var="menuFunc">
																	<input type="checkbox" name="menuFunc" value="${menuFunc.funcCode }" ${menuFunc.authFlag==1?'checked=checked':'' }/>${menuFunc.funcName }
																</c:forEach>
															</td>
														</tr>
														<c:forEach items="${list.childList}" var="list2" varStatus="status2">
															<tr class="odd gradeX">
																<td>${status.index+1 }.${status2.index+1 }</td>
																<td>&nbsp;&nbsp;<i class="icon-chevron-right"></i> ${list2.name }</td>
																<td>${list2.id }</td>
																<td>
																	<div class="danger-toggle-button">
																		<input type="checkbox" class="toggle" ${list2.menuAuth==1?'checked=checked':'' } name="menuAuth" value="${list2.id }"/>
																	</div>
																</td>
																<td>
																	<c:forEach items="${list2.menuFuncList}" var="menuFunc2">
																		<input type="checkbox" name="menuFunc" value="${menuFunc2.funcCode }" ${menuFunc2.authFlag==1?'checked=checked':'' }/>${menuFunc2.funcName }
																	</c:forEach>
																</td>
															</tr>
															<!-- 三级 -->
															<c:forEach items="${list2.childList}" var="list3" varStatus="status3">
																<tr class="odd gradeX">
																	<td>${status.index+1 }.${status2.index+1 }.${status3.index+1 }</td>
																	<td>&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-chevron-right"></i><i class="icon-chevron-right"></i> ${list3.name }</td>
																	<td>${list3.id }</td>
																	<td>
																		<div class="danger-toggle-button">
																			<input type="checkbox" class="toggle" ${list3.menuAuth==1?'checked=checked':'' } name="menuAuth" value="${list3.id }"/>
																		</div>
																	</td>
																	<td>
																		<c:forEach items="${list3.menuFuncList}" var="menuFunc3">
																			<input type="checkbox" name="menuFunc" value="${menuFunc3.funcCode }" ${menuFunc3.authFlag==1?'checked=checked':'' }/>${menuFunc3.funcName }
																		</c:forEach>
																	</td>
																</tr>
																
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</tbody>
											</table>
									   </div> 
									</div> 
									
									<div class="form-actions clearfix">
										<button class="btn blue" type="submit"><i class="icon-ok"></i>完成</button>
										&nbsp;&nbsp;
										<a class="btn" onclick="cancle();">取消</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${resPath}media/js/app.js"></script>   
	<script>
		jQuery(document).ready(function() { 		   
		   $("#inputForm").validate();	
		   App.init();	
		   
		   $('.danger-toggle-button').toggleButtons({
	            style: {
	                enabled: "info",
	                disabled: ""
	            }
	       });	
		});
		function cancle(){
			window.location = "${basePath}framework/sysRole";
		}
	</script>
</body>
</html>
