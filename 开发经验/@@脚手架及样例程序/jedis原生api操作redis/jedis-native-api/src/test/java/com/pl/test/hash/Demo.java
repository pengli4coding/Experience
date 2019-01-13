package com.pl.test.hash;

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
			
			Map<String, String> pairs = new HashMap<String, String>();  
            pairs.put("name", "小立立");  
            pairs.put("age", "2");  
            pairs.put("sex", "Male");  
            jedis.hmset("kid", pairs);  
            List<String> name = jedis.hmget("kid", "name","sex","age");
            System.out.println(name);
            
            jedis.hdel("kid","age"); //删除map中的某个键值  
            System.out.println(jedis.hget("kid", "age")); // 因为删除了，所以返回的是null  
            System.out.println(jedis.hlen("kid")); // 返回key为kid的键中存放的值的个数  
            System.out.println(jedis.exists("kid"));// 是否存在key为kid的记录  
            System.out.println(jedis.hkeys("kid"));// 返回map对象中的所有key  
            System.out.println(jedis.hvals("kid"));// 返回map对象中的所有value  
  
            Iterator<String> iter = jedis.hkeys("kid").iterator();  
            while (iter.hasNext()) {  
                String key = iter.next();  
                System.out.println(key + ":" + jedis.hget("kid", key));  
            }  
  
	        // 清空数据  
	        System.out.println(jedis.flushAll());  
	        // 添加数据  
	        jedis.hset("hashs", "entryKey", "entryValue");  
	        jedis.hset("hashs", "entryKey1", "entryValue1");  
	        jedis.hset("hashs", "entryKey2", "entryValue2");  
	        // 判断某个值是否存在  
	        System.out.println(jedis.hexists("hashs", "entryKey"));  
	        // 获取指定的值  
	        System.out.println(jedis.hget("hashs", "entryKey")); 
	        System.out.println(jedis.hmget("hashs", "entryKey", "entryKey1"));  // 批量获取指定的值  
	        // 删除指定的值  
	        System.out.println(jedis.hdel("hashs", "entryKey"));  
	        // 为key中的域 field 的值加上增量 increment  
	        System.out.println(jedis.hincrBy("hashs", "entryKey", 123l));  
	        // 获取所有的keys  
	        System.out.println(jedis.hkeys("hashs"));  
	        // 获取所有的values  
	        System.out.println(jedis.hvals("hashs")); 
	        
			// 清空缓存
			System.out.println(jedis.flushAll());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis);
		}
	}

}
