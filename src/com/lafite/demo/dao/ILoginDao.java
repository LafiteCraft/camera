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

    /**
     * 保存
     * @param login
     * @throws Exception
     */
    void fullInfo(Login login) throws Exception;

    /**
     * 全部用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    Login findById(String userId) throws Exception;

    List<Login> findAll() throws Exception;
}
