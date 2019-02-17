package com.pl.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.pl.bean.Annimal;
import com.pl.bean.User;
import com.pl.dao.BaseDao;
import com.pl.dao.impl.BaseDaoImpl;

public class UnitTest {

	@Test
	public void testDelete() {
		BaseDao dao = new BaseDaoImpl();
		dao.delete(6, User.class);
	}
	
	@Test
	public void testDelete1() {
		BaseDao dao = new BaseDaoImpl();
		User user = new User(2L,"熊大","男","37",new Date());
		dao.delete(user);
	}
	
	@Test
	public void testGet() {
		BaseDao dao = new BaseDaoImpl();
		User user = dao.get(3,User.class);
		System.out.println(user);
	}
	
	@Test
	public void testGet1() {
		BaseDao dao = new BaseDaoImpl();
		Annimal animal = dao.get(2,Annimal.class);
		System.out.println(animal);
	}
	
	@Test
	public void testFindAll() {
		BaseDao dao = new BaseDaoImpl();
		List<User> list = dao.findAll(User.class);
		for(User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testFindAll1() {
		BaseDao dao = new BaseDaoImpl();
		List<Annimal> list = dao.findAll(Annimal.class);
		for(Annimal annimal : list) {
			System.out.println(annimal);
		}
	}
	
	@Test
	public void testQuery() {
		BaseDao dao = new BaseDaoImpl();
		List<User> list = dao.query(User.class, "select * from tb_user where age = ?", new Object[]{29});
		for(User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testSave() {
		BaseDao dao = new BaseDaoImpl();
		User user = new User();
		user.setName("黄雅尔");
		user.setSex("男");
		user.setAge("28");
		user.setCreateDate(new Date());
		Serializable id = dao.save(user);
		System.out.println(id);
	}
	
	@Test
	public void testSave1() {
		BaseDao dao = new BaseDaoImpl();
		Annimal animal = new Annimal();
		animal.setName("大肥猫");
		animal.setFood("小鱼干");
		animal.setMasterName("小立立");
		Serializable id = dao.save(animal);
		System.out.println(id);
	}
	
	@Test
	public void testSave2() {
		BaseDao dao = new BaseDaoImpl();
		Annimal animal = new Annimal();
		animal.setName("小橘猫");
		animal.setFood("鱿鱼丝");
		animal.setMasterName("小立立");
		Serializable id = dao.save(animal);
		System.out.println(id);
	}

}
