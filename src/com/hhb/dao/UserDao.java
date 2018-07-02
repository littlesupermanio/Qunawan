package com.hhb.dao;

import com.hhb.entity.User;

public interface UserDao {

    User getUserByCondition(String condition);

    User getUserById(int id);

}
