package com.examw.demo.service;

import com.examw.demo.domain.LoginInfo;

public interface ILoginService {
	public boolean UserVerification(LoginInfo loginInfo);
	public boolean CodeVerification(String sessionCode,String loginCode);
}
