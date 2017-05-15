package com.lafite.demo.service.impl;

import com.lafite.demo.dao.IDailyDao;
import com.lafite.demo.entity.Daily;
import com.lafite.demo.service.IDailyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Daily findById (String id) throws Exception {
        Daily daily = this.dailyDao.findById (id);
        return daily;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Daily> findByTitle(String title) throws Exception {
        return this.dailyDao.findByName(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Daily> findAll() throws Exception {
        return this.dailyDao.findAll();
    }

    @Override
    @Transactional()
    public void remove(int id) throws Exception {
        this.dailyDao.delete(id);
    }

    @Override
    @Transactional()
    public void save(Daily daily) throws Exception {
        this.dailyDao.saveOrUpdate(daily);
    }

    @Override
    public void refer(Daily daily) throws Exception {
        this.dailyDao.saveOrUpdate(daily);
    }

}
