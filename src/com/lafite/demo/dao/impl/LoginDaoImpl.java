package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.ILoginDao;
import com.lafite.demo.entity.Login;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:21
 **/
@Repository("loginDao")
public class LoginDaoImpl extends BaseDao<Login> implements ILoginDao {
    @Override
    public List<Login> selectLogin(String loginName) throws Exception {
        List<Login> loginList = this.selectByProperty("loginName", loginName);
        return loginList;
    }

    @Override
    public void register(Login login) throws Exception {
        this.save(login);
    }
}
