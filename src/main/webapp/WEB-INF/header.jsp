<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath()%>/css/global.css" rel="stylesheet">
<header class="m-hd">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="header-container">
        <img src="<%=request.getContextPath()%>/img/logo.png" alt="搬砖网"/>
        <nav>
            <ul class="clearfix">
                <li class="banner-hover<c:if test="${bannerSelected=='index' }"> default active</c:if>">
                    <a href="<%=request.getContextPath()%>/">首 页</a>
                </li>
                <li class="banner-hover<c:if test="${bannerSelected!='index' }"> default active</c:if>">
                    <a href="<%=request.getContextPath()%>/list/c0">职位中心</a>
                </li>
            </ul>
        </nav>
        <div class="hd-r">
          <c:if test="${empty sessionScope.nickname }">
	        <span style="color: #666; font-size: 12px;">社交帐号直接登录&nbsp;&nbsp;&nbsp;&nbsp;</span>
	        <div style="display: inline; float: right;">
	          <a href="<%=request.getContextPath()%>/user/login"><img style="margin: 6px;" src="<%=request.getContextPath()%>/img/login/qq.png"/></a>
<%-- 	          <a href="###"><img style="margin: 6px;" src="<%=request.getContextPath()%>/img/login/weibo.png"/></a>     --%>
	        </div>
          </c:if>
          <c:if test="${!empty sessionScope.nickname }">
	        <div style="display: inline; float: right;">
	          <a href="javascript:void(0)"><img style="margin: 6px;max-width:28px;max-height:28px;" src="${sessionScope.avatar }"/></a>
	          <span style="color: #666;">${sessionScope.nickname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	          <a href="javascript:void(0)" class="logout">退出</a>
	        </div>
          </c:if>
        </div>
    </div>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script>
      $(function() {
        $('.banner-hover').hover(
          function () {
       	    $(".banner-hover").removeClass("active");
            $(this).addClass("active");
          },
          function () {
            $(".banner-hover").removeClass("active");
            $(".default").addClass("active");
        });
        $(".logout").click(function() {
        	$.ajax({
        	  type: 'POST',
        	  url: '<%=request.getContextPath()%>/user/logout' ,
       	      success: function(data) {
       	    	window.location.reload();
        	  }
        	});
        });
      });
    </script>
</header>
