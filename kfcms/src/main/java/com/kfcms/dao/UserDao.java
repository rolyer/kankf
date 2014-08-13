package com.kfcms.dao;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.User;

public interface UserDao {
	public int insert(User user);
	public User queryUserByAccount(String account);
	public User queryUserByEmail(String email);
	public int deleteUserByAccount(String account);
	public User loginByAccount(@Param("account") String account, @Param("password") String password);
	public User loginByEmail(@Param("email") String email, @Param("password") String password);
}
