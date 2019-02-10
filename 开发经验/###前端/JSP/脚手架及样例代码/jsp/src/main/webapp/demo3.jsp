<!-- jsp指令用来声明jsp页面的一些属性等，例如编码方式、文档类型、导入的类包等等，一般jsp指令写在jsp页面的最上面几行 -->
<!-- 常见的jsp指令有三个：page、taglib、include -->
<%@ page language="java" %><!-- 指明当前jsp文件采用哪种语言 -->
<%@ page contentType="text/html; charset=UTF-8" %><!-- 指明当前jsp的内容类型 ，相当于resp.setContentType("text/html; charset=UTF-8")-->
<%@ page pageEncoding="UTF-8" %><!-- 指明当前jsp的编码，相当于resp.setCharacterEncoding("UTF-8")-->
<%@ page import="com.pl.model.*" %><!-- 指明当前jsp需要导入哪些包-->
<%@ page import="java.util.*" %><!-- 指明当前jsp需要导入哪些包-->
<%@ page errorPage="/404.html" %><!-- 指明发生错误时，即500时，跳转的页面，这里是服务器跳转，不需要指定项目名--><!-- 一般情况下，可以在web.xml文件中配置全局的错误跳转页面，请详见项目的web.xml文件 -->
<%@ page isELIgnored="false" %><!-- 指明当前页面是否忽略EL表达式 -->
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<title>demo3</title>
</head>
<body>
<%
User user = new User();
user.setName("pengli4coding");
user.setPassword("123");
%>
<%=user %>
<%="<br>" %>
<%
List<User> list = new ArrayList<User>();
for(int i=0;i<3;i++){
	User userTemp = new User();
	userTemp.setName("pengli4coding");
	userTemp.setPassword("123");
	list.add(userTemp);
}
%>
<%=list %>
<%--int i = 1/0; 测试错误页面的跳转，要看到错误页面的跳转请打开注释--%>

</body>
</html>