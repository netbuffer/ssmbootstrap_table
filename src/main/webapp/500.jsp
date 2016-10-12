<%@ page language="java" isErrorPage="true" errorPage="500.jsp"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>500</title>
<link rel="icon" href="favicon.ico">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<!--JSP的内置(隐含)对象及EL表达式的内置(隐含)对象 http://blog.chinaunix.net/uid-725717-id-2060317.html -->
	<div class="container">
		发生错误啦:
		<c:if test="${not empty result}">
			<h1>校验信息错误</h1>
			<hr/>
			<h3>错误数量:${result.errorCount}</h3>
			<h3>错误对象名:${result.objectName}</h3>
			<c:forEach items="${result.allErrors}" var="error">
				${error.code }/${error.defaultMessage }<br/>
			</c:forEach>
		</c:if>
		<c:if test="${not empty errMsg}">
			<hr/>错误信息:${errMsg }
		</c:if>
		${exception}
	</div>
</body>
</html>