<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pl.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>demo8</title>
</head>
<body>
	<h1>这是demo8.jsp</h1>
	<!-- 
		这个例子先从login.jsp页面看起，再跳转到本页面
	 -->
	<jsp:useBean id="user1" class="com.pl.model.User" scope="page"></jsp:useBean><!-- 该jsp指令相当于pageContext.setAttribute("user1", new User()); -->
	<jsp:setProperty property="*" name="user1" /><!-- 该jsp指令的意思是逐一从request.getParameter(属性名)获取请求参数，并通过反射的方式注入属性值到上面的user1中 -->
	<%
		User user = (User) pageContext.getAttribute("user1");
		out.print(user);
	%>
</body>
</html>