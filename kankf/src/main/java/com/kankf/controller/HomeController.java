package com.kankf.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping
	public void index(ModelMap out){
		out.put("message", "Hello World!");
		out.put("current", new Date());
	}
}
