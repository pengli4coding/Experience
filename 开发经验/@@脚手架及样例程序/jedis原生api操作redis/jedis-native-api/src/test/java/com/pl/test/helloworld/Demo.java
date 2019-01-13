package com.pl.test.helloworld;


import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Demo {

	@Test
	public void test() {
		// 获取redis的连接
		Jedis jedis = new Jedis("192.168.163.133",6379);
		// 往redis中设值
		jedis.set("hello", "redis");
		// 往redis中取值
		String value = jedis.get("hello");
		System.out.println(value);
		jedis.close();
	}
	
	/**
	 * 测试redis连接池方式
	 */
	@Test
	public void testPool() {
		// 创建redis连接池配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置连接最大空闲时间（以秒为单位）
		config.setMaxIdle(20);
		// 设置最大连接数
		config.setMaxTotal(50);
		// 创建连接池对象
		JedisPool pool = new JedisPool(config,"192.168.163.133",6379);
		// 从池中获取redis连接
		Jedis jedis = pool.getResource();
		// 往redis中设值
		jedis.set("hello", "redis");
		// 往redis中取值
		String value = jedis.get("hello");
		System.out.println(value);
		// 关闭资源
		jedis.close();
		pool.close();
		
	}
}
