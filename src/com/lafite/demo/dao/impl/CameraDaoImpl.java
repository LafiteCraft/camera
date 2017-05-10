package com.lafite.demo.dao.impl;

import com.lafite.demo.dao.BaseDao;
import com.lafite.demo.dao.ICameraDao;
import com.lafite.demo.entity.Camera;
import org.springframework.stereotype.Repository;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:15
 **/
@Repository("cameraDao")
public class CameraDaoImpl extends BaseDao<Camera> implements ICameraDao{
}
