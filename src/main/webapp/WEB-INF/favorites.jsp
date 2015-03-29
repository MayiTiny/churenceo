<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>我的收藏职位【搬砖网】知名互联网公司内部推荐平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="搬砖网,内推,内部推荐">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="common/header.jsp" %>
    <link href="<%=request.getContextPath()%>/css/favorites.css" rel="stylesheet">
    <script type="text/javascript">
      if (${empty favorites}) {
        alert("想查看收藏职位，请先登录哦~~");
        location.href = "<%=request.getContextPath()%>/";
      }
    </script>
  </head>
  <body>
    <%@include file="common/nav.jsp" %>
    <input id="contextPath" type="hidden" value="${pageContext.request.contextPath}">
    <div class="container usr-box">
      <div class="left">
        <ul class="nav nav-pills nav-stacked">
<!--           <li role="presentation"><a href="#">投递进度</a></li> -->
<!--           <li role="presentation"><a href="#">投递记录</a></li> -->
          <li role="presentation" class="active"><a href="#">收藏职位</a></li>
          <li role="presentation"><a href="#">个人信息</a></li>
        </ul>
      </div>
      <div class="fav-box">
			<div class="list-group">
              <c:if test="${favorites.count == 0 }"><h4 class="fav-none">您还没有收藏的职位哦~~</h4></c:if>
			  <c:forEach items="${favorites.list }" var="favorite">
				<a href="<%=request.getContextPath()%>/detail/${favorite.jdId }" 
		              class="list-group-item fav-item" target="_blank">
			      <c:if test="${!status }"></c:if>
				  <h4 class="list-group-item-heading">${favorite.jdName }<c:if test="${!favorite.status }"><span class="span-invalid">&nbsp;&nbsp;【已失效，请勿投递】</span></c:if></h4>
				  <p class="list-group-item-text fav-item-text">${favorite.city }</p>
				  <p class="list-group-item-text fav-item-text">${favorite.companyName } • ${favorite.department }</p>
				</a>
			  </c:forEach>
			</div>
		</div>
    </div>
    <%@include file="common/footer.jsp" %>
  </body>
</html>