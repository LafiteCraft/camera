package com.lafite.demo.service.impl;

import com.lafite.demo.dao.ICameraDao;
import com.lafite.demo.entity.Camera;
import com.lafite.demo.service.ICameraService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-11 15:08
 **/
public class CameraServiceImpl implements ICameraService{

    @Resource(name = "cameraDao")
    private ICameraDao cameraDao;

    @Override
    public Camera findById(int id) throws Exception {
        return this.cameraDao.findById(id);
    }

    @Override
    public List<Camera> findByName(String name) throws Exception {
        return this.cameraDao.findByName(name);
    }

    @Override
    public List<Camera> findAll() throws Exception {
        return this.cameraDao.findAll();
    }

    @Override
    public void delete(int id) throws Exception {
        this.cameraDao.delete(id);
    }

    @Override
    public void save(Camera camera) throws Exception {
        this.cameraDao.saveOrUpdate(camera);
    }
}
