<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="阿里内推,内推,内部推荐,阿里,阿里巴巴内部推荐">
    <meta name="author" content="">
    <title>搬砖网 知名互联网公司内部推荐 阿里内推 百度内推</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index_style.css" rel="stylesheet">
    <style id="holderjs-style" type="text/css"></style></head>

    <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">搬砖网</h3>
              <ul class="nav masthead-nav">
<!--                 <li class="active"><a href="#">首页</a></li> -->
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
            <h2 class="illustration margin-top-3">知名互联网公司内部推荐</h2>
            <p class="illustration-lead">都是大厂！厂！厂！厂！！</p>
            <h3 class="illustration margin-top-2">已开通
                <a class="underline bold" href="<%=request.getContextPath()%>/list?company=1">阿里巴巴</a>、<a class="underline bold" href="<%=request.getContextPath()%>/list?company=2">百度</a>、<a class="underline bold" href="<%=request.getContextPath()%>/list?company=3">美团</a>、<a class="underline bold" href="<%=request.getContextPath()%>/list?company=4">去哪儿</a> 内推直通车，腾讯等即将上线...
            </h3>

          </div>

          <div class="mastfoot">
            <div class="inner footer">
              <p>京ICP备14047376</p>
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
            window.location.href = "list?keyword=" + encodeURIComponent(keyword);
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
    <script type="text/javascript" src="js/statistic.js"></script>
    </body>
</html>
