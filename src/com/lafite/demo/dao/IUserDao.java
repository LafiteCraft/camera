package com.lafite.demo.dao;

import com.lafite.demo.entity.User;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface IUserDao {
    User findById(Integer userId) throws Exception;

    List<User> findAll() throws Exception;

    User saveUser(User user) throws Exception;

    List<User> findByName(String userName) throws Exception;

}
