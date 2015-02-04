<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>搬砖网 新增职位</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="搬砖网,${jd.name }内推,${jd.companyName }内推 ">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@include file="common/header.jsp" %>
    <link href="${pageContext.request.contextPath}/css/job_style.css" rel="stylesheet">
  </head>
  <body>
    <%@include file="common/nav.jsp" %>
	<form action="${pageContext.request.contextPath}/add" method="post" class="form-horizontal" target="_blank" role="form-inline">
	<div class="container job-info">
	<div class="panel  panel-info">
	  <div class="panel-heading">
	    <label for="name">职位名称</label><input type="text" class="form-control" name="name">
	  </div>
	  <div class="panel-body">
	    <table class="table table-bordered">
	      <tr class="warning">
	        <td class="col-sm-1">公&emsp;&emsp;司：</td>
	        <td class="col-sm-2">
	        <select class="form-control" name="company">
	          <c:forEach items="${companys }" var="comopany">
	            <option value="${comopany.companyId }">${comopany.companyName }</option>
	          </c:forEach>
	        </select>
	        </td>
	        <td class="col-sm-1">工作地点：</td>
	        <td class="col-sm-2"><input type="text" class="form-control" name="cityId" value="北京市"></td>
	        <td>所属部门：</td>
	        <td><input type="text" class="form-control" name="department"></td>
	      </tr>
	      <tr class="warning">
	          <td>学&emsp;&emsp;历：</td>
	          <td>
	            <select class="form-control" name="degree">
	              <option value="大专">大专</option>
	              <option selected value="本科">本科</option>
	              <option value="硕士">硕士</option>
	              <option value="博士">博士</option>
	            </select>
	          </td>
	          <td>招聘人数：</td>
	          <td><input type="text" class="form-control" name="headCount" value="-1"></td>
	          <td class="col-sm-1">职能类别：</td>
	          <td class="col-sm-2">
	            <select class="form-control" name="functionType">
	              <c:forEach items="${functionTypes }" var="functionType">
	                <option value="${functionType.code }">${functionType.name }</option>
	              </c:forEach>
	            </select>
	          </td>
	      </tr>
	      <tr class="warning">
	          <td>工作年限：</td>
	          <td><input type="text" class="form-control" name="yearsLimit" value="三年以上"></td>
	          <td>密码：</td>
	          <td><input type="text" class="form-control" name="password"></td>
	      </tr>
	    </table>
	  </div>
	    <ul class="list-group">
	        <li class="list-group-item">
	            <h4 class="detail-title">岗位描述</h4>
	            <p class="detail-content"><textarea class="form-control" rows="5" name="postDescription"></textarea></p>
	        </li>    
	  </ul>
	    <ul class="list-group">
	        <li class="list-group-item">
	            <h4 class="detail-title">岗位要求</h4>
	            <p class="detail-content"><textarea class="form-control" rows="5" name="postRequire"></textarea></p>
	        </li>    
	  </ul>
	    <div class="panel-footer">
	        <button type="submit" class="btn btn-default">新增</button> 
	    </div>
	</div>
	</div>
	</form>
<!--       <div class="footer"> -->
<!--         <p>© DEMO</p> -->
<!--       </div> -->
    <%@include file="common/footer.jsp" %>
    <script type="text/javascript">
    $(function() {
        $(".banner-hover").removeClass("active");
        $(".banner-hover-off").addClass("active");
    });
    </script>
</body>
</html>