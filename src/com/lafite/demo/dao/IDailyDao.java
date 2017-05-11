package com.lafite.demo.dao;

import com.lafite.demo.entity.Daily;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface IDailyDao {
    Daily findById(String id) throws Exception;

    List<Daily> findAll() throws Exception;

    List<Daily> findByName(String name) throws Exception;

    void delete(int id) throws Exception;

    void saveOrUpdate(Daily daily) throws Exception;
}
