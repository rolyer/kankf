package com.kfcms.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.kfcms.model.User;
import com.kfcms.service.GameService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/game/")
public class AdminGameController {

	@Autowired
	private GameService gameService;
	
	@RequestMapping("index.html")
	public void index(HttpServletRequest request, ModelMap out, String p) {
		Integer page = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;
		
		List<Game> list = gameService.queryListByConditions(page, Constants.PAGE_SIZE, null);
		
		int totalrecords =  gameService.countListByConditions(null);
		int totalPages = (totalrecords + Constants.PAGE_SIZE - 1) / Constants.PAGE_SIZE;
		
		out.put("list", list);
		
		out.put("totalPages", totalPages);
		out.put("currentPage", page);
	}
	
	@RequestMapping("edit.html")
	public void edit(HttpServletRequest request, ModelMap out, String action, String id) {
		
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
				
				game = gameService.queryByIdAndAccount(gid, null, true);
				
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
	public ModelAndView save(HttpServletRequest request, ModelMap out, Game game, String statTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = sdf.parse(statTime);
			game.setStartTime(date);
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(Constants.LOGIN_ADMIN);
			
			game.setAccount(user.getAccount());
			
			gameService.save(game, true);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("/admin/game/index");
	}
	
	@RequestMapping(value = "delete.html", method = RequestMethod.POST)
	public @ResponseBody Result delete(HttpServletRequest request, String id) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("Illegally parameter id: " + id);
			return result;
		}
		int count = gameService.deleteByIdAndAccount(Integer.parseInt(id), null, true);
		if (count>0) {
			result.setSuccess(true);
		}
		return result;
	}
}
