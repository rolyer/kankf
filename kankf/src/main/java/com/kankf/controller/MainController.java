package com.kankf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/index.html")
	public void index(ModelMap out){
		out.put("message", "Hello World!");
	}
}
