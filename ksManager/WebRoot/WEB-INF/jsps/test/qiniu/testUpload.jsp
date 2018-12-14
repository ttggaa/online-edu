<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css"> 
	-->
	<script type="text/javascript" src='https://unpkg.com/qiniu-js@2.5.1/dist/qiniu.min.js'></script>
	<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
  </head>
  
  <body>
    <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">七牛云存储 - JavaScript SDK</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
        <ul class="nav navbar-nav">
          <li class="active">
            <a href="#">上传示例</a>
          </li>
          <li>
            <a href="http://developer.qiniu.com/code/v6/sdk/javascript.html">SDK 文档</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="mainContainer">
    <div class="row" style="margin-top: 20px;">
      <ul class="tip col-md-12 text-mute">
        <li>
          <small>
            JavaScript SDK 基于 h5 file api 开发，可以上传文件至七牛云存储。
          </small>
        </li>
      </ul>
    </div>
    <div id="box">
      <button class="select-button" onclick="upload();">上传</button>
      <input class="file-input" type="file" id="select" />
    </div>
  </div>
  </body>
  <script type="text/javascript">
  	var config = {
	    useCdnDomain: true,
	    region: qiniu.region.z2
	  };
	  var putExtra = {
	    fname: "",
	    params: {},
	    mimeType: null
	  };
  	function upload(){
  		var file = $("#select");
  		var key = "test";
  		var token = "test";
  		var observable = qiniu.upload(file, key, token, putExtra, config);
  		var subscription = observable.subscribe(observable); // 上传开始
  	}
  </script>
</html>
