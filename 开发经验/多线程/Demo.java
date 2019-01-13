package com.pl.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {

	public static void main(String[] args) {
		// 使用newCachedThreadPool()方法创建无界线程池
		// 注意，这里的“无界”指的是池中存放的线程个数是理论上的Integer.MAX_VALUE最大值
		ExecutorService threadPool = Executors.newCachedThreadPool();
		// 放入第一个线程
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程一开始执行，当前时间为：" + System.currentTimeMillis());
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程一结束执行，当前时间为：" + System.currentTimeMillis());
			}
		});
		// 放入第二个线程
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程二开始执行，当前时间为：" + System.currentTimeMillis());
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程二结束执行，当前时间为：" + System.currentTimeMillis());
			}
		});
	}
}
