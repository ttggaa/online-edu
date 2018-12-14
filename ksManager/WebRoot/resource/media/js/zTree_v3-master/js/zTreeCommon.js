
var setting_checkbox = {
	check: {
		enable: true
	},data: {
		simpleData: {
			enable: true,
			idKey:"id",  
            pIdkey:"pId",  
            rootPid:"-1"
		}
	},view: {
		fontCss: getFontCss
	}
};
var setting_checkbox_nocase = {
		check: {
			chkboxType : { "Y" : "", "N" : "" },
			enable: true
		},data: {
			simpleData: {
				enable: true
			}
		},view: {
			fontCss: getFontCss
		}
};
var setting_radio = {
	check: {
		enable: true,
		chkStyle:"radio",
		radioType: "all"
	},data: {
		simpleData: {
			enable: true
		}
	},view: {
		fontCss: getFontCss
	}
};

var nodeList = [];
function updateNodes(highlight) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	for( var i=0;i<nodeList.length; i++) {
		nodeList[i].highlight = highlight;
		if(!nodeList[i].isParent){
			var node = zTree.getNodeByParam("id", nodeList[i].pId, null);
			zTree.expandNode(node, true, false, true);
		}
		zTree.expandNode(nodeList[i], true, false, true);
		zTree.updateNode(nodeList[i]);
	}
}
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}

function searchNode(id) {
	if($("#" + id).val() != ""){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var value = $.trim($("#" + id).val());
		updateNodes(false);
		nodeList = zTree.getNodesByParamFuzzy("name", value);
		updateNodes(true);
	}else{
		updateNodes(false);
		$("#"+id).focus();
	}
}

function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}