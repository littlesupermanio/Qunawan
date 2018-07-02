package com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestMyBatis() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-test.xml");
        this.userMapper = (UserMapper) ctx.getBean("userMapper");
        String userId = "1";
        User user = this.userMapper.getUser(userId);
        System.out.println(user.getPassword());

    }


    @Test
    public void TestMyBatisGenerator() {
        try {
            generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
