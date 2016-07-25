<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ueditor</title>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript">
function getContentHtml(){
	alert(ue.getContent());
}
function getContent(){
	alert(ue.getContentTxt());
}
</script>
</head>
<body>
    <div class="container">
    	test
    	<hr/>
    	<!-- 加载编辑器的容器 http://fex.baidu.com/ueditor/#start-config -->
	    <script id="container" name="content" type="text/plain">
        	这里写你的初始化内容
    	</script>
	    <!-- 配置文件 -->
	    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ue/ueditor.config.js"></script>
	    <!-- 编辑器源码文件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/ue/ueditor.all.min.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
	        var ue = UE.getEditor('container',{
	        	autoHeight: true
	        });
	    </script>
	    <hr/>
    	<div class="row">
    		<div class="col-md-3"><button class="btn btn-primary" onclick="getContentHtml();">getContentHtml</button></div>
    		<div class="col-md-3"><button class="btn btn-primary" onclick="getContent();">getContent</button></div>
    	</div>
    </div>
</body>
</html>