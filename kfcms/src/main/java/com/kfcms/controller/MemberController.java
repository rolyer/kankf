package com.kfcms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.model.User;
import com.kfcms.service.UserService;

@Controller
public class MemberController {

	@Autowired
	private UserService userService;

	@RequestMapping("/member/index.html")
	public void index(ModelMap out) {
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");

		User user = new User();
		user.setName("hmoe");
		user.setEmail("userEmail");
		user.setPassword("userPassword");
		user.setQqmsn("userQqmsn");
		user.setTel("userTel");

		user.setGmtCreated(new Date());
		user.setGmtModified(new Date());

		userService.add(user);
	}

	@RequestMapping("/member/login.html")
	public void login(ModelMap out) {
	}
	
	@RequestMapping("/member/profile.html")
	public void profile(ModelMap out) {
	}
	
	@RequestMapping("/member/game.html")
	public void game(ModelMap out) {
	}
	
	@RequestMapping("/member/gift.html")
	public void gift(ModelMap out) {
	}
}
