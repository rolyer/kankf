package com.kfcms.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.kfcms.util.AlgorithmUtils;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private UserService userService;

	@RequestMapping("index.html")
	public void index(ModelMap out) {
		out.put("nav", "index");
		
	}

	@RequestMapping("login.html")
	public void login(ModelMap out) {
		
	}
	
	@RequestMapping("ajaxlogin.html")
	public @ResponseBody Result ajaxlogin(HttpServletRequest request, String account, String password) {
		Result result = new Result();
		
		User user = userService.login(account, password);
		if (user != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute(Constants.LOGIN_USER, user);
			
			result.setSuccess(true);
		} else {
			result.setData("用户名或者密码有误");
		}
		
		return result;
	}
	
	@RequestMapping("logout.html")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user  = (User) session.getAttribute(Constants.LOGIN_USER);
		if (user!=null) {
			session.removeAttribute(Constants.LOGIN_USER);
		}
		
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping("profile.html")
	public void profile(HttpServletRequest request, ModelMap out) {
		User loginUser = getLoginUser(request);
		
		User member = userService.queryUserByAccount(loginUser.getAccount());
		
		out.put("member", member);
		out.put("nav", "profile");
	}
	
	@RequestMapping(value = "updateprofile.html", method = RequestMethod.POST)
	public @ResponseBody Result updateprofile(HttpServletRequest request, String tel, String im) {
		Result result = new Result();
		
		User loginUser = getLoginUser(request);
		if (loginUser==null) {
			result.setData(Constants.MSG_USER_NO_LOGIN);
			return result;
		}
		
		User user = new User();
		user.setTel(tel);
		user.setIm(im);
		user.setAccount(loginUser.getAccount());
		
		int count = userService.updateByUser(user);
		if (count>0) {
			result.setSuccess(true);
		}
		return result;
	}
	
	@RequestMapping(value = "updatepwd.html", method = RequestMethod.POST)
	public @ResponseBody Result updatepwd(HttpServletRequest request, String npwd, String opwd) {
		Result result = new Result();
		
		if (StringUtils.isBlank(npwd) || StringUtils.isBlank(opwd)) {
			result.setData(Constants.MSG_USER_NO_LOGIN);
			return result;
		}
		
		User loginUser = getLoginUser(request);
		if (loginUser==null) {
			result.setData(Constants.MSG_USER_NO_LOGIN);
			return result;
		}
		
		try {
			
			String encrypteOpwd = AlgorithmUtils.MD5(opwd, 16);
			
			if (!loginUser.getPassword().equals(encrypteOpwd)) {
				result.setData(Constants.MSG_PASSWORD_NOT_MATCH);
				return result;
			}
			
			String encryptPwd = AlgorithmUtils.MD5(npwd, 16);
			
			User user = new User();
			user.setAccount(loginUser.getAccount());
			user.setPassword(encryptPwd);
			
			int count = userService.updatePasswordByUser(user);
			if (count>0) {
				result.setSuccess(true);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@RequestMapping("help.html")
	public void help(ModelMap out) {
		out.put("nav", "help");
	}
	
	private User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.LOGIN_USER);
		
		return user;
	}
}
