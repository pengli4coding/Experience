package com.pl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pl.dao.UserDao;
import com.pl.pojo.User;
import com.pl.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(User user) {
		this.userDao.saveUser(user);
		
	}

	@Override
	public User findById(Long id) {
		return this.userDao.findById(id);
	}

	@Override
	public List<User> findByAge(int minAge, int maxAge) {
		return this.userDao.findByAge(minAge, maxAge);
	}

	@Override
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	@Override
	public void deleteUserById(Long id) {
		this.userDao.deleteUserById(id);
	}

}
