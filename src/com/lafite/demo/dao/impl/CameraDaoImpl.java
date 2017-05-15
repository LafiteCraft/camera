package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.ICameraDao;
import com.lafite.demo.entity.Camera;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:15
 **/
@Repository("cameraDao")
public class CameraDaoImpl extends BaseDao<Camera> implements ICameraDao{

    @Override
    public Camera findById(int id) throws Exception {
        return this.selectById(id);
    }

    @Override
    public List<Camera> findByName(String name) throws Exception {
        return this.selectByProperty("name", name);
    }

    @Override
    public List<Camera> findAll() throws Exception {
        return this.selectAll();
    }

    @Override
    public void delete(int id) throws Exception {
        Camera camera = new Camera();
        camera.setId(id);
        super.delete(camera);
    }

    @Override
    public void saveOrUpdate(Camera camera) throws Exception {
        this.save(camera);
    }
}
