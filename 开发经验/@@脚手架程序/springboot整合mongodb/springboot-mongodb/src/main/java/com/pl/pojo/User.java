package com.pl.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Mongodb中，一条数据成为一个document，若干相关的document组成的集合成为collection。
 * document相当于数据库中的数据，collection相当于数据库中的一个表格。
 * 注解简介：
 *  @Document  表示当前的类型是需要对应mongodb数据中的document文档的。
 *    属性 collection - 用于配置当前类型的document存放位置的。相当于定义表格。
 *  @Field - 用来描述java中的属性映射mongodb中一个document中的什么字段。
 */
@Document(collection="t_user")
public class User {
	
	@Id
	@Field("id")
	private Long id;
	@Field("name")
	private String name;
	@Field("age")
	private int age;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
}
