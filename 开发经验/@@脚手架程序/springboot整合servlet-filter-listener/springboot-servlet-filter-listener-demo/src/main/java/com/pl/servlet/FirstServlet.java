package com.pl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 定义好servlet后，传统的项目一般是在web.xml中进行配置。
 * 在spring-boot中，是使用注解实现servlet配置的。使用的是servlet3.0以后的标准进行配置的。
 * @WebServlet注解。
 * 属性 ：
 * name 对应web.xml中配置的 <servlet-name>
 * urlPatterns 对应web.xml中配置的 <servlet-mapping>
 * initParams - <init-param>初始化参数, @WebInitParam(name="初始化参数名", value="初始化参数值")
 * 
 */
@WebServlet(name = "", urlPatterns = { "/first", "/first1" }, initParams = {
		@WebInitParam(name = "test", value = "value") }, loadOnStartup = 1)
public class FirstServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("<center><h1>测试Servlet1</h1></center>");
	}

}
