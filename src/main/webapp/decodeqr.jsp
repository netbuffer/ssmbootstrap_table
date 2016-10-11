<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="l2d" uri="/WEB-INF/LongToDateTag.tld"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<c:if test="${empty test}">解析二维码</c:if>
<c:if test="${not empty test}">${test}</c:if>
</title>
<link href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/css/fileinput.min.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/js/plugins/canvas-to-blob.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/js/plugins/sortable.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/js/plugins/purify.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/themes/gly/theme.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/js/fileinput.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-fileinput/4.3.5/js/locales/zh.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#qrfile").fileinput({
			'language':'zh',
			'showUpload':true, 
			'previewFileType':'any',
			'allowedFileTypes':['image', 'html', 'text', 'video', 'audio', 'flash', 'object'],
			'allowedFileExtensions':['jpg', 'gif', 'png', 'txt'],
			'allowedPreviewTypes':['image', 'html', 'text', 'video', 'audio', 'flash', 'object'],
			'uploadUrl':"${pageContext.request.contextPath }/test/decodeqr",
			'uploadExtraData':{"name":"发送的额外数据"}
		});
	    $('#qrfile').on('fileuploaded', function(event, data, previewId, index) {
	        var form = data.form, files = data.files, extra = data.extra,
	            response = data.response, reader = data.reader;
	        console.log('response:%s',response);
	        $('#qrfile').fileinput('refresh');
	        $("#qrresult").text("解析结果:"+response);
	    });
	    //$('#input-id').fileinput('refresh');
	});
</script>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>请上传要解析的二维码<small>qrcode</small></h1>
		</div>
		<form action="${pageContext.request.contextPath }/test/decodeqr" method="post"
	        enctype="multipart/form-data">
			<input id="qrfile" type="file" name="qrimg"/>
	    </form>
	    <hr/>
	    <div class="alert alert-info" role="alert" id="qrresult">
	    </div>
	</div>
</body>
</html>