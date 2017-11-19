package com.xiaojiyun.www.exception;

import com.xiaojiyun.www.common.CommonConstants;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -211047166313331926L;

	public ServiceException(String message){
		super(CommonConstants.SERVICE_EXCEPTION+message);
	}
	
	public ServiceException(Exception e){
		super(e);
	}
}
