var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
           $('#inputForm').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	loginName: {
	                    required: true
	                },
	                passwd: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	            	loginName: {
	                    required: " 账号不能为空."
	                },
	                passwd: {
	                    required: " 密码不能为空."
	                }
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            }
	        });
        }

    };

}();