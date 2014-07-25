package com.kfcms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfcms.mappers.UserMapper;
import com.kfcms.model.User;
import com.kfcms.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

}
