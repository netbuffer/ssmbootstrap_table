<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试jquery-ajax发送bean到后端</title>
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(e){
			console.log(e);
			$.ajax({
				url:"userlistq?name=aa",
				type:"POST",
				data:${querystr},
				success:function(){
					$("#content").text("成功");
				},
				error:function(){
					$("#content").text("失败");
				}
			});
		});
	});
</script>
</head>
<body>
	${query}<hr/>
	${querystr}
	<button id="btn">发送bean到后端</button>
	<p id="content"></p>
</body>
</html>