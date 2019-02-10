<!-- jsp指令用来声明jsp页面的一些属性等，例如编码方式、文档类型、导入的类包等等，一般jsp指令写在jsp页面的最上面几行 -->
<!-- 常见的jsp指令有三个：page、taglib、include -->
<%@ page language="java" %><!-- 指明当前jsp文件采用哪种语言 -->
<%@ page contentType="text/html; charset=UTF-8" %><!-- 指明当前jsp的内容类型 ，相当于resp.setContentType("text/html; charset=UTF-8")-->
<%@ page pageEncoding="UTF-8" %><!-- 指明当前jsp的编码，相当于resp.setCharacterEncoding("UTF-8")-->

<!-- 这里例子演示jsp指令中的页面包含，demo4.jsp页面包含common.jsp页面 -->
<!-- 包含是服务器端包含，不用写项目名 -->
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>demo4</title>
<!-- 这种包含是静态包含，即最终两个jsp会编译为一个class文件，这种静态包含一般会在被包含的jsp文件中引入静态资源，如公用的css、js文件，还有公用的方法如获取项目路径 -->
<%@ include file="/common.jsp" %><!-- 服务器跳转不需要指定项目名 -->
</head>
<body>
<h1>项目路径为：<%=rpath %></h1><!-- 注意这里的rpath变量是在被包含文件common.jsp中定义的 -->
<div class="div1"></div>
</body>
</html>