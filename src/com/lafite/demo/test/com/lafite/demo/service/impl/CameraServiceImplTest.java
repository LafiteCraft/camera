package com.lafite.demo.service.impl;

import com.lafite.demo.entity.Camera;
import com.lafite.demo.service.ICameraService;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class CameraServiceImplTest {

    private ICameraService cameraService;

    @Before
    public void setUp() throws Exception {
        this.cameraService = (ICameraService) BeanFactory.getApplicationContext().getBean("cameraService");
    }

    @Test
    public void findById() throws Exception {
        int id = 4;
        Camera camera = this.cameraService.findById(id);
        assertNotNull(camera);
    }

    @Test
    public void findByName() throws Exception {
        String name = "测试摄像头";
        List<Camera> cameras = this.cameraService.findByName(name);
        assertEquals(cameras.size(), 1);
    }

    @Test
    public void findAll() throws Exception {
        List<Camera> cameras = this.cameraService.findAll();
        assertEquals(cameras.size(), 4);
    }

    @Test
    public void delete() throws Exception {
        int id = 1;
//        this.cameraService.delete(id);
    }

    @Test
    public void save() throws Exception {
        Camera camera = new Camera();
        camera.setTime(new Date(new java.util.Date().getTime()));
        camera.setCode("lafite-1");
        camera.setUrl("blabla");
        camera.setName("lafite摄像头");
//        this.cameraService.save(camera);
    }

}