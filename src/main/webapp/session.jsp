<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>session属性</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<body>
	<div class="container">
		<div class="">
			<h2>Host name :<%=java.net.InetAddress.getLocalHost().getHostName()%></h2>
			<hr/>
			<h3>Session attributes:</h3>
			<div class="table-responsive">
			  <table class="table table-hover table-bordered table-striped">
			  	<tr><th>属性名</th><th>属性值</th></tr>
			  		<%
						for (Enumeration e = session.getAttributeNames(); e
								.hasMoreElements();) {
							String attribName = (String) e.nextElement();
							Object attribValue = session.getAttribute(attribName);
					%>
						<tr><td><%=attribName%></td><td><%=attribValue%></td></tr>
					<%
						}
					%>
			  </table>
			</div>
		</div>
	</div>
	
	

</body>
</html>