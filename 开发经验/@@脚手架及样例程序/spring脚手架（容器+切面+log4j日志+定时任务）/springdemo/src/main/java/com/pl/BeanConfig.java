package com.pl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration// 声明这是一个spring中的配置类
@ComponentScan(basePackages = "com.pl")// 相当于xml配置文件中的扫包<context:component-scan base-package="com.pl"/>
@EnableScheduling// 激活任务调度的@Scheduled注解
public class BeanConfig {


}
