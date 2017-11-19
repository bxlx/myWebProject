package com.xiaojiyun.www.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xiaojiyun.www.dao.UserDao;
import com.xiaojiyun.www.entity.User;
import com.xiaojiyun.www.exception.DaoException;
import com.xiaojiyun.www.utils.Md5Util;

@Repository("userDao")
public class UserDaoImpl extends GenericHibernateDao<User, Integer> implements UserDao{
	
	public UserDaoImpl(){
		System.out.println("xiaojiyun dao");
	}
	public User findById(Integer id) throws DaoException{
		return super.findById(id);
	}
	
	
	public User findByUserNameAndPassword(String userName, String password) throws DaoException{
		try{
			Criteria criteria = this.getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("userName", userName));
			criteria.add(Restrictions.eq("password", Md5Util.GetMD5Code(password)));
			User result = (User) criteria.uniqueResult();
			return result;
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	public boolean checkExistByUserName(String userName) throws DaoException{
		try{
			Criteria criteria = this.getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("userName", userName));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			if(count != null && count > 0){
				return true;
			}
		}catch(Exception e){
			throw new DaoException(e);
		}
		return false;
	}
}
