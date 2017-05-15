package com.lafite.demo.service.impl;

import com.lafite.demo.dao.IDailyDao;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class DailyServiceImplTest {

    private IDailyDao dao;
    @Before
    public void setUp() throws Exception {
        this.dao = (IDailyDao) BeanFactory.getApplicationContext().getBean("dailyDao");
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void findByTitle() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}