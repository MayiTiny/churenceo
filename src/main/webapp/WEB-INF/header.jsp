<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath()%>/css/global.css" rel="stylesheet">
<header class="m-hd">
    <div class="header-container">
        <img src="<%=request.getContextPath()%>/img/logo.png" alt="搬砖网"/>
        <nav>
            <ul class="clearfix">
                <li class="banner-hover active"><a href="<%=request.getContextPath()%>/">首 页</a></li>
                <li class="banner-hover-off"><a href="<%=request.getContextPath()%>/list/c0">职位中心</a></li>
            </ul>
        </nav>
        <div class="hd-r">
<!--             <a href="###">登录</a> | <a href="###">注册</a> -->
        </div>
    </div>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script>
	  $(function() {
	    $('.banner-hover-off').hover(
	      function () {
	        $(this).addClass("active");
	      },
	      function () {
	        $(this).removeClass("active");
	    });
	  });
	</script>
</header>