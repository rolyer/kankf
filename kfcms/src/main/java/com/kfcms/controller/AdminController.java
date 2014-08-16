package com.kfcms.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfcms.dto.Result;
import com.kfcms.model.Admin;
import com.kfcms.service.AdminService;
import com.kfcms.util.AlgorithmUtils;
import com.kfcms.util.Constants;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
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
		if (StringUtils.isBlank(account)) {
			result.setData("账号不能为空");
			return result;
		}
		
		if (StringUtils.isBlank(password)) {
			result.setData("密码不能为空");
			return result;
		}
		
		result = adminService.login(account, password);
		
		if(result.isSuccess()) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.LOGIN_ADMIN, (Admin)result.getData());
		}
		
		return result;
	}
	
	@RequestMapping("logout.html")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin user  = (Admin) session.getAttribute(Constants.LOGIN_ADMIN);
		if (user!=null) {
			session.removeAttribute(Constants.LOGIN_ADMIN);
		}
		
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping("profile.html")
	public void profile(ModelMap out) {
		List<Admin> admins = adminService.queryAll();
		out.put("admins", admins);
		out.put("nav", "profile");
	}
	
	@RequestMapping("addadmin.html")
	public @ResponseBody Result addadmin(ModelMap out, String account, String password) {
		out.put("nav", "profile");
		
		Result result = new Result();
		if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
			result.setData("您提交的信息不完整");
			return result;
		}
		
		try {
			String encryptPassword = AlgorithmUtils.MD5(password, Constants.MD5_LENGTH);
			Admin admin = new Admin();
			admin.setAccount(account);
			admin.setPassword(encryptPassword);
			
			if (adminService.add(admin) > 0) {
				result.setSuccess(true);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("delete.html")
	public @ResponseBody Result delete(ModelMap out, String id) {
		out.put("nav", "profile");
		
		Result result = new Result();
		
		if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
			result.setData("非法数据提交");
			return result;
		}
		
		if (adminService.delete(Integer.parseInt(id))>0) {
			result.setSuccess(true);
			return result;
		}
		
		return result;
	}
	
	@RequestMapping("updatepwd.html")
	public @ResponseBody Result updatepwd (HttpServletRequest request, ModelMap out, String npwd, String opwd, String secpwd) {
		out.put("nav", "profile");
		Result result = new Result();
		
		if (StringUtils.isBlank(npwd)
				|| StringUtils.isBlank(opwd) || StringUtils.isBlank(secpwd)) {
			result.setData("您提交的信息不完整");
			return result;
		}
		
		if (!npwd.endsWith(secpwd)) {
			result.setData("两次输入的密码不一致");
			return result;
		}
		
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute(Constants.LOGIN_ADMIN);
		if (admin==null) {
			result.setData("您还没有登录或登录超时");
			return result;
		}
		
		try {
			String encrypteOpwd = AlgorithmUtils.MD5(opwd, Constants.MD5_LENGTH);
			if (!admin.getPassword().equals(encrypteOpwd)) {
				result.setData("您输入的原密码不正确");
				return result;
			}
			String encryptePassword = AlgorithmUtils.MD5(npwd, Constants.MD5_LENGTH);
			
			if(adminService.updatePassword(admin.getAccount(), encryptePassword)>0) {
				result.setSuccess(true);
				return result;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}