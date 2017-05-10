package com.lafite.demo.service;

import com.lafite.demo.entity.Login;
import com.lafite.demo.entity.User;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface IUserService {
    User findById(String userId) throws Exception;

    List<User> findAll() throws Exception;

    int vaildate(String loginName, String password) throws Exception;

    /**
     * 用户注册
     * @param login
     * @throws Exception
     */
    void register(Login login) throws Exception;

    /**
     * 取得用户名数量用以判断是否有重复登录名
     * @param login_name
     * @return
     * @throws Exception
     */
    int vaildateLoginName(String login_name) throws Exception;

    /**
     * 保存用户信息
     * @param login
     * @throws Exception
     */
    void fullInfo(Login login) throws Exception;
}
