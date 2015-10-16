package com.examw.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class IsCode {
	@RequestMapping(params="code")
	public String codeStr(String codeString,HttpSession session){
		String sessionCode = (String) session.getAttribute("rand");
		System.out.println("sessionCode"+sessionCode+",codeString"+codeString);
		if (sessionCode.equalsIgnoreCase(codeString)) {
			System.err.println("--------------------ok-------------------------");
		}
		return "test";
	}
}
