package com.kfcms.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.kfcms.dao.UserDao;
import com.kfcms.model.User;
import com.kfcms.service.UserService;
import com.kfcms.util.AlgorithmUtils;
import com.kfcms.util.Constants.RegisterStatus;
import com.kfcms.util.UserStatus;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User login(String account, String password) {
		Assert.hasText(account, "Account must not be empty");
		Assert.hasText(password, "Password must not be empty");
		
		// 密码加密
		String encryptPassword = "";
		try {
			encryptPassword = AlgorithmUtils.MD5(password, 16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = userDao.loginByAccount(account, encryptPassword);
		if (user!=null) {
			return user;
		}
		
		user = userDao.loginByEmail(account, encryptPassword);
		if (user!=null) {
			return user;
		}
		
		return null;
	}
	
	@Transactional
	public RegisterStatus register(User user) {
		Assert.notNull(user, "The user must not be null");
		Assert.hasText(user.getAccount(), "Account must not be empty");
		Assert.hasText(user.getEmail(), "User email must not be empty");
		
		User existUser = new User();
		existUser = userDao.queryUserByEmail(user.getEmail());
		if (existUser != null && existUser.getId() != null && existUser.getId().intValue() > 0) {
			return RegisterStatus.DUPLICATE_EMAIL;
		}
		
		existUser = queryUserByAccount(user.getAccount());
		if (existUser != null && existUser.getId() != null && existUser.getId().intValue() > 0) {
			return RegisterStatus.DUPLICATE_NAME;
		}
		
		// 密码加密
		try {
			String encrypt = AlgorithmUtils.MD5(user.getPassword(), 16);
			user.setPassword(encrypt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setGmtCreated(new Date());
		user.setGmtModified(new Date());
		user.setStatus(UserStatus.UNCKECKED.getValue());
		
		userDao.insert(user);
		
		return RegisterStatus.SUCCESS;
	}

	public User query(User user) {
		if(user == null)
			return null;
		
		if(StringUtils.isNotBlank(user.getAccount()))
			return queryUserByAccount(user.getAccount());
		
		if(StringUtils.isNotBlank(user.getEmail()))
			return userDao.queryUserByEmail(user.getEmail());
		
		return null;
	}

	public int updateByUser(User user) {
		Assert.hasText(user.getAccount(), "Account must not be empty");
		
		return userDao.updateByUser(user);
	}

	public int updatePasswordByUser(User user) {
		Assert.hasText(user.getAccount(), "Account must not be empty");
		Assert.hasText(user.getPassword(), "Password must not be empty");
		
		return userDao.updatePasswordByUser(user);
	}

	public User queryUserByAccount(String account) {
		return userDao.queryUserByAccount(account);
	}

	public List<User> queryListByConditions(Integer page, Integer pageSize,
			User user) {
		Integer offset = (page-1) * pageSize;
		
		return userDao.queryListByConditions(offset, pageSize, user);
	}

	public int countListByConditions(User user) {
		return userDao.countListByConditions(user);
	}

}
