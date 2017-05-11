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
    public Login findById(String userId) throws Exception {
        Login login = this.loginDao.findById(userId);
        return login;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Login> findAll() throws Exception {
        List<Login> loginList = this.loginDao.findAll();
        return loginList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Login> vaildate(String loginName, String password) throws Exception {
        List<Login> loginList = this.loginDao.selectLogin(loginName);
        return loginList;
    }

    @Override
    @Transactional
    public void register(Login login) throws Exception {
        User user = login.getUser();
        this.userDao.saveUser(user);
        login.setUser(user);
        this.loginDao.register(login);
    }

    @Override
    @Transactional(readOnly = true)
    public int vaildateLoginName(String login_name) throws Exception {
        List<Login> loginList = this.loginDao.selectLogin(login_name);
        loginList = loginList == null ? new ArrayList<>() : loginList;
        return loginList.size();
    }
}
