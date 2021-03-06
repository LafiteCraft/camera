package com.lafite.demo.dao;

import com.lafite.demo.entity.Camera;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/9
 */
public interface ICameraDao {

    Camera findById(int id) throws Exception;

    List<Camera> findByName(String name) throws Exception;

    List<Camera> findAll() throws Exception;

    void delete(int id) throws Exception;

    void saveOrUpdate(Camera camera) throws Exception;
}
