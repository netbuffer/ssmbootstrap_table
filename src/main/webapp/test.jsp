<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="l2d" uri="/WEB-INF/LongToDateTag.tld"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${test }</title>
</head>
<body>
	改变cookie方式:<a href="${pageContext.request.contextPath }/test/lang?lang=zh">中文locale</a>&emsp;<a href="${pageContext.request.contextPath }/test/lang?lang=en">英文locale</a><br/>
	拦截器传参方式:<a href="${pageContext.request.contextPath }/test/${test}?lang=zh">中文locale</a>&emsp;<a href="${pageContext.request.contextPath }/test/${test}?lang=en">英文locale</a>
	${test }<hr/>${uri}<br/>
	<l2d:l2d value="${showTime}" format="yyyy-MM-dd HH:mm:ss" /><br/>
	<code>fmt:message key="header.language":</code><fmt:message key="header.language"/><br/>
	<code>spring:message code="header.language":</code><spring:message code="header.language"/><br/><hr/>
	syn:${syn}<hr/>
	<span style="color:green;">user</span>:${user}<br />
	<span style="color:red;">u</span>:${u}
</body>
</html>