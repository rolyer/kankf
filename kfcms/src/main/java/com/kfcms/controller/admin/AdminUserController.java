package com.kfcms.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kfcms.model.User;
import com.kfcms.service.UserService;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/user/")
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("index.html")
	public void index(HttpServletRequest request, ModelMap out, String p,
			String account, String status) {
		Integer page = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;

		User user = new User();
		if (!StringUtils.isBlank(account)) {
			user.setAccount(account);
		}
		
		if (!StringUtils.isBlank(status) && StringUtils.isNumeric(status)) {
			user.setStatus(Integer.parseInt(status));
		}

		List<User> users = userService.queryListByConditions(page,
				Constants.PAGE_SIZE, user);

		int totalrecords = userService.countListByConditions(user);
		int totalPages = (totalrecords + Constants.PAGE_SIZE - 1)
				/ Constants.PAGE_SIZE;

		out.put("users", users);

		out.put("totalPages", totalPages);
		out.put("currentPage", page);
		out.put("nav", "user");
	}
	
	@RequestMapping("edit.html")
	public void edit(ModelMap out) {
		
		out.put("nav", "user");
	}
}
