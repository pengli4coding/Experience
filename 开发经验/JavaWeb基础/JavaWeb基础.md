# EL表达式

## 简介

> JSP中可以使用EL（Expression Language） 表达式。EL表达式使用“${}”括起来的脚本，用来更方便地读取对象。EL表达式写在JSP的HTML代码中，而不能写在"<%"与"%>"引起的JSP脚本中。

## EL读取四大域对象中的属性值

四大域对象分别为：page、request、session、application

比如说我们的EL表达式为：${username}，此时会**按照page、request、session、application的顺序依次查找**（即依次pageContext.getAttribute("username")、request.getAttribute("username")、session.getAttribute("username")、application.getAttribute("username")），假如中途找到了username，则直接返回，如果没有找到则继续查找下去。

## EL表达式运算

| 运算描述 | EL表达式          | 输出               |
| -------- | ----------------- | :----------------- |
| 加       | ${1+1}            | 2                  |
| 减       | ${3-2}            | 1                  |
| 乘       | ${2*3}            | 6                  |
| 除       | ${11/3}           | 3.6666666666666665 |
| 取模     | ${11%3}           | 2                  |
| 小于     | ${2<3}            | true               |
| 小于等于 | ${5<=3}           | false              |
| 大于     | ${3>2}            | true               |
| 大于等于 | ${2>=3}           | false              |
| 等于     | ${2==2}           | true               |
| 不等于   | ${2!=2}           | false              |
| 与       | ${2==2 && 3==3}   | true               |
| 或       | ${2==2 \|\| 3!=3} | true               |

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

# 自定义标签

## 为什么要自定义标签

- 在我们进行jsp页面开发的时候，我们最终总是希望jsp页面上面都是标签而没有任何的java代码，这样美工去美化页面的时候就方便很多（因为美工一般来说是看不懂java代码的，他们只认标签）。
- 我们可以将自定义好的标签复用到不同的项目中，提升开发效率

## 如何自定义标签

- 第一步：编写完成标签功能的Java类（标签处理器）
- 第二步：编写标签库描述文件（tld），在tld文件中对自定义标签进行描述
- 第三步：在jsp页面中导入和使用标签

### 第一步：编写完成标签功能的Java类（标签处理器）

### 第二步：编写标签库描述文件（tld），在tld文件中对自定义标签进行描述

### 第三步：在jsp页面中导入和使用标签

# JSTL标签

## 简介

### 