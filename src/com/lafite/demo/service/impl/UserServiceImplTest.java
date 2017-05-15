package com.lafite.demo.service.impl;

import com.lafite.demo.dao.ILoginDao;
import com.lafite.demo.dao.IUserDao;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class UserServiceImplTest {

    private IUserDao userDao;
    private ILoginDao loginDao;

    @Before
    public void setUp() throws Exception {
        this.userDao = (IUserDao) BeanFactory.getApplicationContext().getBean("userDao");
        this.loginDao = (ILoginDao) BeanFactory.getApplicationContext().getBean("loginDao");
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void vaildate() throws Exception {
    }

    @Test
    public void register() throws Exception {
    }

    @Test
    public void vaildateLoginName() throws Exception {
    }

}