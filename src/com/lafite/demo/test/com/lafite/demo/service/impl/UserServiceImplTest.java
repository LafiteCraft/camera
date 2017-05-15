package com.lafite.demo.service.impl;

import com.google.gson.Gson;
import com.lafite.demo.base.UserType;
import com.lafite.demo.entity.User;
import com.lafite.demo.service.IUserService;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class UserServiceImplTest {

    private IUserService userService;
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        this.userService = (IUserService) BeanFactory.getApplicationContext().getBean("userService");
        this.gson = new Gson();
    }

    @Test
    public void findById() throws Exception {
        String id = "1";
        User user = this.userService.findById(id);
        assertNotNull(user);
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = this.userService.findAll();
        assertEquals(userList.size(), 11);
    }

    @Test
    public void vaildate() throws Exception {
        String userName = "lafite";
        String password = "lafite123";
        List<User> userList = this.userService.vaildate(userName, password);
        assertEquals(userList.get(0).getLoginName(), "lafite");
    }

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setLoginName("Yika");
        user.setLoginName("yika");
        user.setPassword("Yika123");
        user.setType(UserType.SUPER_ADMIN.toString());

        this.userService.register(user);
    }

    @Test
    public void vaildateUserName() throws Exception {
        int result = this.userService.vaildateUserName("lafite");
        assertEquals(result, 1);
    }

}