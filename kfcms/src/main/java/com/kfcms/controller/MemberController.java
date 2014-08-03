package com.kfcms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.service.UserService;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private UserService userService;

	@RequestMapping("index.html")
	public void index(ModelMap out) {
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");
	}

	@RequestMapping("login.html")
	public void login(ModelMap out) {
	}
	
	@RequestMapping("profile.html")
	public void profile(ModelMap out) {
	}

	@RequestMapping("help.html")
	public void help(ModelMap out) {
	}
}
