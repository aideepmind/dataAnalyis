var Login = function () {
	
	var emailOrMobileRegex = /^((13[0-9])|(15[^4,\D])|(18[0-9])|(17[0,7,8,9])|(147))\d{8}$|^([A-Za-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	$.validator.addMethod('validEmailOrMobile', function(value, element, param) {
		return this.optional(element) || emailOrMobileRegex.test(value);
	});
	
	var handleLogin = function() {
		$('.login-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	loginName: {
	                    required: true,
	                    validEmailOrMobile: true
	                },
	                loginPwd: {
	                    required: true,
	                    minlength: 6
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	            	loginName: {
	                    required: '请输入邮箱/手机',
	                    validEmailOrMobile: '邮箱/手机格式错误'
	                },
	                loginPwd: {
	                    required: '请输入密码',
	                    minlength: '密码格式错误'
	                }
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	loginSubmit(form);
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    $('.login-form').submit();
	                }
	                return false;
	            }
	        });
	}

	var handleForgetPassword = function () {
		$('.forget-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    emailOrMobileRegex: true
	                }
	            },

	            messages: {
	                email: {
	                    required: "请输入邮箱/手机"
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	            	
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.form-group').addClass('has-error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	forgetPasswordSubmit(form);
	            }
	        });

	        $('.forget-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.forget-form').validate().form()) {
	                    $('.forget-form').submit();
	                }
	                return false;
	            }
	        });

	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });

	}
	
	var loginSubmit = function(form) {
		var form = $(form);
		$.ajax({
			type: 'POST', 
			dataType: "json", 
			url: GLOBAL_PATH + '/login/doLogin/',
			data: form.serialize(),
			async: false,
			success: function (rp) {
				if (rp && rp.success) {
					window.location.href = rp.message.redirectUrl;
				} else if (rp && !rp.success) {
					form.find('.alert-danger span').text(rp.failReason)
					form.find('.alert-danger').show();
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				
			}
		});
	}
	
	var forgetPasswordSubmit = function(form) {
		
	}
    
    return {
        //main function to initiate the module
        init: function () {
        	
            handleLogin();
            handleForgetPassword();

            // init background slide images
		    $.backstretch([
		        RESOURCE_ROOT + "/img/admin-manage/1.jpg",
		        RESOURCE_ROOT + "/img/admin-manage/2.jpg",
		        RESOURCE_ROOT + "/img/admin-manage/3.jpg",
		        RESOURCE_ROOT + "/img/admin-manage/4.jpg"
		        ], {
		          fade: 1000,
		          duration: 8000
		    	}
        	);
        }
    };

}();

jQuery(document).ready(function() {
    Login.init();
});