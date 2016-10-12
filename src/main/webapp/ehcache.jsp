<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<title>ehcache</title>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<body>
	<div class="container">
		<div class="">
			<hr/>
			<h3>ehcache attributes:</h3>
			<div class="table-responsive">
			  <table class="table table-hover table-bordered table-striped">
			  	<tr><th>属性名</th><th>属性值</th></tr>
			  	<tr><td>caches</td><td>${caches}</td></tr>
			  	<tr><td>getDiskStorePath</td><td>${getDiskStorePath }</td></tr>
			  	<tr><td>getName</td><td>${getName }</td></tr>
			  	<tr><td>getStatus</td><td>${getStatus}</td></tr>
			  	<tr><td>getStatistics</td><td>${getStatistics }</td></tr>
			  </table>
			</div>
		</div>
	</div>
	
	

</body>
</html>