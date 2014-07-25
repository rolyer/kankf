package com.kfcms.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.kfcms.model.User;

public interface UserMapper {
	@Insert("INSERT INTO user(user_name, gmt_created) VALUES(#{userName}, #{gmtCreated})")
	@Options(useGeneratedKeys = true, keyProperty = "userId", flushCache = true, keyColumn = "user_id")
	public void insertUser(User user);
}
