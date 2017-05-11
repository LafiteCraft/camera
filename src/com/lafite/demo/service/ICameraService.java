package com.lafite.demo.service;

import com.lafite.demo.entity.Camera;

import java.util.List;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/11
 */
public interface ICameraService {

    Camera findById (int id) throws Exception;

    List<Camera> findByName(String name) throws Exception;

    List<Camera> findAll () throws Exception;

    void delete (int id) throws Exception;

    void save (Camera camera) throws Exception;

}
