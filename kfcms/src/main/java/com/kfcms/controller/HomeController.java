package com.kfcms.controller;

import java.util.Calendar;
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
	
	
	private List<Game> todayGamesList;
	private List<Game> tomorrowGamesList;
	
	private void initGamesList() {
		Date date =  new Date();
		todayGamesList = gameService.queryList(50, date);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		tomorrowGamesList = gameService.queryList(50, date);
	}
	
	@RequestMapping("index.html")
	public void index(ModelMap out){
		LOGGER.debug("HomeController : index");		
		out.put("message", "Hello World!");
		out.put("current", new Date());
		out.put("title", "Index");
		
		if( todayGamesList == null || tomorrowGamesList == null ) {
			initGamesList();
		}
		
		out.put("games", todayGamesList);
		out.put("tomgames", tomorrowGamesList);
	}
	
	@RequestMapping("today.html")
	public void today(ModelMap out){
		if(todayGamesList==null) {
			initGamesList();
		}
		out.put("games", todayGamesList);
	}
	
	@RequestMapping("tomorrow.html")
	public void tomorrow(ModelMap out){
		if(tomorrowGamesList==null) {
			initGamesList();
		}
		out.put("games", tomorrowGamesList);
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
