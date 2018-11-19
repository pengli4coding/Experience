package com.pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//在springboot中，如果要启动计划任务，需要在启动类上增加@EnableScheduling注解，代表计划任务生效。
public class AppStarter {
	
	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
}
