<%@ page language="java" isErrorPage="true" errorPage="500.jsp"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>500</title>
<link rel="icon" href="favicon.ico">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		发生错误啦:
		<h3>错误数量:${result.errorCount}</h3>
		<h3>错误对象名:${result.objectName}</h3>
		<c:forEach items="${result.allErrors}" var="error">
			${error.code }/${error.defaultMessage }<br/>
		</c:forEach>
	</div>
</body>
</html>