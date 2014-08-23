<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Project@YuFei</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index_style.css" rel="stylesheet">
    <style id="holderjs-style" type="text/css"></style></head>

    <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">LOGO放置位</h3>
              <ul class="nav masthead-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="#">社会招聘</a></li>
                <li><a href="#">校园招聘</a></li>
                <li><a href="#">关于我们</a></li>
              </ul>
            </div>
          </div>

          <div class="inner cover">
              <div class="job">
          <form class="form-inline" role="form">
              <div class="form-group  col-sm-10">
                  <input type="text" class="form-control input-lg" id="job_search" placeholder="请输入职位关键词">
              </div>  
              <a href="javascript:void(0)" class="btn btn-lg btn-default js-search">搜索</a>
          </form>
                  </div>
            <h1 class="illustration">介绍公司标题</h1>
            <p class="illustration-lead">介绍公司内容</p>

          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>此处写版权声明</p>
            </div>
          </div>

        </div>

      </div>

    </div>
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
    }); 
    </script>
    </body>
</html>
