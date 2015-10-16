package com.examw.demo.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.demo.domain.LoginInfo;
import com.examw.demo.service.ILoginService;
import com.examw.demo.util.SessionListener;
import com.examw.model.Json;

@Controller
@RequestMapping("verification")
public class LoginController {
	@Autowired
	@Qualifier("loginService")
	private ILoginService loginService;
	
	@RequestMapping(params = "login")
	@ResponseBody
	public Json  userVerification(HttpServletRequest request,LoginInfo loginInfo){
		Json result = new Json();
			//获取HttpSession中的验证码。
			String code = (String)request.getSession().getAttribute("rand");
			if (loginService.CodeVerification(code, loginInfo.getCode())) {
				if (loginService.UserVerification(loginInfo)) {
					result.setSuccess(true);
					result.setMsg("验证成功");
					if (isLoginUser(loginInfo.getUsername(),request.getSession())) {
						result.setSuccess(false);
						result.setData(loginInfo.getUsername());
						result.setMsg("用户已在其他地方登入,是否要登陆?");
					}else{
						request.getSession().setAttribute("username", loginInfo.getUsername());
					}
				}else{
					result.setSuccess(false);
					result.setMsg("用户名或密码错误!");
				}	
			}else {
				result.setSuccess(false);
				result.setMsg("验证码错误!");
			}
		return result;
	}
	
	public boolean isLoginUser(String username,HttpSession session) {
        Set<HttpSession> keys = SessionListener.loginUser.keySet();
        if (keys!=null&&keys.size()>0) {
        	 for (HttpSession key : keys) {
        		 if (key==session&&key.getAttribute("username").equals(username)) {
        			 return false;
				}
        		 if (SessionListener.loginUser.get(key).equals(username)) {
                     return true;
                 }
             }
		}
        return false;
	}
	@ResponseBody
	@RequestMapping(params = "userIsLogin")
	public Json userIsLogin(HttpServletRequest request,String username){
		Json result = new Json();
		request.getSession().setAttribute("username", username);
		result.setSuccess(true);
		return result;
	}
	@RequestMapping(params="logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		if (session != null) {
			session.invalidate();
		}
		return "login";
	}
}
