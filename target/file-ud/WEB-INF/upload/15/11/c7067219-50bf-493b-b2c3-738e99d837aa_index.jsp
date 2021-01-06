        <%@ page contentType="text/html; charset=UTF-8" %>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
                <c:set var="ctx" value="${ pageContext.request.contextPath }" />
                <!DOCTYPE html>
                <html lang="zh-cn">
                <head>
                <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
                <meta http-equiv="content-language" content="zh-CN">
                <meta name="renderer" content="webkit">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <meta name="keywords" content="">
                <meta name="description" content="">
                <title>欧冶金融-融资平台-<sitemesh:title /></title>
                <link rel="shortcut icon" type="image/x-icon" href="${ctx}/resources/img/favicon.ico">
                <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.min.css">
                <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/iconfont.css">
                <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common.css">
                <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/main.css">
                <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/crmanage/crmanage.css">
                <link rel="stylesheet" type="text/css" href="${ctx}/components/res/css/neweit-menu.css" />
                </head>
                <body>
                <%@ include file="/WEB-INF/views/layout/topnav.jsp" %>
                <div class="top-bar navbar-fixed-top">
                <a class="logo ease" href="#">Ouyeel Finance</a>
                <div class="top-nav right">
                <div class="top-nav-avatar mar-right-10">
                <a href="#" onclick="dynaLoadPage(&quot;http://uathx.ouyeelf.com/JDP-UIC-Admin/admin/sec/myaccount&quot;);" class="top-avatar-box ease"><img src="http://testhx.ouyeelf.com/JDP-UIC-Admin/static/images/avatar1.jpg" class="user-avatar"><span>欢迎您,融资管理员<i class="iconfont font14"></i></span></a><div class="top-dropdown dropdown-avatar"><div class="top-dropdown-box"><ul><li><a href="#" onclick="dynaLoadPage(&quot;http://uathx.ouyeelf.com/JDP-UIC-Admin/admin/profile/alterAdminPasswordPage&quot;);"><i class="iconfont middle"></i>修改密码</a></li><li><a href="http://uathx.ouyeelf.com/JDP-UIC-Admin/admin/logOutAll"><i class="iconfont middle"></i>退出</a></li><div class="clear"></div></ul><div class="clear"></div></div></div></div><ul class="top-nav-menu"><li class="top-nav-box"><a href="http://uathx.ouyeelf.com/JDP-UIC-Admin/admin/desktop" class="home ease active"><i class="iconfont font12 mar-right-5"></i>首页</a></li></ul></div></div>
                <div class="new-content new-open"><!--new-close 菜单关闭-->
                <div class="new-sidebar">
                <div class="new-menu">
                <div class="new-sidebar-inner">
                <div class=" iconfont new-sidebar-btn" data-left="&#xe654;" data-right="&#xe655;">&#xe655;</div>
                <div class="new-sidebar-nav" id="new-sidebar-nav">

                </div>
                </div>
                </div>
                </div>
                <!--内容-->
                <div class="new-main product-menu-open">
                <div class="product-menu open" id="product-menu">
                </div>
                <!--子菜单关闭-->
                <div class="product-menu-collapse">
                <div class="product-menu-collapse-inner">
                <div class="product-menu-collapse-bg "></div>
                <div class="product-menu-collapse-box">
                <span class=" iconfont icon-collapse-left">&#xe61b;</span>
                <span class=" iconfont icon-collapse-right">&#xe61b;</span>
                </div>
                </div>
                </div>
                <div class="product-body">
                </div>
                </div>
                </div>
                <input type="hidden" id="context" value="${ctx}">
                <script type="text/javascript" src="${ctx}/components/res/js/core/jquery-1.7.2.min.js"></script>
                <script type="text/javascript" src="${ctx}/components/res/js/common/fx.common.js"></script>

                <script type="text/javascript" src="${ctx}/components/res/js/page/fx.homepageMenu.js"></script>
                <script>
                M.ui.homepageMenu.init({index:1,page:1});
                </script>
                </body>
                </html>