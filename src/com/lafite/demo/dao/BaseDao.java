package com.lafite.demo.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @ClassName: BaseDao
 * @Description: TODO
 * @author: Haozhj
 * @date: 2016年3月15日 下午5:07:45
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDao<T extends Serializable> extends HibernateDaoSupport {

	protected Session session;
	protected Class<T> entityClass;
	protected Query query;
	protected Criteria criteria;
	
	protected void setEntityClass(Class type){
		this.entityClass = type;
	}

	public BaseDao() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	@Resource(name = "sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	/**
	 * @Title: openSession
	 * @Description: TODO
	 * @Author Haozhj
	 * @throws Exception
	 */
	protected void openSession() throws Exception {
		this.session = this.getSessionFactory().getCurrentSession();
	}

	/**
	 * <p>通过ID查询</p>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected T selectById(Object id) throws Exception {
		openSession();
		T entity = null;
		if (id instanceof String) {
			entity = (T)this.session.get(entityClass, (String) id);
		} else if (id instanceof Integer) {
			entity = (T)this.session.get(entityClass, (Integer) id);
		}
		return entity;
	}

	/**
	 * <p>通过某个字段查询</p>
	 * @param propertyName
	 * @param value
	 * @return
	 */
	protected List<T> selectByProperty(String propertyName, Object value) throws Exception {
		createCriteria(entityClass);
		if (value instanceof String) {
			this.criteria.add(Restrictions.eq(propertyName, (String) value));
		} else if (value instanceof Integer) {
			this.criteria.add(Restrictions.eq(propertyName, (Integer) value));
		} else if (value instanceof Double) {
			this.criteria.add(Restrictions.eq(propertyName, (Double) value));
		}
		return this.criteria.list();
	}

	/**
	 * <p>全查询</p>
	 * @return
	 * @throws Exception
	 */
	protected List<T> selectAll() throws Exception {
		openSession();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();
	}

	/**
	 * <p>保存数据</p>
	 * @param entity
	 * @throws Exception
	 */
	protected void save(T entity) throws Exception {
		openSession();
		this.session.saveOrUpdate(entity);
	}

	/**
	 * @Title: delete
	 * @Description: TODO
	 * @Author Haozhj
	 * @param enetiy
	 * @throws Exception
	 */
	protected void delete(T enetiy) throws Exception {
		openSession();
		this.session.delete(enetiy);
	}

	/**
	 * <p>插入数据</p>
	 * @param entity
	 * @throws Exception
	 */
	protected void insert(T entity) throws Exception {
		openSession();
		this.session.save(entity);
	}

	/**
	 * <p>更新数据</p>
	 * @param entity
	 * @throws Exception
	 */
	protected void update(T entity) throws Exception {
		openSession();
		this.session.update(entity);
	}

	/**
	 * @Title: createQuery
	 * @Description: TODO
	 * @Author Haozhj
	 * @param hql
	 * @throws Exception
	 */
	protected void createQuery(String hql) throws Exception {
		openSession();
		this.query = this.session.createQuery(hql);
	}

	/**
	 * @Title: createSQLQuery
	 * @Description: TODO
	 * @Author Haozhj
	 * @param sql
	 * @throws Exception
	 */
	protected void createSQLQuery(String sql) throws Exception {
		openSession();
		this.query = this.session.createSQLQuery(sql);
	}

	/**
	 * @Title: createQuery
	 * @Description: TODO
	 * @Author Haozhj
	 */
	protected void createCriteria(Class type) throws Exception {
		openSession();
		this.criteria = this.session.createCriteria(type);
	}
	
	/** 
	 * @Title: createCriteria 
	 * @Description: TODO
	 * @Author Haozhj
	 * @throws Exception 
	 */
	protected void createCriteria() throws Exception {
		openSession();
		this.criteria = this.session.createCriteria(entityClass);
	}

}
