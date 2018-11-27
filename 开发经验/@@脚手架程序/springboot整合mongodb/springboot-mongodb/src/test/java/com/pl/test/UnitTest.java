package com.pl.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pl.pojo.Person;
import com.pl.pojo.User;
import com.pl.service.PersonService;
import com.pl.service.UserService;
import com.pl.useBasicQuery.BasicQueryUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PersonService personService;

	@Test
	public void saveUser() {
		for(int i=1;i<=20;i++) {
			User user = new User();
			user.setName("张三"+i);
			user.setAge(10+i);
			user.setId(1L+i-1);
			this.userService.saveUser(user);
		}
		
	}
	
	@Test
	public void findById() {
		User user = this.userService.findById(1L);
		System.out.println(user);
	}
	
	@Test
	public void findByAge() {
		List<User> userList = this.userService.findByAge(20, 30);
		System.out.println(userList);
	}
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setName("李四");
		user.setAge(22);
		user.setId(1L);
		
		this.userService.updateUser(user);
	}
	
	@Test
	public void deleteUserById() {
		this.userService.deleteUserById(1L);
	}
	
	@Test
	public void savePerson() {
		for(int i=1;i<=20;i++) {
			Person person = new Person();
			person.setName("张三"+i);
			person.setIdCard(String.valueOf(i));
			person.setAddress("广东深圳"+i);
			this.personService.savePerson(person);
		}
		
	}
	
	@Test
	public void objectIdQuery() {
		Person person = BasicQueryUtil.objectIdQuery("5bfc9534a972ed0ac0a0361e", "t_person");
		System.out.println(person);
		
	}
	

}
