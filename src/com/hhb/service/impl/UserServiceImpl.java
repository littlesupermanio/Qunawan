package com.hhb.service.impl;

import com.hhb.dao.UserDao;
import com.hhb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    // 注入Service依赖
    @Autowired
    private UserDao userDao;


}
