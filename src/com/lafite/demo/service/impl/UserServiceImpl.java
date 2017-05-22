package com.lafite.demo.service.impl;

import com.lafite.demo.dao.IUserDao;
import com.lafite.demo.entity.User;
import com.lafite.demo.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.Date;
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
    public User register(User user) throws Exception {
        Integer userId = user.getId();
        Class<?> clazz = User.class;
        User old = this.userDao.findById(userId);
        if (old != null) {
            // 合并对象
            Method[] methods = clazz.getMethods();
            int length = methods.length;
            for (int i = 0; i < length; i ++ ) {
                Method method = methods[i];
                String methodNmae = method.getName();
                if (methodNmae.contains("get")) {
                    String fieldName = methodNmae.substring(3);
                    Object result = method.invoke(user);
                    if (result != null) {
                        if (method.getReturnType() == String.class) {
                            clazz.getMethod("set" + fieldName, String.class).invoke(old, (String) result);
                        } else if (method.getReturnType() == Integer.class){
                            clazz.getMethod("set" + fieldName, Integer.class).invoke(old, (Integer) result);
                        } else if (method.getReturnType() == Character.class){
                            clazz.getMethod("set" + fieldName, Character.class).invoke(old, (Character) result);
                        } else if (method.getReturnType() == Date.class){
                            clazz.getMethod("set" + fieldName, Date.class).invoke(old, (Date) result);
                        }
                    }
                }
            }
            user = old;
        }
        this.userDao.saveUser(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public int vaildateUserName(String userName) throws Exception {
        List<User> userList = this.userDao.findByName(userName);
        userList = userList == null ? new ArrayList<User>(0) : userList;
        return userList.size();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUserByType(String type) throws Exception {
        List<User> userList = this.userDao.findByType(type);
        return userList;
    }
}
