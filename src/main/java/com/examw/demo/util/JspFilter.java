package com.examw.demo.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String path = httpRequest.getRequestURI();
		if(path.indexOf("/demo/login.html") > -1||path.indexOf("/demo/resources/")>-1||path.indexOf("/demo/my.do")>-1||path.indexOf("/demo/verification.do")>-1) {
				chain.doFilter(httpRequest, httpResponse);
			    return;
		}
		if(httpRequest.getSession().getAttribute("username")==null||"".equals(httpRequest.getSession().getAttribute("username"))){
			httpResponse.sendRedirect("/demo/login.html");
		}else{
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
