package com.kankf.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.kankf.model.User;

public interface UserMapper {
	@Insert("INSERT INTO user(user_name, user_role, gmt_created) VALUES(#{userName},#{userRole}, #{gmtCreated})")
	@Options(useGeneratedKeys = true, keyProperty = "userId", flushCache = true, keyColumn = "user_id")
	public void insertUser(User user);
}
