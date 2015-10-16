package com.examw.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.examw.demo.dao.IStuDAO;
import com.examw.demo.domain.PageBean;
import com.examw.demo.domain.Stu;
import com.examw.demo.util.JsonUtil;
import com.examw.demo.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("index")
public class DemoController {
	//private static final Logger logger = Logger.getLogger(DemoController.class);
	@Autowired
	IStuDAO iStuDAO;
	
	@RequestMapping(params = "home")
	public String index(){
		
		return "home";
	}
	/*@RequestMapping(params="hello")
	public String hello(Model model){
		List<Stu> stus = iStuDAO.selectstus();
		model.addAttribute("stus",stus);
		return "hello-word";
	}*/
	@RequestMapping(params="index-demo")
	public String adds(){
		
		return "index-demo";
	}
	@RequestMapping(params="addstu")
	public void addstu(Stu stu,HttpServletResponse response){
		iStuDAO.addstu(stu);
		/*List<Stu> stus = iStuDAO.selectstus();
		model.addAttribute("stus",stus);
		return "hello-word";*/
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	@RequestMapping(params="upstu")
	public void update(HttpServletResponse response,Stu stu){
		iStuDAO.upstu(stu);
		/*List<Stu> stus = iStuDAO.selectstus();
		model.addAttribute("stus",stus);
		return "hello-word";*/
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 删除学员
	 * @param response
	 * @param id
	 * @param model
	 */
	@RequestMapping(params="delstu")
	public void delstu(HttpServletResponse response,int id,Model model){
		iStuDAO.delstu(id);
		JSONObject result = new JSONObject();
		result.put("success", true);
		/*List<Stu> stus = iStuDAO.selectstus();
		model.addAttribute("stus",stus);*/
		//return "hello-word";
		ResponseUtil.write(response, result);
	}
	/**
	 * 查询stuList
	 * @param response
	 * @param pageBean
	 * @throws Exception
	 */
	
	@RequestMapping(params="stuList")
	@ResponseBody
	public JSONObject stuList(PageBean pageBean) throws Exception{
		PageHelper.startPage(pageBean.getPage(), pageBean.getRows(), StringUtils.trimToEmpty(pageBean.getSort()) + " " + StringUtils.trimToEmpty(pageBean.getOrder()));
		//查询数据
		final List<Stu> list = iStuDAO.findStus(pageBean);
		//获取分页信息
		final PageInfo<Stu> pageInfo = new PageInfo<Stu>(list);
		//初始化
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(list);
			result.put("rows", jsonArray);
			result.put("total", pageInfo.getTotal());
			//JSONObject result = new JSONObject();
			//JSONArray jsonArray = JsonUtil.formatRsToJsonArray(iStuDAO.selectstus(pageBean));
			//int total=iStuDAO.selectCount();
			//result.put("rows", jsonArray);
			//result.put("total", total);
			return result;
	}
}
