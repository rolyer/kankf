package com.kfcms.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfcms.dto.Result;
import com.kfcms.model.Game;
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

		List<Game> list = gameService.queryListByConditions(page,
				Constants.PAGE_SIZE, null);

		int totalrecords = gameService.countListByConditions(null);
		int totalPages = (totalrecords + Constants.PAGE_SIZE - 1)
				/ Constants.PAGE_SIZE;

		out.put("list", list);

		out.put("totalPages", totalPages);
		out.put("currentPage", page);
		out.put("nav", "game");
	}

	@RequestMapping("edit.html")
	public void edit(HttpServletRequest request, ModelMap out, String action,
			String id, String account) {

		if (StringUtils.isNotBlank(action) && StringUtils.isNotBlank(account)) {
			String title = "";
			Game game = new Game();

			boolean disabled = false;
			if ("add".equals(action)) {
				disabled = true;
				title = "添加";
			}

			if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
				Integer gid = Integer.parseInt(id);

				game = gameService.queryByIdAndAccount(gid, account);

				if ("edit".equals(action)) {
					disabled = true;
					title = "编辑";
				} else if ("view".equals(action)) {
					title = "查看";
				}
			}

			out.put("game", game);
			out.put("disabled", disabled);
			out.put("title", title);
			out.put("nav", "game");
			out.put("action", action);
		}
	}

	@RequestMapping(value = "save.html", method = RequestMethod.POST)
	public @ResponseBody
	Result save(ModelMap out, Game game,
			String statTime) {
		Result result = new Result();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = sdf.parse(statTime);
			game.setStartTime(date);
			if (gameService.save(game) > 0) {
				result.setSuccess(true);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			result.setData("日期格式有误");
		}

		return result;
	}

	@RequestMapping(value = "delete.html", method = RequestMethod.POST)
	public @ResponseBody
	Result delete(HttpServletRequest request, String id, String account) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("参数错误");
			return result;
		}
		int count = gameService.deleteByIdAndAccount(Integer.parseInt(id),
				account);
		if (count > 0) {
			result.setSuccess(true);
		}
		return result;
	}
}
