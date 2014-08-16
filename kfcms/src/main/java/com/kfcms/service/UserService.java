package com.kfcms.service;

import java.util.List;

import com.kfcms.model.User;
import com.kfcms.util.Constants.RegisterStatus;

public interface UserService {
	public User login(String account, String password);

	public RegisterStatus register(User user);

	public User query(User user);

	public int updateByUser(User user);

	public int updatePasswordByUser(User user);

	public User queryUserByAccount(String account);

	public List<User> queryListByConditions(Integer page, Integer pageSize,
			User user);

	public int countListByConditions(User user);

	public int deleteUserById(Integer id);

	public int updateStatusById(Integer id, Integer status);
}
