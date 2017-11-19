package com.xiaojiyun.www.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.xiaojiyun.www.dao.UserDao;
import com.xiaojiyun.www.dto.LoginDto;
import com.xiaojiyun.www.dto.MenuDto;
import com.xiaojiyun.www.entity.Menu;
import com.xiaojiyun.www.entity.User;
import com.xiaojiyun.www.exception.DaoException;
import com.xiaojiyun.www.exception.ServiceException;
import com.xiaojiyun.www.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao ;
	
	
	public LoginDto login(String userName, String password) throws ServiceException{
		try {
			if(this.userDao.checkExistByUserName(userName)){
				User user = this.userDao.findByUserNameAndPassword(userName, password);
				if(user != null){
					LoginDto loginDto = new LoginDto();
					BeanUtils.copyProperties(user, loginDto.getUser());
					BeanUtils.copyProperties(user.getRole(), loginDto.getRole());
					for(Menu menu : user.getRole().getMenus()){
						if(menu.getParent() == null){
							MenuDto menuDto = new MenuDto();
							BeanUtils.copyProperties(menu, menuDto);
							if(menu.getChildrens() != null && menu.getChildrens().size() > 0){
								List<MenuDto> childrens = new ArrayList<MenuDto>();
								for(Menu children : menu.getChildrens()){
									MenuDto childrenDto = new MenuDto();
									BeanUtils.copyProperties(children, childrenDto);
									childrens.add(childrenDto);
								}
								menuDto.setChildrens(childrens);	
							}
							loginDto.getPermissionMenus().add(menuDto);
						}
					}
					return loginDto;
				}else{
					return new LoginDto("password","密码错误");
				}
			}else{
				return new LoginDto("userName", "用户不存在");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
