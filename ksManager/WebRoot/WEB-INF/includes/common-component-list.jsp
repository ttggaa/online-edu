<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${resPath}media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<!--[if lte IE 6]>
  <link rel="stylesheet" href="${resPath}media/css/bootstrap-ie6.min.css" />
<![endif]-->
<!--[if lte IE 7]>
<link rel="stylesheet" href="${resPath}media/css/ie.min.css" />
<![endif]-->
<link href="${resPath}media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${resPath}media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${resPath}media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${resPath}media/css/select2_metro.css" />
<link rel="stylesheet" href="${resPath}media/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datetimepicker.css" />

<!-- END PAGE LEVEL STYLES -->
<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />


<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${resPath}media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
<script src="${resPath}js/jquery.cookie.js" type="text/javascript"></script> 
<script src="${resPath}media/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${resPath}media/js/excanvas.min.js"></script>
<script src="${resPath}media/js/respond.min.js"></script>  
<![endif]-->   
<script src="${resPath}media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery.blockui.min.js" type="text/javascript"></script>  
<script src="${resPath}media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${resPath}media/js/select2.min.js"></script>
<script type="text/javascript" src="${resPath}media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${resPath}media/js/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${resPath}media/js/app.js"></script>
<script src="${resPath}media/js/table-managed.js"></script>   
<script src="${resPath}media/js/My97DatePicker/WdatePicker.js"></script>   
<script src="${resPath}media/js/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/zTree_v3-master/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/zTree_v3-master/js/zTreeCommon.js" type="text/javascript"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${resPath}js/html2canvas.min.js"></script>
<script type="text/javascript" src="${resPath}js/canvas2image.js"></script>

<link rel="stylesheet" type="text/css" href="${resPath}media/css/jquery.tagsinput.css" />
<script type="text/javascript" src="${resPath}media/js/jquery.tagsinput.min.js"></script>
<script src="${resPath}js/common.js"></script>
<!--[if lte IE 6]>
<script src="${resPath}media/js/bootstrap-ie.min.js"></script>
<![endif]-->
<script>
	jQuery(document).ready(function() {       
	   App.init();
	});
	
	function showCanvas(id, fileName){
	    html2canvas(document.getElementById(id)).then(function(canvas) {
	    	$("#canvasIdHiddenParent").val(id);
	    	$("#commonDownloadFileName").val(fileName);
	    	$("#canvas_div_responsive").html("");
	        $("#canvas_div_responsive").append(canvas);
	        $('#responsiveCanvasParent').modal('show');
	    });
	}
	
	function saveAsPngCanvas(){
		var id = $("#canvasIdHiddenParent").val();
	    html2canvas(document.getElementById(id)).then(function(canvas) {
	    	$("#canvas_div_responsive").html("");
	        $("#canvas_div_responsive").append(canvas);
	        var img = canvas.toDataURL();
	        var base=encodeURIComponent(img);//转码
	        $("#commonAvatar").val(base);
	        $("#canvasParentForm").submit();
	        //Canvas2Image.saveAsPNG(canvas,canvas.width, canvas.height);
	    });
	}
	
</script>

<div id="responsiveMsgBox" class="modal hide fade" tabindex="-1" data-width="760">
	<input type="hidden" id="msgbox_callbackFunNameHid"/>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3 id="msgbox_title"></h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid" id="msgbox_content">
			
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" onclick="alertCloseMsgBox();" class="btn blue">执意选择</button>
		<button type="button" data-dismiss="modal" class="btn">选择其它 </button>
	</div>
</div>