package com.xiaojiyun.www.service;

import com.xiaojiyun.www.dto.LoginDto;
import com.xiaojiyun.www.exception.ServiceException;

public interface UserService {
	
	LoginDto login(String userName, String password) throws ServiceException;
}
