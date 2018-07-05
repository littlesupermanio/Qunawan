package com.hhb.service.impl;

import com.hhb.dao.UserDao;
import com.hhb.entity.User;
import com.hhb.service.UserService;
import com.hhb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // 注入Service依赖
    @Autowired
    private UserDao userDao;


    @Override
    public boolean checkPhoneAndEmail(String phone, String email, User user) {
        if (!phone.equals(user.getPhone()))
            if (userDao.getUserByCondition(phone)!=null)
                return false;

        if (!email.equals(user.getEmail()))
            if (userDao.getUserByCondition(email)!=null)
                return false;

        return true;
    }

    @Override
    public boolean checkCode(String right_code, String code) {
        if (right_code == null || "".equals(right_code))
            return false;
        right_code = right_code.toUpperCase();
        code = code.toUpperCase();

        if (right_code.equals(code))
            return true;
        else
            return false;
    }

    @Override
    public void registUser(String condition) {
        User user = new User();
        // 如果用户输入的是邮箱号码
        if (Utils.isEmail(condition))
            user.setEmail(condition);
            // 否则用户输入的就是手机号码
        else
            user.setPhone(condition);
        // 设置用户默认密码为123456
        user.setPassword(Utils.toMD5("123456"));
        userDao.save(user);
    }
}
