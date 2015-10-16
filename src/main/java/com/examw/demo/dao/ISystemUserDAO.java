package com.examw.demo.dao;


import java.util.List;

import com.examw.demo.domain.SystemUser;

public interface ISystemUserDAO {
	public List<SystemUser> findSystemUser(SystemUser SystemUser);
}
