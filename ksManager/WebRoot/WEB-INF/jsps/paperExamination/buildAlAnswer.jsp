<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/includes/common-import.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${systemName}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="${resPath}media/js/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
  </head>
  
  <body>
    案例分析题答案生成器工具. <br>
    <form id="form1">
    	<%
    	for(int i=1;i<=5;i++){
    		for(int j=1;j<=4;j++){
    	%>
    			<input type="checkbox" id="alchk<%=i%><%=j%>" name="alchk" value="true"/>
    	<%  
    			out.println((char)(96+j) + "&nbsp;");
    		}
    		out.println("<br />");
    	}%>
    </form>
  </body>
  <script>
  	var api = frameElement.api, W = api.opener;
  	function ok()
  	{
  		var s = "";
  		var lp = "";
  		for(var i=1;i<=5;i++){
    		for(var j=1;j<=4;j++){
  				if($("#alchk"+i+j).is(':checked')){
  					s += lp + "True";
  				}else{
  					s += lp + "False";
  				}
  				lp = ",";
    		}
  		}
  	    return s;
  	}
  	function init(answer){
  		if(answer == "") return ;
  		var arr = answer.split(',');
  		var n = 0;
  		for(var i=1;i<=5;i++){
    		for(var j=1;j<=4;j++){
    			if(arr[n].toUpperCase() == "TRUE"){
    				$("#alchk"+i+j).attr('checked',true);
    			}else{
    				$("#alchk"+i+j).attr('checked',false);
    			}
    			n ++;
    		}
  		}
  	}
  </script>
</html>
