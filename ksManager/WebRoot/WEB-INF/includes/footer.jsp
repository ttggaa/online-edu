<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<form id="canvasParentForm" action="${basePath }common/downloadImg" method="post">
	<input type="hidden" id="commonAvatar" name="commonAvatar"/>
	<input type="hidden" id="commonDownloadFileName" name="commonDownloadFileName"/>
</form>
<div id="responsiveCanvasParent" class="modal hide fade" tabindex="-1" data-width="760" style="top: 4%;">
	<input type="hidden" id="canvasIdHiddenParent"/>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3>截图</h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid" id="canvas_div_responsive">
			
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" onclick="saveAsPngCanvas();" class="btn blue">下载</button>
		<button type="button" data-dismiss="modal" class="btn">关闭</button>
	</div>
</div>
<div class="footer">
	<div class="footer-inner">
		2018 &copy; 大连领航世纪科技发展有限公司.
	</div>
	<div class="footer-tools">
		<span class="go-top">
		<i class="icon-angle-up"></i>
		</span>
	</div>
</div>
