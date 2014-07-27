package com.kfcms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfcms.dao.UserMapper;
import com.kfcms.model.User;
import com.kfcms.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public User add(User user) {
		userMapper.insert(user);
		return user;
	}

	public User query(User user) {
		if(user == null)
			return null;
		
		if(StringUtils.isNotBlank(user.getName()))
			return userMapper.queryUserByEmail(user.getName());
		
		if(StringUtils.isNotBlank(user.getEmail()))
			return userMapper.queryUserByEmail(user.getEmail());
		
		return null;
	}

}
