package com.pl.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pl.model.User;
import com.pl.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)//spring和junit的整合
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})//指定spring配置文件的位置，可以指定多个spring配置文件
public class UnitTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setId(1);
		user.setName("张三");
		user.setBirthday(Calendar.getInstance().getTime());
		user.setAddress("广东深圳");
		this.userService.saveUser(user);
	}

}
