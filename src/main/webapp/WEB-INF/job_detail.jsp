<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>搬砖网 ${jd.name } 职位详情</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/job_style.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
  <style type="text/css"></style><style id="holderjs-style" type="text/css"></style></head>
  <body>

<nav class="navbar navbar-inverse navbar-default navbar-fixed-top" role="navigation">
<div class="container">
<div class="navbar-header">
<a class="navbar-brand" href="<%=request.getContextPath()%>">搬砖网</a>
</div>
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav">
    <li><a href="#">首页</a></li>
    <li class="active"><a href="#social">社会招聘</a></li>
    <li><a href="#school">校园招聘</a></li>
    <li><a href="#about">关于我们</a></li>
</ul>
</div>
</div>
</nav>


<div class="container job-info">
<div class="panel  panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">${jd.name }</h3>
  </div>
  <div class="panel-body">
      <table class="table table-bordered">
            <tr class="warning">
                  <td class="col-sm-1">发布时间：</td>
                  <td class="col-sm-2"><fmt:formatDate value="${jd.beginDate }" pattern="yyyy-MM-dd" /></td>
                  <td class="col-sm-1">工作地点：</td>
                  <td class="col-sm-2">${jd.cityId }</td>
                  <td class="col-sm-1">工作性质：</td>
                  <td class="col-sm-2">${jd.recruitType }</td>
              </tr>
              <tr class="warning">
                  <td>所属部门：</td>
                  <td>${jd.department }</td>
                  <td>学   历：</td>
                  <td>${jd.degree }</td>
                  <td>招聘人数：</td>
                  <td>${jd.headCount }</td>
              </tr>
      </table>
  </div>
    <ul class="list-group">
        <li class="list-group-item">
            <h4 class="detail-title">岗位描述</h4>
            <p class="detail-content">${jd.postDescription }</p>
        </li>    
  </ul>
    <ul class="list-group">
        <li class="list-group-item">
            <h4 class="detail-title">岗位要求</h4>
            <p class="detail-content">${jd.postRequire }</p>
        </li>    
  </ul>
    <div class="panel-footer">
        <button type="button" class="btn btn-danger">申请岗位</button>
    </div>
</div>
</div>

<!--       <div class="footer"> -->
<!--         <p>© DEMO</p> -->
<!--       </div> -->
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>