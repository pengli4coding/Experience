package com.pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @SpringBootApplication 指示当前类是一个springboot应用入口类型。
 * @ServletComponentScan 通知spring容器，扫描Servlet注解的。如： @WebServlet，默认的扫描路径是，当前启动类所在包及其所有的子孙包。
 *
 */
@SpringBootApplication
@ServletComponentScan
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
