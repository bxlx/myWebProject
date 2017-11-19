package com.xiaojiyun.www.dto;

import java.util.List;

public class MenuDto {
	
	private Integer id;
	
	private String name;
	
	private String url;
	
	private Integer orderNum;
	
	private String icon;
	
	private MenuDto parent;
	
	private List<MenuDto> childrens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuDto getParent() {
		return parent;
	}

	public void setParent(MenuDto parent) {
		this.parent = parent;
	}
                                   
	public List<MenuDto> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<MenuDto> childrens) {
		this.childrens = childrens;
	}
	
	
}
