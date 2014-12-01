<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>搬砖网 - 知名互联网公司内部推荐</title>
    <meta name="description" content="阿里内推,百度内推,内部推荐,阿里巴巴内部推荐">
    <link rel="stylesheet" href="css/index.css"/>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%@include file="header.jsp" %>
<section class="m-main">
    <div class="container">
        <div class="hint">
<!--         已开通阿里巴巴、百度内推直通车，美团、去哪儿等即将上线... -->
        </div>
        <div class="so">
            <input id="keyword" type="text" placeholder="请输入职位关键词" autocomplete="off"/>
            <button id="btn-search">搜 索</button>
        </div>
        <div class="hot">
            <span>热门搜索：</span>
            <ul>
                <li><a class="recommend" href="#">JAVA</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">iOS</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">数据</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">安全</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">搜索</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">算法</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">产品</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">运营</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">视觉</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">交互</a>&nbsp;&nbsp;</li>
                <li><a class="recommend" href="#">前端</a></li>
            </ul>
        </div>
        <div class="fri">
            <h6>已开通大厂内推直通车！点击以下图标进入！</h6>
            <ul>
                <li class="ali"><a href="<%=request.getContextPath()%>/list/c1" target="_blank">阿里巴巴</a></li>
                <li class="baidu"><a href="<%=request.getContextPath()%>/list/c2" target="_blank">百度</a></li>
                <li class="tencent"><a href="<%=request.getContextPath()%>/list/c8" target="_blank">腾讯</a></li>
                <li class="mt"><a href="<%=request.getContextPath()%>/list/c3" target="_blank">美团</a></li>
                <li class="qn"><a href="<%=request.getContextPath()%>/list/c4" target="_blank">去哪儿</a></li>
                <li class="sogou"><a href="<%=request.getContextPath()%>/list/c5" target="_blank">搜狗</a></li>
                <li class="qh"><a href="<%=request.getContextPath()%>/list/c7" target="_blank">360</a></li>
            </ul>
        </div>
    </div>
</section>
<footer class="m-ft">
    <div class="m-contact">
        <div class="container">
            <ul>
                <li>
                    <h6>最新职位</h6>
                    <ul>
                        <c:forEach items="${latests }" var="latest">
                            <li><a href="<%=request.getContextPath()%>/detail/${latest.id}">${latest.name }</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <h6>热门职位</h6>
                    <ul>
                        <c:forEach items="${recommends }" var="recommend">
                            <li><a href="<%=request.getContextPath()%>/detail/${recommend.id}">${recommend.name }</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li>
                    <h6>联系方式</h6>
                    <div>QQ : 273205819</div>
                    <div>churenceo@foxmail.com</div>
                </li>
            </ul>
<!--             <div class="code2d"></div> -->
        </div>
    </div>
    <div class="bei">搬砖网 Copyright © 2014 版权所有 | 京ICP备14047376</div>
</footer>
<script>
  $(function() {
	$("#keyword").focus();
    $("#btn-search").click(function() {
        var keyword = $("#keyword").val();
        window.location.href = "list/c0?keyword=" + encodeURIComponent(keyword);
    });
    $('#keyword').keydown(function(event) {  
        if(event.keyCode == "13") {
            $("#btn-search").click();
            if(window.event) {
               window.event.returnValue = false;  
            } else {
               event.preventDefault();//for firefox
            }
        }
    });
    $('.recommend').click(function() {
        var keyword = $(this).text();
        window.location.href = "list/c0?keyword=" + encodeURIComponent(keyword);
    });
    $('.banner-hover-off').hover(
      function () {
        $(this).addClass("active");
      },
      function () {
        $(this).removeClass("active");
    });
  });
</script>
<script type="text/javascript" src="js/statistic.js"></script>
</body>
</html>
