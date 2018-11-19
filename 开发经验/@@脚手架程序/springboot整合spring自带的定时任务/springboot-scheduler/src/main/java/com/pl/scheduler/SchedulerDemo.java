package com.pl.scheduler;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 整合spring自带的scheduler计划任务的demo
 *
 */

/**
 * 方法是通过注解@Scheduled来实现计划任务的定义的。
 * 注解@Scheduled - 其中的属性cron是非常重要的。
 *  cron属性，是用于定义计划任务的执行策略的。 定义在什么时间，执行计划任务方法。
 *  cron属性的赋值，是使用cronEL赋值的。
 *  cron表达式的语法：
 *   seconds minutes hours day month week [year]
 *  不推荐使用7节的cron表达式，因为cron表达式不是spring开发定义的，很多支持计划任务的技术，都可以通过cron表达式来定义执行策略。
 *  cron表达式标准结构就是6节表达式。
 *  
 *  计划任务Scheduled是通过一个线程池实现的。是一个多线程的调度。
 *  SpringBoot会初始化一个线程池，专门用于执行计划任务。
 *  每个计划任务启动的时候，都从线程池中获取一个线程执行，如果发生异常，只是执行当前任务的线程发生异常，而不是计划任务调度线程发生异常。
 */
@Component// 加上@Component注解纳入到spring容器的管理中
public class SchedulerDemo {
	
	@Scheduled(cron="* * * * * ?")
	public void runTask() {
		Random rand = new Random();
		int i = rand.nextInt(100);
		if(i%3 == 0) {
			throw new RuntimeException("定时任务发生异常");
		}
		System.out.println(Thread.currentThread().getName() + "  cron=* * * * * ?---" + new Date());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedRate=1000)
	public void runTask1() {
		System.out.println(Thread.currentThread().getName() + "  fixedRate=1000---" + new Date());
	}
	
	@Scheduled(fixedDelay=3000)
	public void runTask2() {
		System.out.println(Thread.currentThread().getName() + "  fixedDelay=3000---" + new Date());
	}
}
