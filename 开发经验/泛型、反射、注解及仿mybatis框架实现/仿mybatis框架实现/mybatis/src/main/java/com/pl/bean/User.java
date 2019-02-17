package com.pl.bean;

import java.util.Date;

import com.pl.annotation.Column;
import com.pl.annotation.Id;
import com.pl.annotation.Table;

@Table("tb_user")
public class User {

	@Id("ID")
	private Long id;
	private String name;
	private String sex;
	private String age;
	@Column("create_date")
	private Date createDate;
	
	public User() {
	}
	public User(Long id, String name, String sex, String age, Date createDate) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.createDate = createDate;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", createDate=" + createDate
				+ "]";
	}
	
}
