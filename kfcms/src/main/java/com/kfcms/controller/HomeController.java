package com.kfcms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfcms.dto.Result;
import com.kfcms.dto.SeoPage;
import com.kfcms.model.User;
import com.kfcms.service.GameService;
import com.kfcms.service.UserService;
import com.kfcms.util.Constants;
import com.kfcms.util.Constants.RegisterStatus;
import com.kfcms.vo.GameVO;

@Controller
@RequestMapping("/")
public class HomeController {
//	private final static Logger LOGGER = Logger.getLogger(HomeController.class);
	
	private final static Integer PAGE_SIZE = 50;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	private SeoPage seoPage = new SeoPage("看开服", 
			"网页游戏,网页游戏开服表,网页游戏开服时间表,网页游戏新服,最新网页游戏开服表,最新开服网页游戏,最新最全网页游戏开服信息就在看开服", 
			"看开服网页游戏开服表最权威页游媒体精心打造，注册、找攻略、领礼包同步进行，尽享便捷一站式服务。最新最全网页游戏开服信息就在看开服。");
	
	@RequestMapping("index.html")
	public void index(ModelMap out){
		seoPage.setTitle("首页 - 看开服");
		out.put("seo", seoPage);
		
		Date date =  new Date();
		List<GameVO> todayGamesList = gameService.queryListByStartTime(1, PAGE_SIZE, date);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		List<GameVO> tomorrowGamesList = gameService.queryListByStartTime(1, PAGE_SIZE, date);
		
		out.put("games", todayGamesList);
		out.put("tomgames", tomorrowGamesList);
		
	}
	
	@RequestMapping("today.html")
	public void today(ModelMap out, Integer page){
		seoPage.setTitle("今日开服 - 看开服");
		out.put("seo", seoPage);
		
		Integer pageSize = 25;
		if (page == null || page.intValue() <= 0) {
			page = 1;
		}
		
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		date = c.getTime();
		
		List<GameVO> list = gameService.queryListByStartTime(page, pageSize, date);
		
		Integer total =  gameService.countListByStartTime(date);
		Integer totalPages = (total + pageSize - 1) / pageSize;
		
		out.put("page", page);
		out.put("totalPages", totalPages);
		out.put("games", list);
	}
	
	@RequestMapping("tomorrow.html")
	public void tomorrow(ModelMap out, Integer page){
		seoPage.setTitle("明日开服 - 看开服");
		out.put("seo", seoPage);
		
		Integer pageSize = 25;
		if (page == null || page.intValue() <= 0) {
			page = 1;
		}
		
		Date date =  new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		List<GameVO> list = gameService.queryListByStartTime(page, pageSize, date);
		Integer total =  gameService.countListByStartTime(date);
		Integer totalPages = (total + pageSize - 1) / pageSize;
		
		out.put("page", page);
		out.put("totalPages", totalPages);
		out.put("games", list);
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
	public void play(ModelMap out, String url){
		seoPage.setTitle("开始游戏 - 看开服");
		out.put("seo", seoPage);
		
		out.put("url", url);
	}
	
	@RequestMapping("register.html")
	public void register(ModelMap out) {
		seoPage.setTitle("用户注册 - 看开服");
		out.put("seo", seoPage);
	}
	
	@RequestMapping("reg.html")
	public @ResponseBody Result reg(HttpServletRequest request, ModelMap out, User user) {
		Result result = new Result();
		
		RegisterStatus status = userService.register(user);
		if(RegisterStatus.SUCCESS.equals(status)) {
			result.setSuccess(true);
			
			HttpSession session = request.getSession();
			
			session.setAttribute(Constants.LOGIN_USER, user);
		} else {
			if (RegisterStatus.DUPLICATE_EMAIL.equals(status)) {
				result.setData("对不起，该邮箱已被注册");
			} else if (RegisterStatus.DUPLICATE_NAME.equals(status)) {
				result.setData("对不起，该用户名已被注册");
			}
		}
		
		return result;
	}
	
	@RequestMapping("error.html")
	public void error(ModelMap out, String code) {
		seoPage.setTitle("看开服");
		out.put("seo", seoPage);
		
		out.put("code", code);
	}
}
