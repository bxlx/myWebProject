package com.xiaojiyun.www.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.xiaojiyun.www.dao.GenericDao;
import com.xiaojiyun.www.exception.DaoException;

public abstract class GenericHibernateDao<T, ID extends Serializable> implements GenericDao<T, ID> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> persistentClass;
	
	protected Session session;
	
	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		//get persistentClass
		Type class1 = getClass();
		while (!(class1 instanceof ParameterizedType) && class1 != null) {
			class1 = ((Class<?>) class1).getGenericSuperclass();
		}
		if (class1 != null) {
			Type[] parametersType = ((ParameterizedType) class1).getActualTypeArguments();
			if (parametersType[0] instanceof Class) {
				this.persistentClass = (Class<T>) parametersType[0];
			} else if (parametersType[0] instanceof TypeVariable) {
				Type tv = parametersType[0];
				// This part allow to have a DAO which inherit GenericDAO using
				// generic .
				// Sample : MyBeanDAO<T extends MyBean> extends GenericDao<T,
				// ID>. Returns Class<MyBean>
				// For all inherits DAO, returns the good class
				// Sample : MySecondDAO extends MyBeanDAO<MySecondBean>. Returns
				// Class<MySecondBean>
				while (tv instanceof TypeVariable) {
					tv = ((TypeVariable<?>) tv).getBounds()[0];
				}
				this.persistentClass = (Class<T>) tv;
			}
		}
		/*//get session
		try {
			session = getSession();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	protected Session getSession() throws DaoException {
		if (sessionFactory != null) {
			try {
				return sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
				//TO DO log
				throw new DaoException(e);
			}
		}
		// if sessionFactory is null
		String msg = "SessionFactory is null in GenericHibernateDao - you must inject LocalSessionFactoryBean to use this class";
		//log.error(msg);
		throw new DaoException(msg);
	}

	protected void checkWriteOperationAllowed() throws InvalidDataAccessApiUsageException, DaoException {
		if (getSession().getFlushMode().lessThan(FlushMode.COMMIT)) {
			throw new InvalidDataAccessApiUsageException(
					"Write operations are not allowed in read-only mode (FlushMode.MANUAL): Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.");
		}
	}
	
	protected boolean refreshWhenCreateOrUpdate() {
		return false;
	}
	
	public T create(T entity) throws DaoException {
		checkWriteOperationAllowed();
		try {
			getSession().save(entity);
			if (refreshWhenCreateOrUpdate()) {
				//this.hibernateTemplate.refresh(entity);
				getSession().flush();
				getSession().clear();
			}
		} catch (HibernateException e) {
			// TO DO log
			throw new DaoException(e);
		}
		return entity;
	}
	

	public T createOrUpdate(T entity) throws DaoException {
		checkWriteOperationAllowed();
		try {
			getSession().saveOrUpdate(entity);
			if (refreshWhenCreateOrUpdate()) {
				// getHibernateTemplate().refresh(entity);
				getSession().flush();
				getSession().clear();
			}
		} catch (HibernateException e) {
			
			throw new DaoException(e);
		}
		return entity;
	}

	
	public T update(T entity) throws DaoException {
		checkWriteOperationAllowed();
		try {
			getSession().update(entity);
			if (refreshWhenCreateOrUpdate()) {
				// getHibernateTemplate().refresh(entity);
				getSession().flush();
				getSession().clear();
			}
		} catch (HibernateException e) {

			throw new DaoException(e);
		}
		return entity;
	}

	
	public T merge(T entity) throws DaoException {
		checkWriteOperationAllowed();
		try {
			getSession().merge(entity);
			if (refreshWhenCreateOrUpdate()) {
				// getHibernateTemplate().refresh(entity);
				getSession().flush();
				getSession().clear();
			}
		} catch (HibernateException e) {

			throw new DaoException(e);
		}
		return entity;
	}

	
	public void delete(T entity) throws DaoException {
		checkWriteOperationAllowed();
		try {
			getSession().delete(entity);
		} catch (HibernateException e) {

			throw new DaoException(e);
		}
	}

	
	public void deleteById(ID id) throws DaoException {
		checkWriteOperationAllowed();
		try {
			T entity = findById(id);
			getSession().delete(entity);
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public Collection<T> findAll() throws DaoException {
		Collection<T> entities = null;
		try {
			Query query = getSession().createQuery("from " + getPersistentClass().getName());
			entities = query.list();
		} catch (HibernateException e) {
			
			throw new DaoException(e);
		}
		return entities;
	}

	
	@SuppressWarnings("unchecked")
	public T findById(ID id) throws DaoException {
		T entity = null;
		try {
			entity = (T) getSession().get(getPersistentClass(), id);
		} catch (HibernateException e) {

			throw new DaoException(e);
		}
		return entity;
	}


	@SuppressWarnings("unchecked")
	public T loadById(ID id) throws DaoException {
		T entity = null;
		try {
			entity = (T) getSession().load(getPersistentClass(), id);
		} catch (HibernateException e) {

			throw new DaoException(e);
		}
		return entity;
	}
	
	protected void addCriteriaOrList(Criteria criteria, List<SimpleExpression> listOrExpressions) {
		if (listOrExpressions != null && listOrExpressions.size() != 0) {
			if (listOrExpressions.size() == 1) {
				criteria.add(listOrExpressions.get(0));
			} else if (listOrExpressions.size() > 1) {
				LogicalExpression previousOrExp = null;
				for (int i = 1; i < listOrExpressions.size(); i++) {
					if (previousOrExp == null) {
						previousOrExp = Restrictions.or(listOrExpressions.get(i - 1), listOrExpressions.get(i));
					} else {
						previousOrExp = Restrictions.or(previousOrExp, listOrExpressions.get(i));
					}
				}
				criteria.add(previousOrExp);
			}
		}
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}


	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}


	public void setSession(Session session) {
		this.session = session;
	}
	
	
}
