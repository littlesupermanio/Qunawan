package com.test;

import com.hhb.dao.CityDao;
import com.hhb.dao.UserDao;
import com.hhb.entity.City;
import com.hhb.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserTest extends BaseTest{
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void TestMyBatisQuery() {
        int cityId = 77;
        City city = cityDao.getCityById(cityId);
        System.out.println(city);
    }

    @Test
    public void UserDaoTest() {
        int userId = 1;
        User user = userDao.getUserById(userId);
        user.setName("张小飞");
        user.setCity(cityDao.getCityById(101));
        userDao.update(user);
        System.out.println(user);
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指向逆向工程配置文件
        File configFile = new File("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);

    }


}
