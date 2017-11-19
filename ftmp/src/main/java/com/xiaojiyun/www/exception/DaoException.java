package com.xiaojiyun.www.exception;

import com.xiaojiyun.www.common.CommonConstants;

public class DaoException extends Exception {

	private static final long serialVersionUID = -2331891249664645444L;
	
	
	public DaoException(String message){
		super(CommonConstants.DAO_EXCEPTION+message);
	}
	
	public DaoException(Exception e){
		super(e);
	}
}
