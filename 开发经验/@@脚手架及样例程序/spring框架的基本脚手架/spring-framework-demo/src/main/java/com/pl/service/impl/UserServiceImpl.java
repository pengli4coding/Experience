package com.pl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.dao.UserDao;
import com.pl.model.User;
import com.pl.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		this.userDao.saveUser(user);
	}

}
