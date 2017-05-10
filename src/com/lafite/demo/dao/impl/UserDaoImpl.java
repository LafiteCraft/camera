package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.IUserDao;
import com.lafite.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 13:35
 **/
@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements IUserDao {
    @Override
    public User findById(Integer userId) throws Exception {
        return super.selectById(userId);
    }

    @Override
    public List<User> findAll() throws Exception {
        return super.selectAll();
    }

    @Override
    public User saveUser(User user) throws Exception {
        this.save(user);
        return user;
    }
}
