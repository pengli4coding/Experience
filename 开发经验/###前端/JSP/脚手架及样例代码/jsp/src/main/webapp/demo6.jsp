<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>demo6</title>
<!-- jsp的include指令用来动态包含其它页面，动态包含两个jsp会编译为两个class文件，动态包含一般可以用来做动态视图用 -->
<jsp:include page="/common.jsp"></jsp:include><!-- 服务器跳转不需要指定项目名 -->
</head>
<body>
	<h1>这是demo6.jsp</h1>
</body>
</html>