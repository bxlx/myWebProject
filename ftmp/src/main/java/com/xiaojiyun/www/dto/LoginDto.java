package com.xiaojiyun.www.dto;

import java.util.ArrayList;
import java.util.List;

public class LoginDto {
	
	private String errorMsg;
	
	private String field;
	
	private UserDto user;
	
	private RoleDto role;
	
	private List<MenuDto> permissionMenus;
	
	public LoginDto(){
		user = new UserDto();
		role = new RoleDto();
		permissionMenus = new ArrayList<MenuDto>();
	}

	public LoginDto(String field, String errorMsg){
		this.field = field;
		this.errorMsg = errorMsg;
	}

	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public List<MenuDto> getPermissionMenus() {
		return permissionMenus;
	}

	public void setPermissionMenus(List<MenuDto> permissionMenus) {
		this.permissionMenus = permissionMenus;
	}

	

	

	
	
	
}
