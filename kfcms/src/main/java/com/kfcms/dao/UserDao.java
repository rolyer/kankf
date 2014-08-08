package com.kfcms.dao;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.User;

public interface UserDao {
	public int insert(User user);
	public User queryUserByName(String name);
	public User queryUserByEmail(String email);
	public int deleteUserByName(String name);
	public User loginByName(@Param("name") String name, @Param("password") String password);
	public User loginByEmail(@Param("email") String email, @Param("password") String password);
}
