<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pl.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<%@ include file="/common.jsp" %>
</head>
<body>
	<form action="<%=rpath %>/demo8.jsp" method="post">
		姓名：<input type="text" name="name"><br>
		密码：<input type="password" name="password"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>