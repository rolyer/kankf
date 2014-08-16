package com.kfcms.dao;

import com.kfcms.model.Admin;

public interface AdminDao {
	public Admin queryByAccount(String account);
}
