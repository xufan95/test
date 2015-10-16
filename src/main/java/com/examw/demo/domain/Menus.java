package com.examw.demo.domain;

import java.util.List;

public class Menus {
	private int id;
	private String menuname;
	private String iconCls;
	private List<Submenu> submenuList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<Submenu> getSubmenuList() {
		return submenuList;
	}
	public void setSubmenuList(List<Submenu> submenuList) {
		this.submenuList = submenuList;
	}
	
}
