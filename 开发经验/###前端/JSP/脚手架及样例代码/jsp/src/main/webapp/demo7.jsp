<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>demo7</title>
</head>
<body>
	<h1>这是demo7.jsp</h1>
	<%
		if (true) {// 这里一般可以写上进行页面跳转的条件
			request.setAttribute("username", "小立立");
			request.setAttribute("password", "123456");
	%>
	<!-- 这个jsp指令相当于req.getRequestDispatcher("跳转页面地址").forward(request, response); -->
	<jsp:forward page="/controller1"></jsp:forward><!-- 服务器跳转不需要指定项目名 -->
	<%
		}
	%>

</body>
</html>