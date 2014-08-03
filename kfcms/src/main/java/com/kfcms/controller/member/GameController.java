package com.kfcms.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/game/")
public class GameController {

	@RequestMapping("index.html")
	public void index(ModelMap out) {
	}
	
	@RequestMapping("edit.html")
	public void edit(ModelMap out, String id) {
		
	}
}
