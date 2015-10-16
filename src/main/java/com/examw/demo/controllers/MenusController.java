package com.examw.demo.controllers;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examw.demo.dao.IMenusDAO;
import com.examw.demo.dao.ISubmenusDAO;
import com.examw.demo.util.JsonUtil;
import com.examw.demo.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("menusController")
public class MenusController {
	@Autowired
	IMenusDAO iMenusDAO;
	@Autowired
	ISubmenusDAO iSubmenusDAO;
	
	@RequestMapping(params="menusList")
	public void menusList(HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		JSONArray menus = JsonUtil.formatRsToJsonArray(iMenusDAO.selectmenus());
		JSONArray submenus = JsonUtil.formatRsToJsonArray(iSubmenusDAO.selectSubmenus());
		result.put("menus", menus);
		result.put("submenus", submenus);
		ResponseUtil.write(response, result);
	}
}
