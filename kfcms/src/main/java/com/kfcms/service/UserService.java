package com.kfcms.service;

import com.kfcms.model.User;
import com.kfcms.util.Constants.RegisterStatus;

public interface UserService {
	public User login(String account, String password);

	public RegisterStatus register(User user);

	public User query(User user);

	public int updateByUser(User user);

	public int updatePasswordByUser(User user);
	
	public User queryUserByAccount(String account);
}
