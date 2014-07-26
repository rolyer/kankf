package com.kfcms.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.model.User;
import com.kfcms.service.UserService;

@Controller
public class HomeController {
	private final static Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("index.html")
	public void index(ModelMap out){
		LOGGER.debug("HomeController : index");		
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");
		
		User user = new User();
		user.setGmtCreated(new Date());
		user.setUserName("hmoe");
		
		userService.insertUser(user);
	}
	
	@RequestMapping("today.html")
	public void today(ModelMap out){
		
	}
	
	@RequestMapping("tomorrow.html")
	public void tomorrow(){
		
	}
	
	@RequestMapping("rank.html")
	public void rank(){
		
	}
	
	@RequestMapping("gift.html")
	public void gift(){
		
	}
	
	@RequestMapping("search.html")
	public void search(){
		
	}
	
	@RequestMapping("play.html")
	public void play(){
		
	}
}
