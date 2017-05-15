package com.lafite.demo.service.impl;

import com.lafite.demo.dao.IUserDao;
import com.lafite.demo.entity.User;
import com.lafite.demo.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:41
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User findById(String userId) throws Exception {
        User user = this.userDao.findById(Integer.parseInt(userId));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws Exception {
        List<User> userList = this.userDao.findAll();
        return userList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> vaildate(String userName, String password) throws Exception {
        List<User> userList = this.userDao.findByName(userName);
        return userList;
    }

    @Override
    @Transactional
    public void register(User user) throws Exception {
        this.userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public int vaildateUserName(String userName) throws Exception {
        List<User> userList = this.userDao.findByName(userName);
        userList = userList == null ? new ArrayList<>() : userList;
        return userList.size();
    }
}
