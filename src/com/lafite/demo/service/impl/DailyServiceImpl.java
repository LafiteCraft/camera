package com.lafite.demo.service.impl;

import com.lafite.demo.dao.IDailyDao;
import com.lafite.demo.entity.Daily;
import com.lafite.demo.service.IDailyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-11 14:03
 **/
@Service("dailyService")
public class DailyServiceImpl implements IDailyService {

    @Resource(name = "dailyDao")
    private IDailyDao dailyDao;

    @Override
    public Daily findById (String id) throws Exception {
        Daily daily = this.dailyDao.findById (id);
        return daily;
    }

    @Override
    public List<Daily> findByTitle(String title) throws Exception {
        return this.dailyDao.findByName(title);
    }

    @Override
    public List<Daily> findAll() throws Exception {
        return this.dailyDao.findAll();
    }

    @Override
    public void remove(int id) throws Exception {
        this.dailyDao.delete(id);
    }

    @Override
    public void save(Daily daily) throws Exception {
        this.dailyDao.saveOrUpdate(daily);
    }

}
