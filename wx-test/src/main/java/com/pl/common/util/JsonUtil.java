package com.pl.common.util;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.entity.OpenidSessionKey;

/**
 * 处理json串的工具类
 * 
 * @Description:
 * @author 910146
 * @date: 2018-11-05 18:21:33
 *
 */
public class JsonUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * json字符串转为对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		try {
			return clazz.equals(String.class) ? (T) jsonStr : mapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转为json字符串
	 * @param obj
	 * @return
	 */
	public static <T> String objectToJson(T obj) {
		if (null == obj) {
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 对象转为json字符串（漂亮格式）
	 * @param obj
	 * @return
	 */
	public static <T> String objectToJsonPretty(T obj) {
		if(null == obj) {
			return null;
		}
		try {
			return obj instanceof String ?(String) obj:mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		OpenidSessionKey obj = new OpenidSessionKey();
		obj.setErrcode(200);
		obj.setErrmsg("成功");
		obj.setOpenid("abcdefghijklmn");
		obj.setSession_key("aaa");
		String json1 = JsonUtil.objectToJson(obj);
		String json2 = JsonUtil.objectToJsonPretty(obj);
		System.out.println(json1);
		System.out.println("========================");
		System.out.println(json2);
	}*/
	
}
