function delData(url){
	if(confirm("您确认要进行删除操作吗？")){
		window.location=url;
	}
}

function delDataForm(actionUrl, formId){
	if(confirm("您确认要进行删除操作吗？")){
		var tempAction = $("#" + formId).attr("action");
		$("#" + formId).attr('action',actionUrl); 
		$("#" + formId).submit();	
		$("#" + formId).attr('action',tempAction); 
	}
}

function cancelData(url){
	if(confirm("您确认要进行取消操作吗？")){
		window.location=url;
	}
}

function resetForm(formId){
	var Form1 = document.getElementById(formId);
	Form1.reset();
	var obj = null;
    for (var i = 0; i <= Form1.elements.length - 1; i++) {
        obj = Form1.elements[i];
        if (obj.tagName == "INPUT" && obj.type == "text") {
            obj.setAttribute("value", "");
        }
        if (obj.tagName == "INPUT" && obj.type == "checkbox") {
            obj.setAttribute("checked", false);
        }
        if (obj.tagName == "SELECT") {
            obj.options[0].selected = true;
        }
    }
    $(".chosen").val("");
    $(".chosen").trigger("liszt:updated");
}

function initViewColConf(cookieKey){
	$.cookie(cookieKey, '', { expires: 0, path: '/' }); 
	$('#responsive').modal('hide');
	alert('初使化完成，请手动刷新当前页面！');
}

function initExpColConf(cookieKey){
	$.cookie("exp_" + cookieKey, '', { expires: 0, path: '/' }); 
	$('#responsiveExport').modal('hide');
	alert('初使化完成，请手动刷新当前页面！');
}

function viewColChange(cookieKey){
	var temp = "";
	var lp = "";
	$("[name='viewColsCheckbox']").each(function(){
		var colCode = $(this).val();
    	if($(this).is(":checked")){
    		$("#vc_th_" + colCode).show();
    		temp += lp + colCode;
    		lp = "#";
    		for(var i=1;i<=10;i++){
    			$("#vc_td_" + colCode+"_" + i).show();
    		}    		
    	}else{
    		$("#vc_th_" + colCode).hide();
    		for(var i=1;i<=10;i++){
    			$("#vc_td_" + colCode+"_" + i).hide();
    		} 
    	}
    });
	$.cookie(cookieKey, temp, { expires: 7, path: '/' }); 
	$('#responsive').modal('hide');
}

function expColChange(cookieKey, expActionUrl){
	var temp = "";
	var lp = "";
	$("[name='expColsCheckbox']").each(function(){
		var colCode = $(this).val();
    	if($(this).is(":checked")){
    		temp += lp + colCode;
    		lp = "#";
    	}
    });
	$.cookie("exp_" + cookieKey, temp, { expires: 7, path: '/' }); 
	window.location = expActionUrl;
	$('#responsiveExport').modal('hide');
}

function expColChangeForm(cookieKey, expActionUrl, formId){
	var temp = "";
	var lp = "";
	$("[name='expColsCheckbox']").each(function(){
		var colCode = $(this).val();
    	if($(this).is(":checked")){
    		temp += lp + colCode;
    		lp = "#";
    	}
    });
	$.cookie("exp_" + cookieKey, temp, { expires: 7, path: '/' }); 
	$("#" + formId).attr('action',expActionUrl); 
	$("#" + formId).submit();
	$('#responsiveExport').modal('hide');
}

function submitForm(actionUrl, formId){
	var tempAction = $("#" + formId).attr("action");
	$("#" + formId).attr('action',actionUrl); 
	$("#" + formId).submit();	
	$("#" + formId).attr('action',tempAction); 
}

