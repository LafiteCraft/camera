package com.lafite.demo.service.impl;

import com.lafite.demo.dao.ILoginDao;
import com.lafite.demo.dao.IUserDao;
import com.lafite.demo.entity.Login;
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
    @Resource(name = "loginDao")
    private ILoginDao loginDao;

    @Override
    @Transactional(readOnly = true)
    public User findById(String userId) throws Exception {
        User user = this.userDao.findById(Integer.parseInt(userId));
        return user;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = this.userDao.findAll();
        return userList;
    }

    @Override
    public int vaildate(String loginName, String password) throws Exception {
        int result = 0;
        List<Login> loginList = this.loginDao.selectLogin(loginName);
        if (loginList.size() == 0) {
            result = 2;
        } else if (loginList.get(0).getPassword().equals(password)) {
            result = 1;
        } else {
            result = 3;
        }
        return result;
    }

    @Override
    @Transactional
    public void register(Login login) throws Exception {
        this.loginDao.register(login);
    }

    @Override
    public int vaildateLoginName(String login_name) throws Exception {
        List<Login> loginList = this.loginDao.selectLogin(login_name);
        loginList = loginList == null ? new ArrayList<>() : loginList;
        return loginList.size();
    }

    @Override
    @Transactional
    public void fullInfo(Login login) throws Exception {
        User user = this.userDao.saveUser(login.getUser());
        login.setUser(user);
        this.loginDao.fullInfo(login);
    }
}
