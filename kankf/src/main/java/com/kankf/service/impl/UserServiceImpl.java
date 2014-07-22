package com.kankf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kankf.mappers.UserMapper;
import com.kankf.model.User;
import com.kankf.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

}
