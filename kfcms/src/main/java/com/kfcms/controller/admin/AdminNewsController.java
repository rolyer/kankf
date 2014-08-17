package com.kfcms.controller.admin;

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
import com.kfcms.model.News;
import com.kfcms.service.NewsService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/news/")
public class AdminNewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("index.html")
	public void index(HttpServletRequest request, ModelMap out, String p,
			News news) {
		Integer page = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;

		List<News> list = newsService.queryListByConditions(page,
				Constants.PAGE_SIZE, news);

		int totalrecords = newsService.countListByConditions(news);
		int totalPages = (totalrecords + Constants.PAGE_SIZE - 1)
				/ Constants.PAGE_SIZE;

		out.put("list", list);

		out.put("totalPages", totalPages);
		out.put("currentPage", page);

		out.put("nav", "news");
	}

	@RequestMapping("edit.html")
	public void edit(HttpServletRequest request, ModelMap out, String action,
			String id) {

		String title = "";
		News news = new News();

		boolean disabled = false;
		if ("add".equals(action)) {
			disabled = true;
			title = "添加";
		}

		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			news = newsService.findOne(Integer.parseInt(id));

			if ("edit".equals(action)) {
				disabled = true;
				title = "编辑";
			} else if ("view".equals(action)) {
				title = "查看";
			}
		}

		out.put("news", news);
		out.put("disabled", disabled);
		out.put("title", title);
		out.put("action", action);

		out.put("nav", "news");
	}

	@RequestMapping(value = "delete.html", method = RequestMethod.POST)
	public @ResponseBody
	Result delete(HttpServletRequest request, String id) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("参数错误");
			return result;
		}
		int count = newsService.deleteById(Integer.parseInt(id));
		if (count > 0) {
			result.setSuccess(true);
		}
		return result;
	}

	@RequestMapping(value = "save.html", method = RequestMethod.POST)
	public @ResponseBody
	Result save(ModelMap out, News news) {
		Result result = new Result();
		if(newsService.save(news) > 0) {
			result.setSuccess(true);
		}
		return result;
	}
}
