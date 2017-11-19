package com.xiaojiyun.www.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.xiaojiyun.www.dao.RoleDao;
import com.xiaojiyun.www.service.RoleService;

@Repository("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao ;
	
	
	
}
