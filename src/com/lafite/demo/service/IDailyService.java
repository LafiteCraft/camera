package com.lafite.demo.service;

import com.lafite.demo.entity.Daily;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/11
 */
public interface IDailyService {
    Daily findById(String id) throws Exception;

    List<Daily> findByTitle(String title) throws Exception;

    List<Daily> findAll() throws Exception;

    void remove(int id) throws Exception;

    void save(Daily daily) throws Exception;
}
