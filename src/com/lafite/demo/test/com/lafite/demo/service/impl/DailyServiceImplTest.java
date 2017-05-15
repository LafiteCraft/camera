package com.lafite.demo.service.impl;

import com.lafite.demo.entity.Daily;
import com.lafite.demo.service.IDailyService;
import com.lafite.util.BeanFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author: LafiteHao
 * @date: create on 2017/5/15
 */
public class DailyServiceImplTest {

    private IDailyService dailyService;

    @Before
    public void setUp() throws Exception {
        this.dailyService = (IDailyService) BeanFactory.getApplicationContext().getBean("dailyService");
    }

    @Test
    public void findById() throws Exception {
        String id = "1";
        Daily daily = this.dailyService.findById(id);
        assertNotNull(daily);
    }

    @Test
    public void findByTitle() throws Exception {
        String title = "test";
        List<Daily> dailyList = this.dailyService.findByTitle(title);
        assertEquals(dailyList.size(), 4);
    }

    @Test
    public void findAll() throws Exception {
        List<Daily> dailies = this.dailyService.findAll();
        assertEquals(dailies.size(), 6);
    }

    @Test
    public void remove() throws Exception {
        String id = "2";
        try {
            this.dailyService.remove(Integer.parseInt(id));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void save() throws Exception {
        Daily daily = new Daily();
        daily.setTitle("this is a test!!!");
        try {
            this.dailyService.save(daily);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void refer() throws Exception {
        Daily daily = new Daily();
        daily.setId(3);
        daily.setContent("This is a test!");
        try {
            this.dailyService.save(daily);
        } catch (Exception e) {
            fail();
        }
    }

}