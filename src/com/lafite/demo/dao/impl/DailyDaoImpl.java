package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.IDailyDao;
import com.lafite.demo.entity.Daily;
import org.springframework.stereotype.Repository;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:20
 **/
@Repository("dailyDao")
public class DailyDaoImpl extends BaseDao<Daily> implements IDailyDao {
}
