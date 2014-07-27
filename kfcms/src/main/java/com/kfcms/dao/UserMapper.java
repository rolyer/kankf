package com.kfcms.dao;

import com.kfcms.model.User;

public interface UserMapper {
	public int insert(User user);
	public User queryUserByName(String name);
	public User queryUserByEmail(String email);
	public int deleteUserByName(String name);
}
