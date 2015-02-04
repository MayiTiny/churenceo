<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>

<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script>
  $(function() {
    $('.banner-hover').hover(
      function () {
        $(".banner-hover").removeClass("active");
        $(this).addClass("active");
      },
      function () {
        $(".banner-hover").removeClass("active");
        $(".default").addClass("active");
    });
    $(".logout").click(function() {
        $.ajax({
          type: 'POST',
          url: '<%=request.getContextPath()%>/user/logout' ,
          success: function(data) {
            window.location.reload();
          }
        });
    });
  });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/statistic.js"></script>