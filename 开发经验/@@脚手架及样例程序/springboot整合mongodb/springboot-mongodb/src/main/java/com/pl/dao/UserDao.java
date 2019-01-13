package com.pl.dao;

import java.util.List;

import com.pl.pojo.User;

public interface UserDao {

	void saveUser(User user);

	User findById(Long id);

	List<User> findByAge(int minAge, int maxAge);

	void updateUser(User user);

	void deleteUserById(Long id);
}
