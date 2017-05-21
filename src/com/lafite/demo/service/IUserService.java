package com.lafite.demo.service;

import com.lafite.demo.entity.User;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface IUserService {
    User findById(String userId) throws Exception;

    List<User> findAll() throws Exception;

    List<User> vaildate(String userName, String password) throws Exception;

    /**
     * 用户注册
     * @param user
     * @throws Exception
     */
    void register(User user) throws Exception;

    /**
     * 取得用户名数量用以判断是否有重复登录名
     * @param user_name
     * @return
     * @throws Exception
     */
    int vaildateUserName(String user_name) throws Exception;

    /**
     * 通过用户类型查找用户信息
     * @return
     * @throws Exception
     */
    List<User> findUserByType(String type) throws Exception;
}
