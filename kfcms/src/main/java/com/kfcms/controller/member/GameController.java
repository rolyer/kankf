package com.kfcms.controller.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kfcms.dto.Result;
import com.kfcms.model.Game;
import com.kfcms.service.GameService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/member/game/")
public class GameController {

	@Autowired
	private GameService gameService;
	
	private final String username = "test";

	@RequestMapping("index.html")
	public void index(ModelMap out, String p) {
		Integer page = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;

		List<Game> list = gameService.queryListByConditions(page, Constants.PAGE_SIZE, username);
		
		int totalrecords =  gameService.countListByConditions(username);
		int totalPages = (totalrecords + Constants.PAGE_SIZE - 1) / Constants.PAGE_SIZE;
		
		out.put("list", list);
		
		out.put("totalPages", totalPages);
		out.put("currentPage", page);
	}

	@RequestMapping("edit.html")
	public void edit(ModelMap out, String action, String id) {
		
		if(StringUtils.isNotBlank(action)){
			String title = "";
			Game game = new Game();
			
			boolean disabled = false;
			if("add".equals(action)) {
				disabled = true;
				title = "添加";
			}
			
			if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
				Integer gid = Integer.parseInt(id);
				game = gameService.queryByIdAndUserName(gid, username);
				
				if("edit".equals(action)) {
					disabled = true;
					title = "编辑";
				} else if("view".equals(action)) {
					title = "查看";
				}
			}
			
			out.put("game", game);
			out.put("disabled", disabled);
			out.put("title", title);
		}
	}
	
	@RequestMapping("save.html")
	public ModelAndView save(ModelMap out, Game game, String statTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = sdf.parse(statTime);
			game.setStartTime(date);
			game.setUserName(username);
			
			gameService.save(game);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("/member/game/index");
	}
	
	@RequestMapping(value = "delete.html", method = RequestMethod.POST)
	public @ResponseBody Result delete(String id) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("Illegally parameter id: " + id);
			return result;
		}
		
		int count = gameService.deleteByIdAndUserName(Integer.parseInt(id), username);
		if (count>0) {
			result.setSuccess(true);
		}
		return result;
	}
}
