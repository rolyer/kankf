package com.kfcms.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.kfcms.dao.UserDao;
import com.kfcms.model.User;
import com.kfcms.service.UserService;
import com.kfcms.util.UserStatus;
import com.kfcms.util.Constants.RegisterStatus;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public RegisterStatus register(User user) {
		Assert.notNull(user, "The user must not be null");
		Assert.hasText(user.getName(), "User name must not be empty");
		Assert.hasText(user.getEmail(), "User email must not be empty");
		
		User existUser = new User();
		existUser = userDao.queryUserByEmail(user.getEmail());
		if (existUser != null && existUser.getId() != null && existUser.getId().intValue() > 0) {
			return RegisterStatus.DUPLICATE_EMAIL;
		}
		
		existUser = userDao.queryUserByEmail(user.getEmail());
		if (existUser != null && existUser.getId() != null && existUser.getId().intValue() > 0) {
			return RegisterStatus.DUPLICATE_NAME;
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
		
		if(StringUtils.isNotBlank(user.getName()))
			return userDao.queryUserByEmail(user.getName());
		
		if(StringUtils.isNotBlank(user.getEmail()))
			return userDao.queryUserByEmail(user.getEmail());
		
		return null;
	}

}
