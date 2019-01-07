package com.pl.test.set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.pl.RedisUtil;

import redis.clients.jedis.Jedis;

/**
 * redis中数据类型为hash的样例代码
 * 
 * @author pengli4coding
 *
 */
public class Demo {

	@Test
	public void test() {
		Jedis jedis = RedisUtil.getJedis();
		try {
			// 清空缓存
			System.out.println(jedis.flushAll());
			
			jedis.sadd("myset", "1");  
            jedis.sadd("myset", "2");  
            jedis.sadd("myset", "3");  
            jedis.sadd("myset", "4");  
            Set<String> setValues = jedis.smembers("myset");  
            System.out.println(setValues);  
  
            // 移除元素 
            jedis.srem("myset", "4");  
            System.out.println(jedis.smembers("myset"));// 获取所有加入的value  
            System.out.println(jedis.sismember("myset", "4"));// 判断 minxr  
                                                                // 是否是sname集合的元素  
            System.out.println(jedis.scard("myset"));// 返回集合的元素个数  
  
	        // 清空数据  
	        System.out.println(jedis.flushAll());  
	        // 添加数据  
	        jedis.sadd("sets", "HashSet");  
	        jedis.sadd("sets", "SortedSet");  
	        jedis.sadd("sets", "TreeSet");  
	        // 判断value是否在列表中  
	        System.out.println(jedis.sismember("sets", "TreeSet"));  
	        // 整个列表值  
	        System.out.println(jedis.smembers("sets"));  
	        // 删除指定元素  
	        System.out.println(jedis.srem("sets", "SortedSet"));  
	        // 出栈  
	        System.out.println(jedis.spop("sets"));  
	        System.out.println(jedis.smembers("sets"));  
	        //  
	        jedis.sadd("sets1", "HashSet1");  
	        jedis.sadd("sets1", "SortedSet1");  
	        jedis.sadd("sets1", "TreeSet");  
	        jedis.sadd("sets2", "HashSet2");  
	        jedis.sadd("sets2", "SortedSet1");  
	        jedis.sadd("sets2", "TreeSet1");  
	        // 交集  
	        System.out.println(jedis.sinter("sets1", "sets2"));  
	        // 并集  
	        System.out.println(jedis.sunion("sets1", "sets2"));  
	        // 差集  
	        System.out.println(jedis.sdiff("sets1", "sets2"));
	        
			// 清空缓存
			System.out.println(jedis.flushAll());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis);
		}
	}

}
