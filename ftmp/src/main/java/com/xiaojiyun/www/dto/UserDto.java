package com.xiaojiyun.www.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {
	
	private Integer id;
	 
	@NotEmpty(message = "LOGIN_USER_NAME_NOT_EMPTY")
	private String userName; 
	
	@NotEmpty(message = "LOGIN_PASSWORD_NOT_EMPTY")
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Integer gender;
	
	private Boolean isRemember;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Boolean getIsRemember() {
		return isRemember;
	}

	public void setIsRemember(Boolean isRemember) {
		this.isRemember = isRemember;
	}
	
	
}
