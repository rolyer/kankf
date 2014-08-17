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
import com.kfcms.model.User;
import com.kfcms.service.UserService;
import com.kfcms.util.Constants;
import com.kfcms.util.Constants.RegisterStatus;

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
	public void edit(HttpServletRequest request, ModelMap out, String action,
			String id) {

		if (StringUtils.isNotBlank(action)) {
			String title = "";
			User user = new User();

			boolean disabled = false;
			if ("add".equals(action)) {
				disabled = true;
				title = "添加";
			}

			if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
				user = userService.queryById(Integer.parseInt(id));

				if ("edit".equals(action)) {
					disabled = true;
					title = "编辑";
				} else if ("view".equals(action)) {
					title = "查看";
				}
			}

			out.put("user", user);
			out.put("disabled", disabled);
			out.put("title", title);
			out.put("action", action);
			out.put("nav", "user");
		}
	}
	
	@RequestMapping(value = "updateStatus.html", method = RequestMethod.POST)
	public @ResponseBody
	Result updateStatus(HttpServletRequest request, String id, String status) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)
				|| StringUtils.isBlank(status)
				|| !StringUtils.isNumeric(status)) {
			result.setData("参数错误");
			return result;
		}
		int count = userService.updateStatusById(Integer.parseInt(id),
				Integer.parseInt(status));
		if (count > 0) {
			result.setSuccess(true);
		}
		return result;
	}
	
	@RequestMapping(value = "delete.html", method = RequestMethod.POST)
	public @ResponseBody
	Result delete(HttpServletRequest request, String id) {
		Result result = new Result();
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("参数错误");
			return result;
		}
		int count = userService.deleteUserById(Integer.parseInt(id));
		if (count > 0) {
			result.setSuccess(true);
		}
		return result;
	}
	
	@RequestMapping(value = "add.html", method = RequestMethod.POST)
	public @ResponseBody Result add(HttpServletRequest request, ModelMap out, User user) {
		Result result = new Result();
		RegisterStatus status = userService.register(user);
		if(RegisterStatus.SUCCESS.equals(status)) {
			result.setSuccess(true);
		} else {
			if (RegisterStatus.DUPLICATE_EMAIL.equals(status)) {
				result.setData("对不起，该邮箱已被注册");
			} else if (RegisterStatus.DUPLICATE_NAME.equals(status)) {
				result.setData("对不起，该用户名已被注册");
			} else {
				result.setData("添加失败");
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "update.html", method = RequestMethod.POST)
	public @ResponseBody Result update(HttpServletRequest request, ModelMap out, User user) {
		Result result = new Result();
		RegisterStatus status = userService.update(user);
		if(RegisterStatus.SUCCESS.equals(status)) {
			result.setSuccess(true);
		} else {
			if (RegisterStatus.NO_SUCH_USER.equals(status)) {
				result.setData("该用户不存在或已删除");
			} else if (RegisterStatus.DUPLICATE_EMAIL.equals(status)) {
				result.setData("对不起，该邮箱已被注册");
			} else if (RegisterStatus.DUPLICATE_NAME.equals(status)) {
				result.setData("对不起，该用户名已被注册");
			} else if (RegisterStatus.PWD_ENCRYPT_ERORR.equals(status)) {
				result.setData("密码加密失败，请重试！");
			} else {
				result.setData("更新失败");
			}
		}
		
		return result;
	}
}
