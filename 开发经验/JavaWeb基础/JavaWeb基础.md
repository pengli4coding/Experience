# EL表达式

## 简介

> JSP中可以使用EL（Expression Language） 表达式。EL表达式使用“${}”括起来的脚本，用来更方便地读取对象。EL表达式写在JSP的HTML代码中，而不能写在"<%"与"%>"引起的JSP脚本中。

## EL表达式运算

| 运算描述 | EL表达式 | 输出 |
| -------- | -------- | :--- |
| 加       | ${1+1}   | 2    |
| 减       | ${3-2}   | 1    |
| 乘       | ${2*3}   | 6    |
| 除       | ${11/3}  | 3    |
| 取模     | ${11%3}  | 2    |
|          |          |      |
|          |          |      |
|          |          |      |
|          |          |      |

## EL表达式不起作用的问题

有些时候会发现EL表达式不起作用，需要在jsp页面头部加上**<%@ page isELIgnored="false" %>**这一行代码才能使得EL表达式生效，这主要是因为web.xml的版本不对，在Servlet2.3或者更早的web.xml版本中EL表达式默认是不生效的，所以要使EL表达式默认是生效的，必须使用Servlet2.4及以上的web.xml版本。下面给出Servlet3.1版本的web.xml文件（从tomcat8中截取过来的）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1">

  <display-name>Welcome to Tomcat</display-name>
  <description>
     Welcome to Tomcat
  </description>

</web-app>

```



# JSTL标签

## 简介

