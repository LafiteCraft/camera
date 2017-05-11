package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.IDailyDao;
import com.lafite.demo.entity.Daily;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:20
 **/
@Repository("dailyDao")
public class DailyDaoImpl extends BaseDao<Daily> implements IDailyDao {
    @Override
    public Daily findById(String id) throws Exception {
        return this.selectById(id);
    }

    @Override
    public List<Daily> findAll() throws Exception {
        return this.selectAll();
    }

    @Override
    public List<Daily> findByName(String name) throws Exception {
        createCriteria(Daily.class);
        this.criteria.add(Restrictions.like("title", name));
        return criteria.list();
    }

    @Override
    public void delete(int id) throws Exception {
        this.delete(id);
    }

    @Override
    public void saveOrUpdate(Daily daily) throws Exception {
        this.save(daily);
    }
}
