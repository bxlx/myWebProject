package com.xiaojiyun.www.dao;

import com.xiaojiyun.www.entity.User;
import com.xiaojiyun.www.exception.DaoException;

public interface UserDao extends GenericDao<User, Integer>{
	User findById(Integer id) throws DaoException;
	User findByUserNameAndPassword(String userName, String password) throws DaoException;
	boolean checkExistByUserName(String userName) throws DaoException;
}
