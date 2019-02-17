package com.pl.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {

	/**
	 * 删除
	 * @param id 要删除数据的id
	 * @param clazz 实体类型
	 */
	void delete(Integer id, Class<?> clazz);

	/**
	 * 删除
	 * @param t 实体类型
	 */
	<T> void delete(T t);

	/**
	 * 查询
	 * @param id 要查询数据的id
	 * @param clazz 实体类型
	 * @return
	 */
	<T> T get(Integer id, Class<?> clazz);
	
	/**
	 * 查询所有
	 * @param clazz 实体类型
	 * @return
	 */
	<T> List<T> findAll(Class<?> clazz);
	
	/**
	 * 传入sql语句查询
	 * @param sql
	 * @param clazz
	 * @return
	 */
	<T> List<T> query(Class<?> clazz, String sql, Object... objects);
	
	/**
	 * 保存对象并放回主键
	 * @param t
	 * @return
	 */
	<T> Serializable save(T t);
	
}
