package com.pl.dao.impl;

import org.springframework.stereotype.Repository;

import com.pl.dao.UserDao;
import com.pl.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public void saveUser(User user) {
		System.out.println("保存用户操作，用户信息为："+user);
	}

}
