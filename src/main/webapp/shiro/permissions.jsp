<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro-permission-list</title>
<link rel="icon" href="favicon.ico">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<link
	rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
	});
</script>	
</head>
<body>
	<div class="container-fluid">
		<table class="table">
	      <caption>
	      	<c:choose>
	      		<c:when test="${not empty role}">查询<span style="font-weight:bold;">${role.name}</span>对应的</c:when>
	      		<c:when test="${not empty user}">查询<span style="font-weight:bold;">${user.name}</span>对应的</c:when>
	      	</c:choose>
	      	权限列表
	      </caption>
	      <thead>
	        <tr>
	          <th></th>
	          <th>ID</th>
	          <th>Name</th>
	          <th>URL</th>
	        </tr>
	      </thead>
	      <tbody>
	        <c:forEach var="item" items="${permissions}">
				<tr>
		          <td>
		          	<label>
				      <input name="permissionid" value="${item.id}" type="checkbox"/> &emsp;<span class="glyphicon glyphicon-ok"></span>
				    </label>
				  </td>
		          <td>${item.id}</td>
		          <td>${item.name}</td>
		          <td>${item.url}</td>
		        </tr>
			</c:forEach>
	      </tbody>
	    </table>
	</div>
</body>
</html>