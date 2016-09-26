<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro-role-list</title>
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
	});
</script>	
</head>
<body>
	<c:forEach var="item" items="${roles}">
		${item}
	</c:forEach>
</body>
</html>