package com.examw.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examw.demo.dao.ISystemUserDAO;
import com.examw.demo.domain.LoginInfo;
import com.examw.demo.domain.SystemUser;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	ISystemUserDAO iSystemUserDAO;    
	
	public boolean UserVerification(LoginInfo loginInfo) {
		SystemUser user = new SystemUser();
		user.setUsername(loginInfo.getUsername());
		user.setPassword(loginInfo.getPassword());
		List<SystemUser> systemUser = iSystemUserDAO.findSystemUser(user);
		if (systemUser!=null&&systemUser.size()>0){
			return true;
		}
		return false;
	}

	public boolean CodeVerification(String sessionCode, String loginCode) {
		if(sessionCode.equalsIgnoreCase(loginCode)){
			return true;
		}
		return false;
	}

}
