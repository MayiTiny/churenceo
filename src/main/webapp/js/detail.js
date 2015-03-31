$(function() {
    $('#myModal').modal({
    	backdrop: 'static',
    	show: false
  	});
    var uploader = $("#bootstrapped-fine-uploader").fineUploader({
        element: $('#bootstrapped-fine-uploader'),
        request: {
            endpoint: $("#contextPath").val() + '/upload'
        },
        multiple: false,
        text: {
            uploadButton: '上传简历' //上传按钮的文字
        },
        validation: {
            allowedExtensions: ['doc', 'pdf', 'docx',], //限制上传格式
            sizeLimit: 1024*1024*10 //限制上传大小 byte
        },
        template: 
        	  '<div class="qq-uploader span12">' +
        	  '<pre class="qq-upload-drop-area span12"><span>{dragZoneText}</span></pre>' +
        	  '<div class="qq-upload-button btn btn-success" style="width: auto; background-color: #428bca; border-color: #357ebd;">{uploadButtonText}</div>' +
        	  '<span class="qq-drop-processing"><span>{dropProcessingText}</span>'+
        	  '<span class="qq-drop-processing-spinner"></span></span>' +
        	  '<ul class="qq-upload-list" style="margin-top: 10px; text-align: center;"></ul>' +
        	  '</div>',
        classes: {
            success: 'alert alert-success',
            fail: 'alert alert-error'
        }
      });
    uploader.on("complete", function(event, id, name, responseJSON, test) {
    	$("#resumePath").val(responseJSON.path);
    });
    $("#deliver-form").submit(function() {
    	if ($("#inputName").val() == "") {
    	    alert("姓名不能为空哦！请报上您的大名！");
    	    return false;
    	}
    	var emailName = $("#emailName").val();
    	var atIndex = emailName.indexOf("@");
    	var dotIndex = emailName.lastIndexOf(".");
    	if (atIndex <= 0 || dotIndex <= atIndex) {
    	    alert("你的邮箱填错了！我读书少，你不要骗我！");
    	    return false;
    	}
    	if ($("#resumePath").val() == "") {
    	    alert("您的简历还没有上传呢呀！请上传简历后再投递哦！");
    	    return false;
    	}
    	$("#deliver-submit").attr('disabled',true);
        $(this).ajaxSubmit({
            type: "post",
            dataType: "json",
            url: $("#contextPath").val() + "/deliver",
            success: function(data) {
                alert("投递成功！简历经过搬砖网筛选通过后，会直接内部推荐到用人部门！请耐心等待，或微信搜索“搬砖网”关注我们。");
                window.location.reload();
                return false;
            },
            error: function(data) {
            	alert("投递失败%>_<% 麻烦亲手动投递到job@churenceo.com");
            	window.location.reload();
            	return false;
            }
        });  
        return false;
    });
    $(".js-fav").click(function() {
    	if (userId <= 0) {
    		alert("请先登录再收藏职位~~");
    		return;
    	}
    	$(this).attr('disabled',true);
    	$.ajax({
            type: "GET",
            url: context + "/favorites/add",
            data: {"jdId": $("#jobId").val(), "userId": userId, "companyId": $("#companyId").val() },
            dataType: "json",
            success: function(data) {
            	if (data.succeed == false) {
            		alert(data.msg);
            	} else {
            		$(".js-fav").hide();
            		$(".js-fav-cancel").removeAttr("disabled");
            		$(".js-fav-cancel").show();
            		alert("收藏职位成功，您可到个人中心查看。亲，别忘了稍后投递简历哦~~");
            	}
	        }
        });
    });
    $(".js-fav-cancel").click(function() {
    	if (userId <= 0) {
    		alert("请先登录再取消收藏~~");
    		return;
    	}
    	$(this).attr('disabled',true);
    	$.ajax({
            type: "GET",
            url: context + "/favorites/cancel",
            data: {"jdId": $("#jobId").val(), "userId": userId },
            dataType: "json",
            success: function(data) {
            	if (data.succeed == false) {
            		alert(data.msg);
            	} else {
            		$(".js-fav-cancel").hide();
            		$(".js-fav").removeAttr("disabled");
            		$(".js-fav").show();
            		alert("取消收藏成功，在看看其他职位吧~~");
            	}
	        }
        });
    });
});