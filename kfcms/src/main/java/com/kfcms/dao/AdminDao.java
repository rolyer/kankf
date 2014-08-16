package com.kfcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.Admin;

public interface AdminDao {
	public Admin queryByAccount(String account);
	
	public List<Admin> queryAll();
	
	public int updatePassword(@Param("account") String account, @Param("password") String password);
	
	public int deleteById(@Param("id") Integer id);
	
	public int insert(Admin admin);
}
