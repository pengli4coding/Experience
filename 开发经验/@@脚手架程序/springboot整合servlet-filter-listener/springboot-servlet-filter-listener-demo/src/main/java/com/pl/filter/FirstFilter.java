package com.pl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @WebFilter 指示当前类是一个filter过滤器。
 * urlPatterns - 过滤器要过滤的路径。字符串数组。
 * 
 * 一般来说，Spring容器在加载配置信息的时候，优先加载@Configuration注解相关的配置。再加载其他注解的配置。
 *
 */
@WebFilter(urlPatterns="/*", initParams={@WebInitParam(name="test", value="test init param value")})
public class FirstFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 处理初始化参数
		String initParameter = filterConfig.getInitParameter("test");
		System.out.println("init param value : " + initParameter);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("请求过来先进行拦截器拦截");
		chain.doFilter(request, response);
		System.out.println("执行连接器链之后再走拦截器");
	}

	@Override
	public void destroy() {
		
	}

}