function calculation(ysId,jsId,index,itemCode){
	var ys = document.getElementById(ysId).value;
	var js = document.getElementById(jsId).value;
	ys = Number(ys);
	js = Number(js);
	var jin = ys-js;
	$("#addDecMoneyVieSpan_"+index).text(jin);
	var i = 0;
	var ysHS = 0;
	var jsHS = 0;
	ysHS = Number(ysHS);
	jsHS = Number(jsHS);
	var ysHZ = 0;
	var jsHZ = 0;
	ysHZ = Number(ysHZ);
	jsHZ = Number(jsHZ);
	for (i = 0; i <=coursePlanLen - 1; i++) {  
		var yId = "ys"+i;
		var jId = "js"+i;
		var itemCodeTemp = $("#itemCode_" + i).val();
		if(itemCodeTemp == '2013' && itemCodeTemp != itemCode){//计算学院管理费
			var yG = ysHS * 0.2;
			yG = Number(yG).toFixed(2);
			var jG = jsHS * 0.2;
			jG = Number(jG).toFixed(2);//小数点后两位
			var conG = Number(yG)-Number(jG);
			var fid = i;//coursePlanLen - 1;
			$("#ys"+fid).val(yG);
			$("#js"+fid).val(jG);
			$("#addDecMoneyVieSpan_"+fid).text(conG);
		}
		ys = Number(document.getElementById(yId).value);
	    	js = Number(document.getElementById(jId).value);
	    	if(i<4){
	    		ysHS = ysHS + ys;
	    		jsHS = jsHS + js;
	    	}else{
	    		ysHZ = ysHZ + ys;
	    		jsHZ = jsHZ + js;
	    	};
	};
	$("#ysHS").text(ysHS);
	$("#jsHS").text(jsHS);
	
	$("#ysHZ").text(ysHZ);
	$("#jsHZ").text(jsHZ);
	
	$("#countHS").text(ysHS-jsHS);
	$("#countHZ").text(ysHZ-jsHZ);
	
	var ysL = ysHS-ysHZ;
	var jsL = jsHS-jsHZ;
	$("#ysL").text(ysL);
	$("#jsL").text(jsL);
	
	var ysV = 0;
	if(ysHS != 0){//合计非空时进行
		ysV = Number(ysL)/ysHS * 100;
		ysV = Number(ysV).toFixed(2);
	}
	var jsV = 0;
	if(jsHS != 0){
		jsV = Number(jsL)/jsHS * 100;
		jsV = Number(jsV).toFixed(2);
	}
	$("#jsV").text(jsV+"%");
	$("#ysV").text(ysV+"%");
	
}	

function calculationJ(jsId,index,itemCode){
	var js = document.getElementById(jsId).value;
	var i = 0;
	var jsHS = 0;
	jsHS = Number(jsHS);
	var jsHZ = 0;
	jsHZ = Number(jsHZ);
	for (i = 0; i <=coursePlanLen - 1; i++) {  
		var jId = "js"+i;
		var itemCodeTemp = $("#itemCode_" + i).val();
		if(itemCodeTemp == '2013' && itemCodeTemp != itemCode){//计算学院管理费
			var jG = jsHS * 0.2;
			jG = Number(jG).toFixed(2);
			var fid = i;
			$("#js"+fid).val(jG);
		};
	    js = Number(document.getElementById(jId).value);
	    	if(i<4){
	    		jsHS = jsHS + js;
	    	}else{
	    		jsHZ = jsHZ + js;
	    	};
	};
	$("#jsHS").text(jsHS);
	$("#jsHZ").text(jsHZ);
	
	
	var jsL = jsHS-jsHZ;
	$("#jsL").text(jsL);
	
	var jsV = 0;
	if(jsHS != 0){
		jsV = Number(jsL)/jsHS * 100;
		jsV = Number(jsV).toFixed(2);
	}
	$("#jsV").text(jsV+"%");
	
}	

//判断是否为移动端
function isMobile(){
	if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
	    return true;
	} else {
		return false;
	}
}

function alertMsgbox(title,content, msgbox_callbackFunName){
	$("#msgbox_title").html(title);
	$("#msgbox_content").html(content);
	$("#msgbox_callbackFunNameHid").val(msgbox_callbackFunName);
	$('#responsiveMsgBox').modal('show');
}

function alertCloseMsgBox(){
	var msgbox_callbackFunName = $("#msgbox_callbackFunNameHid").val();
	$("#msgbox_title").html("");
	$("#msgbox_content").html("");
	$("#msgbox_callbackFunNameHid").val("");
	$('#responsiveMsgBox').modal('hide');
	if(msgbox_callbackFunName != "") eval(msgbox_callbackFunName);
}

function insertText(obj,str) {
	if (document.selection) {
		var sel = document.selection.createRange();
	    sel.text = str;
	} else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
		var startPos = obj.selectionStart,
		endPos = obj.selectionEnd,
		cursorPos = startPos,
		tmpStr = obj.value;
		obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
	    cursorPos += str.length;
	    obj.selectionStart = obj.selectionEnd = cursorPos;
	} else {
	    obj.value += str;
	}
}
