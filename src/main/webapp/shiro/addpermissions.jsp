<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro-permission-add</title>
<link rel="icon" href="favicon.ico">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" action="${pageContext.request.contextPath }/shiro/permission/add" method="post">
			<fieldset>

				<!-- Form Name -->
				<legend>添加权限</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">权限名</label>
					<div class="col-md-4">
						<input id="name" name="name" placeholder="权限名"
							class="form-control input-md" required="" type="text"> <span
							class="help-block">如：index-add</span>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="permission">权限标识</label>
					<div class="col-md-4">
						<input id="permission" name="permission" placeholder="权限标识"
							class="form-control input-md" required="" type="text"> <span
							class="help-block">如:add</span>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="url">权限路径</label>
					<div class="col-md-4">
						<input id="url" name="url" placeholder="权限路径"
							class="form-control input-md" required="" type="text"> <span
							class="help-block">如:/index.jsp</span>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="parentPermission">父权限</label>
					<div class="col-md-4">
						<input id="parentPermission" name="parentPermission"
							placeholder="父权限" class="form-control input-md" required=""
							type="text"> <span class="help-block">如:index</span>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="save"></label>
					<div class="col-md-4">
						<button id="save" name="save" class="btn btn-primary">保存</button>
					</div>
				</div>

			</fieldset>
		</form>

	</div>
</body>
</html>