package com.hhb.dao;

import com.hhb.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User getUserByCondition(String condition);

    User getUserById(int id);



    /**
     * 将用户对象存储到数据库
     * 注册时只需要填写手机号或者邮箱地址
     * 密码是默认的123456
     * 所以只需插入这三个字段
     * @param user
     */
    void save(User user);

    /**
     * 更新或保存用户对象
     * @param user
     */
    void update(User user);

}
