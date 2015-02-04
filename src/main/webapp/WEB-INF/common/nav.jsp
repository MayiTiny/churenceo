<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>

<header class="m-hd">
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
              <ul class="nav navbar-nav" style="width: 100%;">
                <li style="width: 100%;">
		          <a href="javascript:void(0)" data-toggle="dropdown" style="padding: 10px 15px;">
		            <img style="margin: -5px 6px;max-width:28px;max-height:28px;" src="${sessionScope.avatar }"/>
		            <span style="color: #666;">${sessionScope.nickname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		            <b class="caret"></b>
	              </a>
	              <ul class="dropdown-menu" style="width: 100%;">
	                <li style="width: 100%;"><a class="logout" href="javascript:void(0)">退出</a></li>
	              </ul>
                </li>
              </ul>
	        </div>
          </c:if>
        </div>
    </div>
</header>
