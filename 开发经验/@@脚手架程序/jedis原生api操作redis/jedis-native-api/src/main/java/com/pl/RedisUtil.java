package com.pl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

public class RedisUtil {

	private static final String IP = "192.168.163.133";

	private static final int PORT = 6379;

	/**
	 * 私有构造器
	 */
	private RedisUtil() {

	}

	/**
	 * 存储对应不同实例的redis的redispool，即有多个redis实例，每个实例对应一个连接池
	 */
	private static Map<String, JedisPool> maps = new HashMap<String, JedisPool>();

	private static JedisPool getPool(String ip, int port) {
		String key = ip + ":" + port;
		JedisPool pool = null;
		if (!maps.containsKey(key)) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			try {
				/**
				 * 如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息
				 * 请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒)
				 */
				pool = new JedisPool(config, ip, port, 2000);
				maps.put(key, pool);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			pool = maps.get(key);
		}
		return pool;
	}

	static JedisPool getPool() {
		return getPool(IP, PORT);
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
	 */
	private static class RedisUtilHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static RedisUtil instance = new RedisUtil();
	}

	/**
	 * 当getInstance方法第一次被调用的时候，它第一次读取
	 * RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静
	 * 态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
	 * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
	 */
	public static RedisUtil getInstance() {
		return RedisUtilHolder.instance;
	}

	/**
	 * 获取redis连接
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public static Jedis getJedis(String ip, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(ip, port).getResource();
			} catch (Exception e) {
				// 销毁对象
				if (null != jedis) {
					jedis.close();
				}
			}
			count++;
		} while (jedis == null && count <= 5);
		return jedis;
	}

	public static Jedis getJedis() {
		return getJedis(IP, PORT);
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param jedis redis实例
	 */
	public static void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public static ShardedJedisPool getShardedJedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(500);
		poolConfig.setMaxIdle(1000 * 60);
		poolConfig.setTestOnBorrow(true);

		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo a = new JedisShardInfo("192.168.0.120", 6378);
		JedisShardInfo b = new JedisShardInfo("192.168.0.120", 6377);
		shards.add(a);
		shards.add(b);

		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards, Hashing.MURMUR_HASH);
		return pool;

	}
}
