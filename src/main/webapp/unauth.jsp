<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无权限</title>
</head>
<body>
	<c:set var="basepath">
		${pageContext.request.scheme}://${pageContext.request.serverName}<c:if test="${pageContext.request.serverPort!=80 }">:${pageContext.request.serverPort}</c:if>${pageContext.request.contextPath }
	</c:set>
	<hr/>
	无权限》》<a href="${basepath}">去登录</a>
</body>
</html>