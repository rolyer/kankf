package com.kfcms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.dto.SeoPage;
import com.kfcms.model.Game;
import com.kfcms.service.GameService;
import com.kfcms.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
//	private final static Logger LOGGER = Logger.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	private SeoPage seoPage = new SeoPage("看开服", 
			"网页游戏,网页游戏开服表,网页游戏开服时间表,网页游戏新服,最新网页游戏开服表,最新开服网页游戏,最新最全网页游戏开服信息就在看开服", 
			"看开服网页游戏开服表最权威页游媒体精心打造，注册、找攻略、领礼包同步进行，尽享便捷一站式服务。最新最全网页游戏开服信息就在看开服。");
	
	private List<Game> todayGamesList;
	private List<Game> tomorrowGamesList;
	
	@RequestMapping("index.html")
	public void index(ModelMap out){
		seoPage.setTitle("首页 - 看开服");
		out.put("seo", seoPage);
		
		Date date =  new Date();
		todayGamesList = gameService.queryList(50, date);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		tomorrowGamesList = gameService.queryList(50, date);
		
		out.put("games", todayGamesList);
		out.put("tomgames", tomorrowGamesList);
		
	}
	
	@RequestMapping("today.html")
	public void today(ModelMap out){
		seoPage.setTitle("今日开服 - 看开服");
		out.put("seo", seoPage);
		
		Date date =  new Date();
		todayGamesList = gameService.queryList(50, date);
		
		out.put("games", todayGamesList);
	}
	
	@RequestMapping("tomorrow.html")
	public void tomorrow(ModelMap out){
		seoPage.setTitle("明日开服 - 看开服");
		out.put("seo", seoPage);
		
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		tomorrowGamesList = gameService.queryList(50, date);
		out.put("games", tomorrowGamesList);
	}
	
	@RequestMapping("rank.html")
	public void rank(ModelMap out){
		seoPage.setTitle("开服排行 - 看开服");
		out.put("seo", seoPage);
		
	}
	
	@RequestMapping("gift.html")
	public void gift(ModelMap out){
		seoPage.setTitle("开服礼包 - 看开服");
		out.put("seo", seoPage);
	}
	
	@RequestMapping("search.html")
	public void search(ModelMap out){
		seoPage.setTitle("搜索 - 看开服");
		out.put("seo", seoPage);
	}
	
	@RequestMapping("play.html")
	public void play(ModelMap out){
		seoPage.setTitle("开始游戏 - 看开服");
		out.put("seo", seoPage);
	}
}
