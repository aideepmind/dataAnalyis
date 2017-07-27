<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%
	String path = request.getContextPath();
	String st = path + application.getInitParameter("resourceRoot");
%>
<html>
	<body>
		<!-- BEGIN HEADER MENU -->
        <div class="page-header-menu">
            <div class="container">
                <!-- BEGIN HEADER SEARCH BOX -->
                <form class="search-form" action="page_general_search.html" method="GET">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="query">
                        <span class="input-group-btn">
                            <a href="javascript:;" class="btn submit">
                                <i class="icon-magnifier"></i>
                            </a>
                        </span>
                    </div>
                </form>
                <!-- END HEADER SEARCH BOX -->
                <!-- BEGIN MEGA MENU -->
                <!-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background -->
                <!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover -->
                <div class="hor-menu  ">
                    <ul class="nav navbar-nav">
                        <li class="menu-dropdown classic-menu-dropdown active">
                            <a href="javascript:;">控制台
                                <span class="arrow"></span>
                            </a>
                        </li>
                        <li class="menu-dropdown mega-menu-dropdown  ">
                            <a href="javascript:;">数据管理
                                <span class="arrow"></span>
                            </a>
                            <ul class="dropdown-menu pull-left">
                                <li class=" ">
                                    <a href="layout_mega_menu_light.html" class="nav-link  ">数据采集</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">数据分析</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">数据查询</a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-dropdown classic-menu-dropdown ">
                            <a href="javascript:;">报表管理
                                <span class="arrow"></span>
                            </a>
                            <ul class="dropdown-menu pull-left">
                                <li class=" ">
                                    <a href="layout_mega_menu_light.html" class="nav-link  ">A类报表</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">B类报表</a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-dropdown classic-menu-dropdown ">
                            <a href="javascript:;">系统管理
                                <span class="arrow"></span>
                            </a>
                            <ul class="dropdown-menu pull-left">
                                <li class=" ">
                                    <a href="layout_mega_menu_light.html" class="nav-link  ">用户管理</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">用户组管理</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">角色管理</a>
                                </li>
                                <li class=" ">
                                    <a href="layout_top_bar_light.html" class="nav-link  ">权限管理</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- END MEGA MENU -->
            </div>
        </div>
        <!-- END HEADER MENU -->
	</body>
</html>