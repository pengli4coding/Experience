<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>demo1</title>
</head>
<body>
<!-- jsp脚本一共由三种编写语法 -->
<% 
for(int i=0;i<10;i++){
	out.print("<p>hello jsp</p>");
}
%>

<%="你好jsp" %>

<%!
private String param = "这是定义成员变量";
private int add(int a,int b){//这是定义成员函数
	return a + b;
}
%>
</body>
</html>