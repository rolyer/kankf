package com.kfcms.service;

import com.kfcms.model.User;
import com.kfcms.util.Constants.RegisterStatus;

public interface UserService {
	public RegisterStatus register(User user);
	
	public User query(User user);
}
