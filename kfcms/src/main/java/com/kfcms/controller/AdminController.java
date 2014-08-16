package com.kfcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfcms.dto.Result;
import com.kfcms.model.Admin;
import com.kfcms.service.AdminService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("index.html")
	public void index(ModelMap out) {
		out.put("nav", "index");
	}
	
	@RequestMapping("login.html")
	public void login(ModelMap out) {
		
	}
	
	@RequestMapping("ajaxlogin.html")
	public @ResponseBody Result ajaxlogin(HttpServletRequest request, String account, String password) {
		Result result = new Result();
		if (StringUtils.isBlank(account)) {
			result.setData("账号不能为空");
			return result;
		}
		
		if (StringUtils.isBlank(password)) {
			result.setData("密码不能为空");
			return result;
		}
		
		result = adminService.login(account, password);
		
		if(result.isSuccess()) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.LOGIN_ADMIN, (Admin)result.getData());
		}
		
		return result;
	}
	
	@RequestMapping("logout.html")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin user  = (Admin) session.getAttribute(Constants.LOGIN_ADMIN);
		if (user!=null) {
			session.removeAttribute(Constants.LOGIN_ADMIN);
		}
		
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping("profile.html")
	public void profile(ModelMap out) {
		out.put("nav", "admin");
		//TODO: read admin list
	}
	
	@RequestMapping("addadmin.html")
	public @ResponseBody Result addadmin(ModelMap out) {
		out.put("nav", "admin");
		//TODO: read admin list
		
		return null;
	}
	
	@RequestMapping("deladmin.html")
	public @ResponseBody Result deladmin(ModelMap out) {
		out.put("nav", "admin");
		//TODO: read admin list
		
		return null;
	}
	
	@RequestMapping("updatepwd.html")
	public @ResponseBody Result updatepwd (ModelMap out) {
		out.put("nav", "admin");
		//TODO: read admin list
		
		return null;
	}
}