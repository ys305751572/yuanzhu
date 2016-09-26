package com.smallchill.test.springtest;

import com.smallchill.system.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * 登录测试
 * Created by 叶松 on 2016/9/26.
 */
public class LoginTest{

    @Resource
    private RoleService roleService;

    @Before
    public void before() {
        System.out.println("before");
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/springmvc-servlet.xml");
        roleService.findLastNum("1");
    }

    @Test
    public void loginTest() {
        System.out.println("result:");
    }
}
