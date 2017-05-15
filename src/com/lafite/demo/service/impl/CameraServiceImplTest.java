package com.lafite.demo.service.impl;

import com.lafite.demo.dao.ICameraDao;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class CameraServiceImplTest {
    private ICameraDao dao;
    @Before
    public void setUp() throws Exception {
        this.dao = (ICameraDao) BeanFactory.getApplicationContext().getBean("cameraDao");
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void findByName() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}