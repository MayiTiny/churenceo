$(function() {
    $(".banner-hover").removeClass("active");
    $(".banner-hover-off").addClass("active");
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
    	$("#deliver-submit").unbind();
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
            	alert("投递失败%>_<% 麻烦亲手动投递到churenceo@foxmail.com");
            	window.location.reload();
            	return false;
            }
        });  
        return false;
    });  
});