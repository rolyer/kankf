package com.kfcms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.model.Game;
import com.kfcms.service.GameService;
import com.kfcms.service.UserService;

@Controller
public class HomeController {
	private final static Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	private List<Game> games;
	
	private void initGameList(){
		games = gameService.queryList(10);
	}
	
	@RequestMapping("index.html")
	public void index(ModelMap out){
		LOGGER.debug("HomeController : index");		
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");
		if(games==null) {
			initGameList();
		}
		out.put("games", games);
	}
	
	@RequestMapping("today.html")
	public void today(ModelMap out){
		if(games==null) {
			initGameList();
		}
		out.put("games", games);
	}
	
	@RequestMapping("tomorrow.html")
	public void tomorrow(ModelMap out){
		if(games==null) {
			initGameList();
		}
		out.put("games", games);
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
