<%@ page language="java" isErrorPage="true" errorPage="500.jsp"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>error</title>
<link rel="icon" href="favicon.ico">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>发生错误啦:</h1><br/>
		${ex}<hr/>
		<h2>错误堆栈</h2><hr>
		${stackTrace}
		<c:if test="${not empty errCode&&not empty errMsg}">
			<h1>错误信息</h1>
			<hr/>
			<h3>错误状态码:<span class="label label-danger">${errCode}</span></h3>
			<h3>错误详情:<span class="label label-info">${errMsg}</span></h3>
		</c:if>
		<c:if test="${not empty errMsg}">
			<hr/>错误信息:${errMsg }
		</c:if>
	</div>
</body>
</html>