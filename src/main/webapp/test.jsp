<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="l2d" uri="WEB-INF/LongToDateTag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${test }</title>
</head>
<body>
	${test }<hr/>${uri}<br/>
	<l2d:l2d value="${showTime}" format="yyyy-MM-dd HH:mm:ss" />
</body>
</html>