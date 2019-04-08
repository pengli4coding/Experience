package com.pl.service.impl;

import com.pl.dao.UserDao;
import com.pl.model.User;
import com.pl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        userDao.addUser(user);
        return true;
    }
}
