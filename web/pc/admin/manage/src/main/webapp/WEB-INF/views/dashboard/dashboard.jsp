<%@page import="com.hejia.dataAnalysis.module.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String st = path + application.getInitParameter("resourceRoot");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
	<head>
		
	</head>
	<body class="page-container-bg-solid"><!-- page-header-fixed  -->
		<!-- BEGIN HEADER -->
		<div class="page-header">
			<jsp:include page="../frame_header_top.jsp"></jsp:include>
			<jsp:include page="../frame_header_menu.jsp"></jsp:include>
		</div>
		<!-- END HEADER -->
		<!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <!-- BEGIN PAGE HEAD-->
                <div class="page-head">
                    <div class="container">
                        <!-- BEGIN PAGE TITLE -->
                        <div class="page-title">
                            <h1>控制台
                                <small>控制台 & 统计报表</small>
                            </h1>
                        </div>
                        <!-- END PAGE TITLE -->
                        <!-- BEGIN PAGE TOOLBAR -->
                        <jsp:include page="../frame_page_toolbar.jsp"></jsp:include>
                        <!-- END PAGE TOOLBAR -->  
                    </div>
                </div>
                <!-- END PAGE HEAD-->
                <!-- BEGIN PAGE CONTENT BODY -->
                <div class="page-content">
                    <div class="container">
                        <!-- BEGIN PAGE BREADCRUMBS -->
                        <ul class="page-breadcrumb breadcrumb">
                            <li>
                                <a href="/dashboard/">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>控制台</span>
                            </li>
                        </ul>
                        <!-- END PAGE BREADCRUMBS -->
                        <!-- BEGIN PAGE CONTENT INNER -->
                        <div class="page-content-inner">
                            <div class="row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="portlet light ">
                                        <div class="portlet-title">
                                            <div class="caption ">
                                                <span class="caption-subject font-dark bold uppercase">大数据需求分析报表</span>
                                                <span class="caption-helper">按行业划分</span>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div id="dashboard_chart_1" class="CSSAnimationChart"  style="overflow: hidden;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="portlet light ">
                                        <div class="portlet-title">
                                            <div class="caption ">
                                                <span class="caption-subject font-dark bold uppercase">大数据需求分析报表</span>
                                                <span class="caption-helper">按公司规模划分</span>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div id="dashboard_chart_2" class="CSSAnimationChart"  style="overflow: hidden;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="page-content-inner">
                            <div class="row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="portlet light ">
                                        <div class="portlet-title">
                                            <div class="caption ">
                                                <span class="caption-subject font-dark bold uppercase">大数据需求分析报表</span>
                                                <span class="caption-helper">按行业划分</span>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div id="dashboard_chart_3" class="CSSAnimationChart"  style="overflow: hidden;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="portlet light ">
                                        <div class="portlet-title">
                                            <div class="caption ">
                                                <span class="caption-subject font-dark bold uppercase">大数据需求分析报表</span>
                                                <span class="caption-helper">按公司规模划分</span>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div id="dashboard_chart_4" class="CSSAnimationChart"  style="overflow: hidden;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END PAGE CONTENT INNER -->
                    </div>
                </div>
                <!-- END PAGE CONTENT BODY -->
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
		<!-- BEGIN INNER FOOTER -->
		<jsp:include page="../frame_footer.jsp"></jsp:include>
		<!-- END INNER FOOTER -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <!-- END PAGE LEVEL PLUGINS -->
        <script type="text/javascript">
			require.config({
				baseUrl:'<%=st%>',
				urlArgs: 'v=0.0.1',
				paths: {
					'common': 'js/common',
					'dashboard': 'js/dashboard',
			        'theme': 'lib/echarts/theme',
			        'map': 'lib/echarts/map'
				},
				packages: [{
		               main: 'echarts',
		               location: '<%=st%>/lib/echarts/src',
		               name: 'echarts'
		        },{
		            main: 'zrender',
		            location: '<%=st%>/lib/zrender/src',
		            name: 'zrender'
		        }]
			});
			require(['dashboard/main']);
		</script>
	</body>
</html>