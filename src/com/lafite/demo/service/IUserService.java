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

    /**
     * 登陆验证
     * 0 -- 代码异常
     * 1 -- 登陆成功
     * 2 -- 用户名不存在
     * 3 -- 密码错误
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    int vaildate(String loginName, String password) throws Exception;

}
