<!-- 去除jsp导入带来的空行 -->
<%-- <%@ page trimDirectiveWhitespaces="true"%> --%>
<%@ page language="java" isErrorPage="true" errorPage="500.jsp"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Hello World</title>
	<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript">
		function test(){
			$.post('${pageContext.request.contextPath }/test/ajax', function(data) {
			   alert(data);
			});
		}
		
		function testjsonp(){
			$.ajax({ 
				url:"http://localhost:8080/ssmbootstrap_table/jsonp/tj/1.json", 
				dataType:"jsonp",
				data:{"productType":"01"}, 
				jsonp: "callback", 
				success: function(data) {
					alert(JSON.stringify(data));
				}, 
				error: function(jqXHR){ 
					console.log(jqXHR); 
				}
			});
			var head = document.head || $('head')[0] || document.documentElement; // code from jquery
			  var script = $(head).find('script')[0];
			  script.onerror = function(evt) {
				alert("网络超时,请稍后再试!");
			    // do some clean
			    // delete script node
			    if (script.parentNode) {
			      script.parentNode.removeChild(script);
			    }
			    // delete jsonCallback global function
			    var src = script.src || '';
			    var idx = src.indexOf('callback=');
			    if (idx != -1) {
			      var idx2 = src.indexOf('&');
			      if (idx2 == -1) {
			        idx2 = src.length;
			      }
			      var jsonCallback = src.substring(idx + 13, idx2);
			      delete window[jsonCallback];
			    }
			  };
		}
	</script>
</head>
<body>
	<h2>Hello World!</h2>
	<a href="user/showUser?id=1">user/showUser</a><hr/>
	${pageContext.request.scheme }<br/>
	${pageContext.request.serverName }<br/>
	${pageContext.request.serverPort }<br/>
	${pageContext.request.contextPath }<br/>
	通过EL设置basepath
	<c:set var="basepath">
		${pageContext.request.scheme}://${pageContext.request.serverName}<c:if test="${pageContext.request.serverPort!=80 }">:${pageContext.request.serverPort}</c:if>${pageContext.request.contextPath }
	</c:set>
	${basepath }<hr/>
	<pre>
	${pageContext.request.queryString} 取得请求的参数字符串
	${pageContext.request.requestURL} 取得请求的URL，不包括参数字符串
	${pageContext.request.contextPath}         服务的web application 的名称
	${pageContext.request.method}           取得HTTP 的方法(GET、POST)
	${pageContext.request.protocol}         取得使用的协议(HTTP/1.1、HTTP/1.0)
	${pageContext.request.remoteUser}         取得用户名称
	${pageContext.request.remoteAddr }         取得用户的IP 地址
	${pageContext.session.id}               取得session 的ID
	${pageContext.servletContext.serverInfo}   取得主机端的服务信息
	</pre>
	<button onclick="test();">ajax</button>&emsp; <button onclick="testjsonp();">testjsonp</button>
	<hr/>
	<a href="${basepath}/metrics">metrics</a>
</body>
</html>
