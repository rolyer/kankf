package com.kfcms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfcms.dto.Result;
import com.kfcms.model.User;
import com.kfcms.service.UserService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index.html")
	public void index(ModelMap out) {
	}
	
	@RequestMapping("login.html")
	public void login(ModelMap out) {
		
	}
	
	@RequestMapping("ajaxlogin.html")
	public @ResponseBody Result ajaxlogin(HttpServletRequest request, String account, String password) {
		Result result = new Result();
		
		User user = userService.login(account, password);
		if (user != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute(Constants.LOGIN_ADMIN, user);
			
			result.setSuccess(true);
		} else {
			result.setData("用户名或者密码有误");
		}
		
		return result;
	}
	
	@RequestMapping("logout.html")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user  = (User) session.getAttribute(Constants.LOGIN_ADMIN);
		if (user!=null) {
			session.removeAttribute(Constants.LOGIN_ADMIN);
		}
		
		return "redirect:" + request.getHeader("Referer");
	}
}