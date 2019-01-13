package com.pl.test.list;

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
			
            jedis.rpush("messages", "Hello how are you?");  
            jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");  
            jedis.rpush("messages", "I should look into this NOSQL thing ASAP");  
  
            // 再取出所有数据jedis.lrange是按范围取出，  
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
            List<String> values = jedis.lrange("messages", 0, -1);  
            System.out.println(values);  
  
	        // 清空数据  
	        System.out.println(jedis.flushAll());  
	        // 添加数据  
	        jedis.lpush("lists", "vector");  
	        jedis.lpush("lists", "ArrayList");  
	        jedis.lpush("lists", "LinkedList");  
	        // 数组长度  
	        System.out.println(jedis.llen("lists"));  
	        // 字串  ，结束位置的下标超过列表长度的时候
	        System.out.println(jedis.lrange("lists", 0, 10));  
	        // 修改列表中单个值  
	        jedis.lset("lists", 0, "hello list!");  
	        // 获取列表指定下标的值  
	        System.out.println(jedis.lindex("lists", 1));  
	        // 删除列表指定下标的值  
	        System.out.println(jedis.lrem("lists", 1, "vector"));  
	        // 删除区间以外的数据   
	        System.out.println(jedis.ltrim("lists", 0, 1));  
	        // 列表出栈  
	        System.out.println(jedis.lpop("lists"));  
	        // 整个列表值  
	        System.out.println(jedis.lrange("lists", 0, -1));
	        
			// 清空缓存
			System.out.println(jedis.flushAll());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis);
		}
	}

}
