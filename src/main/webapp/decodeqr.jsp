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
	<h1>请上传要解析的二维码</h1>
	<form action="${pageContext.request.contextPath }/test/decodeqr" method="post"
        enctype="multipart/form-data">
		<input type="file" name="qrimg"/>
		<button type="submit">提交</button>
    </form>
</body>
</html>