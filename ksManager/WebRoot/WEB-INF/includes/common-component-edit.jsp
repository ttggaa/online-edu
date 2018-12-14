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
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${resPath}media/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/select2_metro.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/clockface.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/bootstrap-wysihtml5.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/timepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/bootstrap-toggle-buttons.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/daterangepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/multi-select-metro.css" />
<link href="${resPath}media/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${resPath}media/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datetimepicker.css" />
<!-- END PAGE LEVEL STYLES -->
<link rel="shortcut icon" href="${resPath}media/image/favicon.ico" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${resPath}media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${resPath}media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>   
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
<script type="text/javascript" src="${resPath}media/js/ckeditor.js"></script>  
<script type="text/javascript" src="${resPath}media/js/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="${resPath}media/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${resPath}media/js/select2.min.js"></script>
<script type="text/javascript" src="${resPath}media/js/wysihtml5-0.3.0.js"></script> 
<script type="text/javascript" src="${resPath}media/js/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="${resPath}media/js/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="${resPath}media/js/jquery.toggle.buttons.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${resPath}media/js/clockface.js"></script>
<script type="text/javascript" src="${resPath}media/js/date.js"></script>
<script type="text/javascript" src="${resPath}media/js/daterangepicker.js"></script> 
<script type="text/javascript" src="${resPath}media/js/bootstrap-colorpicker.js"></script>  
<script type="text/javascript" src="${resPath}media/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/jquery.inputmask.bundle.min.js"></script>   
<script type="text/javascript" src="${resPath}media/js/jquery.input-ip-address-control-1.0.min.js"></script>
<script type="text/javascript" src="${resPath}media/js/jquery.multi-select.js"></script>   

<link rel="stylesheet" type="text/css" href="${resPath}media/css/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/timepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/colorpicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/bootstrap-toggle-buttons.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/daterangepicker.css" />
<link rel="stylesheet" type="text/css" href="${resPath}media/css/datetimepicker.css" />
<link href="${resPath}media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

<script type="text/javascript" src="${resPath}media/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/clockface.js"></script>
<script type="text/javascript" src="${resPath}media/js/date.js"></script>
<script type="text/javascript" src="${resPath}media/js/common.js"></script>
<script type="text/javascript" src="${resPath}media/js/daterangepicker.js"></script> 
<script type="text/javascript" src="${resPath}media/js/bootstrap-colorpicker.js"></script>  



<script src="${resPath}media/js/bootstrap-modal.js" type="text/javascript" ></script>
<script src="${resPath}media/js/bootstrap-modalmanager.js" type="text/javascript" ></script> 
<!--[if lte IE 6]>
<script src="${resPath}media/js/bootstrap-ie.min.js"></script>
<![endif]-->
<!-- END PAGE LEVEL PLUGINS -->
<script src="${resPath}media/js/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery.json-2.4.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/json-class.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery-validate-message.js" type="text/javascript"></script>
<script src="${resPath}media/js/jquery.metadata.js" type="text/javascript"></script>

<script src="${resPath}media/js/zTree_v3-master/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${resPath}media/js/zTree_v3-master/js/zTreeCommon.js" type="text/javascript"></script>
<script src="${resPath}media/js/app.js"></script>
<script src="${resPath}media/js/index.js"></script>
<script src="${resPath}media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${resPath}media/js/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${resPath}media/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${resPath}js/jquery.form.js" type="text/javascript"></script>   
<script src="${resPath}js/common.js"></script>
<script src="${resPath}js/uniteTable.js"></script>

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