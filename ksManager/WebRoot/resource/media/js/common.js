;(function() {
	
	/**
	 * 表单相关脚本
	 */
	window.wForm = window.wForm || {};
	
	/**
	 * 点击全选按钮触发的方法
	 */
	function allCheck(){
		var checkBox = $("[name='subcheckBox']");

	    if($("#all").get(0).checked==true){
	       checkBox.attr("checked", "true");
	    }else{
	       checkBox.each(function() {
	       		this.checked = '';
	       });
	    }
	}
	function checkT_F(){
	    var checkBox = $("[name='subcheckBox']");
	    var j=0;
	    for(var i=0;i<checkBox.length;i++){
	        if(checkBox[i].checked==true){    //并且为选中
	            j++;
	        }
	    }
	    if(j==checkBox.length){    //如果复选框选中的数量等于（复选框总和减去全选这个选框的数量）
	        $("#all").get(0).checked=true; //全选被激活
	    }else{
	        $("#all").get(0).checked=false;    //取消全选
	    }
	}

	function getcheckedValue(){
	  	var str="";
	   		 
		$("[name='subcheckBox']").each(function(){
	 		 if($(this).get(0).checked) {
	 		 	if(str=="") str=$(this).val();
	 		  	else str+=","+$(this).val();
	 		 }
		});
		return str;
	}
	
	window.wForm.allCheck = allCheck;
	window.wForm.checkT_F = checkT_F;
	window.wForm.getcheckedValue = getcheckedValue;
	
})()


;(function() {
	
		window.wForm = window.wForm || {};
	
		function createPseudoInput(parent, className, html) {
			$('<div></div>').addClass(className).html(html).appendTo(parent);
		}

function disableInput(key) {
	var record ={}, tempArr = [];

	$('[id*="' + key + '"]').each(function() {
		var input = $(this),
			type = input.attr('type');
		
		switch(type) {
			case 'checkbox':
				var name = input.attr('name'),
					parent = input.parent();

				if(record[name]) {
					return ;
				}
				record[name] = true;
				$('input[name="' + name + '"]').each(function() {
					var that = $(this);

					if(this.checked) {
						tempArr.push(that.next('label').html());
					}
				});
				parent.html('');
				createPseudoInput(parent, 'pseudo-input', tempArr.join(','))
				tempArr = [];
				break;
			case 'radio':
				var parent = input.parent();

				tempArr.push(input.next('label').html());
				parent.html('');
				createPseudoInput(parent, 'pseudo-input', tempArr.join(','))
				tempArr = [];
				break;
			default:
				createPseudoInput(input.parent(), 'pseudo-input', input.val())
					input.remove();	
			}
		});
	}
	
	window.wForm.disableInput = disableInput;
})()
