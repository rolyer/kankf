package com.kfcms.mappers;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kfcms.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml" })
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsertUser() {
		
		User user = new User("testUserName", "userPassword", "userEmail",
				"userTel", "userQqmsn", new Date(), new Date());
		
		userMapper.insertUser(user);
	}
}
