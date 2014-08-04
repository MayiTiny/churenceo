<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>PROJECT@YUFEI</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/job_style.css" rel="stylesheet">
    <style type="text/css"></style><style id="holderjs-style" type="text/css"></style>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
  </head>
  <body>
	<nav class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
	  <div class="container">
		<div class="navbar-header">
		  <a class="navbar-brand" href="#">LOGO</a>
		</div>
		<div class="navbar-collapse collapse">
		  <ul class="nav navbar-nav">
		    <li class="active"><a href="#">首页</a></li>
		    <li><a href="#social">社会招聘</a></li>
		    <li><a href="#school">校园招聘</a></li>
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
                  <input type="text" class="form-control input-lg" id="job_search" placeholder="请输入职位关键词">
              </div>  
              <a href="#" class="btn btn-lg btn-default">搜索</a>
          </form>
            <div class="search-tab ">
                <div class="search-li-box layout">
                    <b>工作地点：</b> 
                    <span class="r-span"> <a href="javascript:;" data-check-type="dress" rel="all" style="display:none">全部</a><span id="search-dress"><a data-check-type="dress" rel="杭州" href="javascript:;">杭州</a><a href="javascript:;" rel="北京" data-check-type="dress">北京</a><a href="javascript:;" rel="上海" data-check-type="dress">上海</a><a href="javascript:;" rel="深圳" data-check-type="dress">深圳</a></span> <a class="check-more-dress" href="javascript:;" data-current="4">更多&gt;&gt;</a>
                    </span>
                </div>

                <div class="search-li-box">
                    <b>职位类别：</b>
                    <span class="r-span">
                        <a class="current" href="javascript:;" style="display:none">全部</a>
                        <a href="javascript:;" rel="综合类" class="position-child" data-child-id="1">综合类</a>
                        <a href="javascript:;" rel="技术类" class="position-child" data-child-id="2">技术类</a>
                        <a href="javascript:;" rel="产品类" class="position-child" data-child-id="3">产品类</a>
                        <a href="javascript:;" rel="运营类" class="position-child" data-child-id="4">运营类</a>
                        <a href="javascript:;" rel="设计类" class="position-child" data-child-id="5">设计类</a>
                        <a href="javascript:;" rel="客服类" class="position-child" data-child-id="6">客服类</a>
                        <a href="javascript:;" rel="市场拓展" class="position-child" data-child-id="7">市场拓展</a>
                        <a href="javascript:;" rel="数据类" class="position-child" data-child-id="8">数据类</a>
                        <a href="javascript:;" rel="金融类" class="position-child" data-child-id="9">金融类</a>
                        <a href="javascript:;" rel="销售类" class="position-child" data-child-id="10">销售类</a>
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
                  <li><a href="#">&laquo;</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
      <p>© DEMO</p>
    </div>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  </body>
</html>