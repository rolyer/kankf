package com.kfcms.service;

import java.util.List;

import com.kfcms.dto.Result;
import com.kfcms.model.Admin;

public interface AdminService {
	public Result login(String account, String password);

	public List<Admin> queryAll();

	public int updatePassword(String account, String password);

	public int delete(Integer id);

	public int add(Admin admin);
}
