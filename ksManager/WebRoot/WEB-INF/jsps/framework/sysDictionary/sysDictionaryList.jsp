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
</head>
<body class="page-header-fixed">
	<%@ include file="/WEB-INF/includes/header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<%@ include file="/WEB-INF/includes/leftMenu.jsp"%>
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->        
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							数据字典管理
						</h3>
					</div>
				</div>
						
				<div class="row-fluid">
					<div class="span2">
						<div class="portlet box grey">
							<div class="portlet-title">
								<div class="caption"><i class="icon-comments"></i>数据字典</div>
							</div>
							<div class="portlet-body fuelux">
								<ul class="tree" id="tree_2">
									<li>
										<a href="javascript:void();" onclick="theSubmit('');" 
											data-role="branch" class="tree-toggle" data-toggle="branch" data-value="Bootstrap_Tree">分组编码
										</a>
										<ul class="branch in">
											<c:forEach items="${fieldsList}" var="fieldsList" varStatus="statusTree">
												<li title="${fieldsList.fieldName }">
													<a href="javascript:void();" onclick="theSubmit('${fieldsList.fieldId }');" data-role="leaf" id="nut">${fieldsList.fieldName }</a>
												</li>
											</c:forEach>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="span10">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box light-grey">
							<div class="portlet-title"></div>
							<div class="portlet-body">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<form id="searchForm" class="horizontal-form" action="${basePath }framework/sysDictionary" method="post">
										<div class="row-fluid">
											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="fieldsId">分组KEY</label>
													<div class="controls">
														<input class="m-wrap span10" type="text" placeholder="fieldId..." id="fieldId" name="map['fieldId']" value="${searchParams.map['fieldId'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="fieldsName">分组名称</label>
													<div class="controls">
														<input class="m-wrap span10" type="text" placeholder="fieldsName..." id="fieldsName" name="map['fieldsName']" value="${searchParams.map['fieldsName'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										
										<div class="row-fluid">
											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="code">字典值</label>
													<div class="controls">
														<input class="m-wrap span10" type="text" placeholder="code..." id="code" name="map['code']" value="${searchParams.map['code'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->

											<div class="span5 ">
												<div class="control-group">
													<label class="control-label" for="label">字典描述</label>
													<div class="controls">
														<input class="m-wrap span10" type="text" placeholder="label..." id="label" name="map['label']" value="${searchParams.map['label'] }"/>													
													</div>
												</div>
											</div>
											<!--/span-->
										</div>
										<!--/row-->

										<div style="text-align:center;">
											<button type="submit" class="btn blue"><i class="icon-ok"></i> 查询</button>
											<button type="button" class="btn" onclick="resetForm('searchForm');">重置</button>
										</div>
									</form>
								</div>
								<div class="alert ${MESSAGE_STATE } ${empty MESSAGE ?'hide':'' }">
									<button class="close" data-dismiss="alert"></button>
									<span>${MESSAGE }</span>
								</div>
								<div class="clearfix">
									<div class="btn-group">
										<button class="btn green" onclick="javascript:window.location='${basePath }framework/sysDictionary/create';">
										新增 <i class="icon-plus"></i>
										</button>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover" id="sample_1">
									<thead>
										<tr>
											<th class="hidden-480" style="width:40px;">序号</th>
											<th>分组编码</th> 
											<th class="hidden-480">分组名称</th> 
											<th>值</th> 
											<th>描述</th> 
											<th class="hidden-480">排序</th> 
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list" varStatus="status">
											<tr class="odd gradeX">
												<td class="hidden-480">${status.index+1 }</td>
												<td>${list.fieldId }</td> 
												<td class="hidden-480">${list.fieldName }</td> 
												<td>${list.code }</td> 
												<td>${list.label }</td> 
												<td class="hidden-480">${list.orderIndex }</td> 
												<td >
													<a href="${basePath}framework/sysDictionary/update/${list.id}" class="btn mini purple"><i class="icon-edit"></i> 修改</a>&nbsp;
													<a  href="javascript:void();" onclick="delData('${basePath}framework/sysDictionary/delete/${list.id}');" class="btn mini black"><i class="icon-trash"></i> 删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<p:page url="framework/sysDictionary" cpage="${page.cpage }" perItem="${page.perItem }" totalItem="${page.totalItem }" formId="searchForm"/>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">  
	function theSubmit(fieldId){
		$("#fieldId").val(fieldId);
		$("#searchForm").submit();		
	}
</script>
</html>
