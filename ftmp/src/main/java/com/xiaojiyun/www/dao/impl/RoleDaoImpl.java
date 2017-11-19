package com.xiaojiyun.www.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaojiyun.www.dao.RoleDao;
import com.xiaojiyun.www.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends GenericHibernateDao<Role, Integer> implements RoleDao{
	
	public RoleDaoImpl(){
		System.out.println("xiaojiyun dao");
	}
	
	
}
