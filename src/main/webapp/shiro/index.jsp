<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro权限测试</title>
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
	});
</script>	
</head>
<body>
	<c:if test="${not empty msg}"><p style="text-align:center;">${msg}</p></c:if>
	perms:${perms}<br/>
	roles:${roles}<br/>
	<form action="${pageContext.request.contextPath }/shiro/" method="post">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">权限字符串</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input type="text" name="perms" placeholder="权限字符串" class="weui_input">
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">角色字符串</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input type="text" name="roles" placeholder="角色字符串" class="weui_input">
				</div>
			</div>
<!-- 	        <input name="img" value="img1"> -->
<!-- 	        <input name="img" value="img2"> -->
<!-- 	        <input name="img" value="img3"> -->
	        <div class="weui_btn_area">
		        <button type="submit" class="weui_btn weui_btn_primary">查询</button>
		    </div>
   </form>
</body>
</html>