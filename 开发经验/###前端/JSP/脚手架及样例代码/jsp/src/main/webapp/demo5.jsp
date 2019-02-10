<!-- jsp指令用来声明jsp页面的一些属性等，例如编码方式、文档类型、导入的类包等等，一般jsp指令写在jsp页面的最上面几行 -->
<!-- 常见的jsp指令有三个：page、taglib、include -->
<%@ page language="java" %><!-- 指明当前jsp文件采用哪种语言 -->
<%@ page contentType="text/html; charset=UTF-8" %><!-- 指明当前jsp的内容类型 ，相当于resp.setContentType("text/html; charset=UTF-8")-->
<%@ page pageEncoding="UTF-8" %><!-- 指明当前jsp的编码，相当于resp.setCharacterEncoding("UTF-8")-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- 指明当前jsp的导入的第三方标签库-->

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>demo5</title>
</head>
<body>
	<h1>这是demo5.jsp</h1>
</body>
</html>