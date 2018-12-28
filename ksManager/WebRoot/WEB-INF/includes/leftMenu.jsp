<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String requestUrl = (String)request.getAttribute("requestUrl");
%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->        
	<ul class="page-sidebar-menu">
		<li>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<div class="sidebar-toggler hidden-phone"></div>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		</li>
		<li class="start ">

			<a href="${basePath }console">

			<i class="icon-home"></i> 

			<span class="title">控制台</span>

			</a>

		</li>
		<c:forEach items="${sessionScope.LOGIN_USER.menuList}" var="menuList" varStatus="status">
			<li class="${sessionScope.menuRootId==menuList.id?'open active':'' }">
				<a href="javascript:;">
					<i class="${menuList.icon }"></i> 
					<span class="title">${menuList.name }</span>
					<span class="arrow "></span>
				</a>
				<c:if test="${not empty menuList.childList}">
					<ul class="sub-menu">
						<c:forEach items="${menuList.childList}" var="childList" varStatus="status2">
							<li class="${sessionScope.menuId==childList.id?'open active':'' }">
								<a href="${basePath}${childList.linkUrl }?menuRootId=${menuList.id }&menuId=${childList.id }">
									<i class="${childList.icon }"></i>
									${childList.name }
								</a>
								<c:if test="${not empty childList.childList}">
									<ul class="sub-menu">
										<c:forEach items="${childList.childList}" var="childList2" varStatus="status3">
											<li class="${sessionScope.menuId==childList.id && sessionScope.subMenuId == childList2.id?'open active':'' }">
												<a href="${basePath}${childList2.linkUrl }?menuRootId=${menuList.id }&menuId=${childList.id }&subMenuId=${childList2.id}">
													${childList2.name }
												</a>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
		
	</ul>
	<!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->