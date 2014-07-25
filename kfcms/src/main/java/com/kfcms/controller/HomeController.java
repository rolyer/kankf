package com.kfcms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.model.User;
import com.kfcms.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public void index(ModelMap out){
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");
		
		User user = new User();
		user.setGmtCreated(new Date());
		user.setUserName("userName");
		
		userService.insertUser(user);
	}
}
