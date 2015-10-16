package com.examw.demo.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener{
	public static Map<HttpSession,String> loginUser = new HashMap<HttpSession, String>();
	public static String SESSION_LOGIN_NAME = "username"; 
	
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		 	try {
		 		loginUser.remove(se.getSession());
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String userlogin = (String) se.getValue();
		if (se.getName().equals(SESSION_LOGIN_NAME)) {
			for(Entry<HttpSession,String> entry : loginUser.entrySet()){   
				HttpSession session = entry.getKey();
				String username = (String) session.getAttribute(SESSION_LOGIN_NAME);
	            if(username != null&&userlogin!=null&&username.equals(userlogin)){ 
	                session.removeAttribute(SESSION_LOGIN_NAME);
	                loginUser.remove(session); 
	                break;
	             }   
	    }
			   loginUser.put(se.getSession(), String.valueOf(se.getValue()));
		}
	}
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		if (se.getName().equals(SESSION_LOGIN_NAME)) {
            try {
            	loginUser.remove(se.getSession());
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	}
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		 if (se.getName().equals(SESSION_LOGIN_NAME)) {
			   loginUser.put(se.getSession(),String.valueOf(se.getValue()));
			}
	}
}
