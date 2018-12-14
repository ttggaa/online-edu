<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/data" prefix="d"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";

pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("resPath", basePath+"resource/");
pageContext.setAttribute("ztreeDiyIconPath", basePath+"resource/media/js/zTree_v3-master/css/zTreeStyle/img/diy/");
pageContext.setAttribute("systemName", "领航考务管理系统");
pageContext.setAttribute("domainUrl", "http://");
pageContext.setAttribute("meta-keywords", "管理系统");
pageContext.setAttribute("meta-description", "管理系统");
%>