package com.kfcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.User;

public interface UserDao {
	public int insert(User user);

	public User queryUserByAccount(String account);

	public User queryUserByEmail(String email);

	public int deleteUserByAccount(String account);

	public int deleteUserById(Integer id);

	public User loginByAccount(@Param("account") String account,
			@Param("password") String password);

	public User loginByEmail(@Param("email") String email,
			@Param("password") String password);

	public int updateByUser(User user);

	public int updatePasswordByUser(User user);

	public List<User> queryListByConditions(@Param("offset") Integer offset,
			@Param("rowCount") Integer rowCount, @Param("user") User user);

	public int countListByConditions(@Param("user") User user);

	public int updateStatusById(@Param("id") Integer id,
			@Param("status") Integer status);
	
	public User queryById(Integer id);
	
	public int update(User user);
}
