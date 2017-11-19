package com.xiaojiyun.www.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity(name="menu")
public class Menu {
	
	private Integer id;
	
	private String name;
	
	private String url;
	
	private Integer orderNum;
	
	private String icon;
	
	private Menu parent;
	
	private Set<Menu> childrens;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name",nullable=false, length=32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="url",nullable=true, length=256)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="orderNum",nullable=false)
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name="icon",nullable=true, length=32)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@ManyToOne// 指定多对一关系
	@JoinColumn(name="parentId")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
 
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parent")	
	@OrderBy("orderNum")
	public Set<Menu> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Menu> childrens) {
		this.childrens = childrens;
	}

	                               
	
	
	
}
