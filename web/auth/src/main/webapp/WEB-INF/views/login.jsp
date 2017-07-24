<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String st = path + application.getInitParameter("resourceRoot");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>登录 - 数据分析系统</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="<%=st%>/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=st%>/lib/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=st%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=st%>/lib/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=st%>/css/admin-manage/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=st%>/css/admin-manage/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="<%=st%>/css/admin-manage/login.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> </head>
    <!-- END HEAD -->

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="javascript:void(0)" style="font-size: 36px; text-decoration: none; color: white; cursor: default;">
				数据分析系统
			</a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form">
            	<input type="hidden" name="platformType" value="1">
            	<!-- <input type="hidden" name="redirectUrl" value="http://www.baidu.com/"> -->
            	<input type="hidden" name="verifyType" value="pwd">
                <h3 class="form-title">登录</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span></span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">用户名</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="邮箱/手机" name="loginName" /> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="loginPwd" /> </div>
                </div>
                <div class="form-actions">
                    <label class="rememberme mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="remember" value="1" />记住我
                        <span></span>
                    </label>
                    <button type="submit" class="btn green pull-right">登录 </button>
                </div>
                <div class="forget-password">
                    <a href="javascript:;" id="forget-password">忘记密码 ?</a>
                </div>
            </form>
            <!-- END LOGIN FORM -->
            <!-- BEGIN FORGOT PASSWORD FORM -->
            <form class="forget-form" action="index.html" method="post">
                <h3>找回密码</h3>
                <p>请输入邮箱. </p>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-envelope"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" /> </div>
                </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn red btn-outline">返回</button>
                    <button type="submit" class="btn green pull-right">提交</button>
                </div>
            </form>
            <!-- END FORGOT PASSWORD FORM -->
        </div>
        <!-- END LOGIN -->
        <!-- BEGIN COPYRIGHT -->
        <div class="copyright"> 2015-2017 &copy; 禾家科技 </div>
        <!-- END COPYRIGHT -->
        <!--[if lt IE 9]>
			<script src="<%=st%>/lib/respond.min.js"></script>
			<script src="<%=st%>/lib/excanvas.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="<%=st%>/lib/jquery.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/js.cookie.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<%=st%>/lib/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="<%=st%>/js/admin-manage/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="<%=st%>/js/admin-manage/login.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
        <script type="text/javascript">
			var GLOBAL_PATH = '<%=path%>';
			var RESOURCE_ROOT = '<%=st%>';
		</script>
    </body>

</html>