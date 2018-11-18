package com.pl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 
 * @WebListener 指示当前类是一个监听器类
 * 实现了ServletContextListener接口，在servlet容器启动或者销毁的时候进行事件的触发
 *
 */
@WebListener
public class FirstListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext容器上下文启动的时候，进行一些参数初始化工作。。。");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
