<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>搬砖网 ${params.keyword } 职位列表</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/job_style.css" rel="stylesheet">
    <style type="text/css"></style><style id="holderjs-style" type="text/css"></style>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
  </head>
  <body>
	<nav class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
	  <div class="container">
		<div class="navbar-header">
		  <a class="navbar-brand" href="<%=request.getContextPath()%>">搬砖网</a>
		</div>
		<div class="navbar-collapse collapse">
		  <ul class="nav navbar-nav">
<!-- 		    <li class="active"><a href="#">首页</a></li> -->
		    <li><a href="#about">关于我们</a></li>
		  </ul>
		</div>
	  </div>
	</nav>
    <div class="container job-box">
      <div>
        <h3>职位搜索</h3>
        <div>
          <form class="form-inline" role="form">
              <div class="form-group  col-sm-10">
                  <input type="text" class="form-control input-lg" id="job_search" value="${params.keyword }" placeholder="请输入职位关键词">
              </div>  
              <a href="#" class="btn btn-lg btn-default js-search">搜索</a>
          </form>
            <div class="search-tab ">
                <div class="search-li-box layout">
                    <b>工作地点：</b> 
                    <span class="r-span">
                        <a href="javascript:;" data-check-type="dress" rel="all" class="city <c:if test="${empty params.city||params.city=='全部' }">current</c:if>">全部</a>
                        <span id="search-dress">
	                        <a href="javascript:;" rel="杭州" data-check-type="dress" class="city <c:if test="${params.city=='杭州' }">current</c:if>">杭州</a>
	                        <a href="javascript:;" rel="北京" data-check-type="dress" class="city <c:if test="${params.city=='北京' }">current</c:if>">北京</a>
	                        <a href="javascript:;" rel="上海" data-check-type="dress" class="city <c:if test="${params.city=='上海' }">current</c:if>">上海</a>
	                        <a href="javascript:;" rel="深圳" data-check-type="dress" class="city <c:if test="${params.city=='深圳' }">current</c:if>">深圳</a>
                        </span> 
                    </span>
                </div>

                <div class="search-li-box">
                    <b>职位类别：</b>
                    <span class="r-span">
                        <a class="<c:if test="${params.category==0 }">current</c:if> position-child" href="javascript:;" data-child-id="0">全部</a>
                        <c:forEach items="${categorys }" var="category">
	                        <a href="javascript:;" rel="${category.name }" class="position-child <c:if test="${params.category==category.code }">current</c:if>" data-child-id="${category.code }">${category.name }</a>
                        </c:forEach>
                    </span>
                </div>                  
            </div>
        </div>
            <div>
                <table class="table table-hover job-list">
                    <thead>
                        <tr>
                            <th class="col-sm-4"><span>职位名称</span></th>
                            <th class="col-sm-2"><span>职位类别</span></th>
                            <th class="col-sm-2"><span>工作地点</span></th>
                            <th class="col-sm-2"><span>招聘人数</span></th>
                            <th class="col-sm-2"><span>更新时间</span></th>
                        </tr>
                    </thead>
                    <tbody id="J-list-box">
                    <c:forEach items="${jds.list }" var="item">
                        <tr>
	                        <td>
	                           <span>
	                               <a target="_blank" href="detail/${item.id }">${item.name }</a>
	                            </span>
	                         </td>
	                        <td><span>${item.functionType }</span></td>
	                        <td><span>${item.cityId }</span></td>
	                        <td><span>${item.headCount }</span></td>
	                        <td><span><fmt:formatDate value="${item.beginDate }" pattern="yyyy-MM-dd" /></span></td>
                        </tr>
                    </c:forEach>
                </tbody>
                </table>
                <div class="col-sm-offset-5">
                <ul class="pagination">
				<pg:pager items="${jds.count }" url="list" export="currentPageNumber=pageNumber" scope="request">
					<pg:first><li><a href="${pageUrl}&keyword=${params.keyword}" mce_href="${pageUrl}&keyword=${params.keyword}">第一页</a></li></pg:first>  
					<pg:prev><li><a href="${pageUrl}&keyword=${params.keyword}" mce_href="${pageUrl}&keyword=${params.keyword}">&laquo;</a></li></pg:prev>  
					<pg:pages>  
						<c:choose>  
							<c:when test="${pageNumber eq currentPageNumber }">  
				                <li><a><font color="red">${pageNumber }</font></a></li> 
							</c:when>  
							<c:otherwise>  
							    <li><a href="${pageUrl }&keyword=${params.keyword}" mce_href="${pageUrl }&keyword=${params.keyword}">${pageNumber}</a></li>
							</c:otherwise>  
						</c:choose>  
					</pg:pages>  
					<pg:next><li><a href="${pageUrl}&keyword=${params.keyword}" mce_href="${pageUrl}&keyword=${params.keyword}">&raquo;</a></li></pg:next>  
					<pg:last><li><a href="${pageUrl}&keyword=${params.keyword}" mce_href="${pageUrl}&keyword=${params.keyword}">尾页</a></li></pg:last>  
				</pg:pager>  
                </ul>
                </div>
            </div>
        </div>
    </div>
<!--     <div class="footer"> -->
<!--       <p>© DEMO</p> -->
<!--     </div> -->
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function() {
        $(".js-search").click(function() {
            var keyword = $("#job_search").val();
            window.location.href = "list?keyword=" + keyword;
        });
        $('#job_search').keydown(function(event) {
            if(event.keyCode == "13") {
                $(".js-search").click();
                if(window.event) {
                   window.event.returnValue = false;  
                } else {
                   event.preventDefault();//for firefox
                }
            }
        });

        $(".position-child").click(function() {
        	$(".position-child").removeClass("current");
        	$(this).addClass("current");
        	search();
        });

        $(".city").click(function() {
            $(".city").removeClass("current");
            $(this).addClass("current");
            search();
        });

        function search() {
            var city = $(".city.current").text();
            var category = $(".position-child.current").attr("data-child-id");
            var keyword = $("#job_search").val();
            window.location.href = "list?keyword=" + keyword + "&category=" + category + "&city=" + city;
        }
        
    }); 
    </script>
  </body>
</html>