package com.examw.demo.domain;

public class PageBean extends Stu{
	private String order,sort;
	private Integer page,rows;
	//private int page; // 第几页
	//private int rows; // 每页的记录数
	//private int start; // 起始页
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	/*public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}*/
	/*public int getStart() {
		return (page-1)*rows;
	}
	public void setStart(int start) {
		this.start = start;
	}*/
	
}
