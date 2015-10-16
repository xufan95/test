package com.examw.demo.dao;

import java.util.List;

import com.examw.demo.domain.PageBean;
import com.examw.demo.domain.Stu;

public interface IStuDAO {
	public List<Stu> findStus(Stu info);
	public int selectCount();
	public List<Stu> selectstus(PageBean pageBean);
	public void addstu(Stu stu);
	public void upstu(Stu stu);
	public void delstu(long id);
}
