package com.pl.dao.impl;

import com.pl.dao.UserDao;
import com.pl.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger log = LogManager.getLogger();

    @Override
    public boolean addUser(User user) {
        log.info("保存用户成功，用户为：" + user.toString());
        return true;
    }

}
