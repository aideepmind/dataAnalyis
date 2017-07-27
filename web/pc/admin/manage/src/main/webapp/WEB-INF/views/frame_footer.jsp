<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String st = path + application.getInitParameter("resourceRoot");
%>
<html>
	<body>
		<div class="page-footer">
            <div class="container">2015-2017 &copy; 禾家科技
                <a href="javascript:void(0)" title="" target="_blank"></a>
            </div>
        </div>
        <div class="scroll-to-top">
            <i class="icon-arrow-up"></i>
        </div>
        <!-- BEGIN CORE PLUGINS -->
        <script src="<%=st%>/lib/jquery.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/esl.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <%-- <script src="<%=st%>/lib/template.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/js.cookie.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="<%=st%>/lib/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script> --%>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="<%=st%>/js/common/app.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="<%=st%>/layouts/layout3/scripts/layout.min.js" type="text/javascript"></script>
        <script src="<%=st%>/layouts/layout3/scripts/demo.min.js" type="text/javascript"></script>
        <script src="<%=st%>/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
	</body>
</html>