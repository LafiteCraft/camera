package com.lafite.demo.dao;

import com.lafite.demo.entity.Login;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface ILoginDao {
    List<Login> selectLogin(String loginName) throws Exception;

    /**
     * 用户注册
     * @param login
     * @throws Exception
     */
    void register(Login login) throws Exception;
}
