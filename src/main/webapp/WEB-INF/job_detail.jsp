<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="搬砖网,${jd.name }内推,${jd.companyName }内推 ">
    <meta name="author" content="">
    <title>搬砖网 ${jd.companyName }-${jd.name }内推 ${jd.companyName }-${jd.name }职位详情</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/file-uploader/3.7.0/fineuploader.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/job_style.css" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <style type="text/css">
    </style>
    <style id="holderjs-style" type="text/css"></style>
  </head>
  <body>

  <%@include file="header.jsp" %>
  <input id="contextPath" type="hidden" value="${pageContext.request.contextPath}">
  <div class="container job-info">
    <div class="panel  panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">${jd.name }</h3>
      </div>
    <div class="panel-body">
      <table class="table table-bordered">
        <tr class="warning">
          <td class="col-sm-1">公&emsp;&emsp;司：</td>
          <td class="col-sm-2">${jd.companyName }</td>
          <td class="col-sm-1">工作地点：</td>
          <td class="col-sm-2">${jd.cityId }</td>
          <td>所属部门：</td>
          <td>${jd.department }</td>
        </tr>
        <tr class="warning">
	      <td>学&emsp;&emsp;历：</td>
	      <td>${jd.degree }</td>
	      <td>招聘人数：</td>
	      <td><c:if test="${jd.headCount==-1 }">若干</c:if><c:if test="${jd.headCount!=-1 }">${jd.headCount }</c:if></td>
	      <td class="col-sm-1">发布时间：</td>
	      <td class="col-sm-2"><fmt:formatDate value="${jd.endDate }" pattern="yyyy-MM-dd" /></td>
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
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">我要内推</button>&nbsp;&nbsp;&nbsp;&nbsp;<--&nbsp;&nbsp;快点我 
      </div>
    </div>
  </div>

  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow: initial;">
    <div class="modal-dialog" style="top: 50px;">
      <div class="modal-content">
	    <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	      <h4 class="modal-title" id="myModalLabel">职位在线投递</h4>
	    </div>
	    <form class="form-horizontal" id="deliver-form" action="${pageContext.request.contextPath}/deliver" type="post" role="form">
	      <div class="modal-body">
	        <input id="resumePath" name="resumePath" type="hidden">
	        <input id="title" name="title" type="hidden" value="${jd.companyName }-${jd.name}">
	        <div class="form-group">
		      <label for="inputName" class="col-sm-3 control-label">姓名</label>
		      <div class="col-sm-6">
		        <input type="text" class="form-control" id="inputName" name="name" placeholder="姓名">
		      </div>
		    </div>
	        <div class="form-group">
		      <label for="emailName" class="col-sm-3 control-label">邮箱</label>
		      <div class="col-sm-6">
		        <input type="text" class="form-control" id="emailName" name="email" placeholder="亲确保正确哦">
		      </div>
		    </div>
		    <div class="form-group">
		      <label for="msgName" class="col-sm-3 control-label">留言</label>
              <div class="col-sm-6">
                <textarea rows="3" class="form-control" id="msgName" name="msg" placeholder="任何疑问或相对搬砖君说的话^_^"></textarea>
              </div>
		    </div>
		    <div class="form-group">
		      <label for="bootstrapped-fine-uploader" class="col-sm-3 control-label">简历</label>
		      <div class="col-sm-6">
		        <div id="bootstrapped-fine-uploader"></div>
		      </div>
		    </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">亲也可以手动发送“搬砖网-姓名-工作年限-${jd.companyName }-职位名” 到 job@churenceo.com 并附上职位链接与简历。</div>
          </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary" id="deliver-submit">投递</button>
	      </div>
	    </form>
	  </div>
	</div>
  </div>
<!--       <div class="footer"> -->
<!--         <p>© DEMO</p> -->
<!--       </div> -->
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-modal/2.2.5/js/bootstrap-modalmanager.min.js"></script>
    <script src="http://cdn.bootcss.com/file-uploader/3.7.0/fineuploader-jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/detail.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/statistic.js"></script>
  </body>
</html>