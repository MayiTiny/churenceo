<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="搬砖网,${params.keyword }内推,${comopany.companyName }内推,">
    <meta name="author" content="">
    <title>搬砖网 ${comopany.companyName }${params.keyword }内推 ${comopany.companyName }${params.keyword }职位列表</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/job_style.css" rel="stylesheet">
    <style type="text/css"></style><style id="holderjs-style" type="text/css"></style>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
  </head>
  <body>
    <%@include file="header.jsp" %>
    <div class="container job-box">
      <div>
        <div>
          <form class="form-inline" role="form">
              <div class="form-group div-search"><b class="text-search">职位搜索</b>
                <input type="text" class="form-control input-search" id="job_search" value="${params.keyword }" placeholder="请输入职位关键词">
                <button class="btn btn-search js-search">搜索</button>
              </div>  
          </form>
            <div class="search-tab ">
                <div class="search-li-box layout">
                    <b>公&emsp;&emsp;司：</b> 
                    <span class="r-span">
                        <a href="javascript:;" data-check-type="dress" rel="all" company-id="0" class="company <c:if test="${empty companys||params.company==0 }">current</c:if>">全部</a>
                        <span id="search-dress">
                            <c:forEach items="${companys }" var="comopany">
		                        <a href="javascript:;" rel="${comopany.companyName }" company-id="${comopany.companyId }" data-check-type="dress" 
		                        class="company <c:if test="${params.company==comopany.companyId }">current</c:if>">${comopany.companyName }
		                        </a>
                            </c:forEach>
                        </span> 
                        <a href="javascript:;" data-check-type="dress" rel="all" company-id="-1" style="color: #888;">（更多知名大厂敬请期待...）</a>
                    </span>
                </div>
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
                            <th class="col-sm-3"><span>职位名称</span></th>
                            <th class="col-sm-1"><span>公司</span></th>
                            <th class="col-sm-1"><span>职位类别</span></th>
                            <th class="col-sm-1"><span>工作地点</span></th>
                            <th class="col-sm-1"><span>招聘人数</span></th>
                            <th class="col-sm-1"><span>更新时间</span></th>
                        </tr>
                    </thead>
                    <tbody id="J-list-box">
                    <c:forEach items="${jds.list }" var="item">
                        <tr>
	                        <td>
	                           <span>
	                               <a target="_blank" href="<%=request.getContextPath()%>/detail/${item.id }">${item.name }</a>
	                            </span>
	                         </td>
	                        <td><span>${item.companyName }</span></td>
	                        <td><span>${item.functionType }</span></td>
	                        <td><span>${item.cityId }</span></td>
	                        <td><span><c:if test="${item.headCount==-1 }">若干</c:if><c:if test="${item.headCount!=-1 }">${item.headCount }</c:if></span></td>
	                        <td><span><fmt:formatDate value="${item.beginDate }" pattern="yyyy-MM-dd" /></span></td>
                        </tr>
                    </c:forEach>
                </tbody>
                </table>
                <div class="col-sm-offset-5">
                <ul class="pagination">
                <c:if test="${jds.count > 0 }">
				  <pg:pager items="${jds.count }" url="${pageContext.request.contextPath }/list/c${params.company }" export="currentPageNumber=pageNumber" scope="request">
					<pg:param name="category" value="${params.category }" />
					<pg:first><li><a href="${pageUrl}&keyword=${params.keyword}&city=${params.city }" mce_href="${pageUrl}&keyword=${params.keyword}&city=${params.city }">第1页</a></li></pg:first>  
					<pg:prev><li><a href="${pageUrl}&keyword=${params.keyword}&city=${params.city }" mce_href="${pageUrl}&keyword=${params.keyword}&city=${params.city }">&laquo;</a></li></pg:prev>  
					<pg:pages>  
						<c:choose>  
							<c:when test="${pageNumber eq currentPageNumber }">  
				                <li><a><font color="red">${pageNumber }</font></a></li> 
							</c:when>  
							<c:otherwise>  
							    <li><a href="${pageUrl }&keyword=${params.keyword}&city=${params.city }" mce_href="${pageUrl }&keyword=${params.keyword}&city=${params.city }">${pageNumber}</a></li>
							</c:otherwise>  
						</c:choose>  
					</pg:pages>  
					<pg:next><li><a href="${pageUrl}&keyword=${params.keyword}&city=${params.city }" mce_href="${pageUrl}&keyword=${params.keyword}&city=${params.city }">&raquo;</a></li></pg:next>  
					<pg:last><li><a href="${pageUrl}&keyword=${params.keyword}&city=${params.city }" mce_href="${pageUrl}&keyword=${params.keyword}&city=${params.city }">第${pageNumber}页</a></li></pg:last>  
				  </pg:pager>
				</c:if>
                </ul>
                </div>
            </div>
        </div>
    </div>
<!--     <div class="footer"> -->
<!--       <p>© DEMO</p> -->
<!--     </div> -->
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function() {
    	$(".banner-hover").removeClass("active");
    	$(".banner-hover-off").addClass("active");
    	
        $(".js-search").click(function() {
            var keyword = $("#job_search").val();
            window.location.href = "${pageContext.request.contextPath }/list/c0?keyword=" + encodeURIComponent(keyword);
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

        $(".company").click(function() {
            var companyId = $(this).attr("company-id");
            $(".company").removeClass("current");
            $(this).addClass("current");
            search();
        });

        function search() {
            var city = $(".city.current").text();
            var category = $(".position-child.current").attr("data-child-id");
            var keyword = $("#job_search").val();
            var company = $(".company.current").attr("company-id");
            window.location.href = "${pageContext.request.contextPath }/list/c"+ company + "?keyword=" + keyword + "&category=" + category + "&city=" + city;
        }
        
    }); 
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/statistic.js"></script>
  </body>
</html>