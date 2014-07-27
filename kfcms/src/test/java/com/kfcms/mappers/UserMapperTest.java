package com.kfcms.mappers;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kfcms.dao.UserDao;
import com.kfcms.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml", "classpath:test-spring-mybatis.xml" })
public class UserMapperTest {

	@Autowired
	private UserDao dao;
	
	private final static String NAME = "testUserName";
	private final static String EMAIL = "test@test.com";
	
	@Before
	public void before() {
		insert();
	}
	
	@After
	public void after() {
		delete();
	}

	public void insert() {
		User user = new User(NAME, "userPassword", EMAIL, 0,
				"userTel", "userQqmsn", new Date(), new Date());

		int  id = dao.insert(user);
		
		Assert.assertTrue(id>0);
	}
	
	public void delete() {
		int id = dao.deleteUserByName(NAME);
		
		Assert.assertTrue(id==1);
	}
	
	@Test
	public void testQueryUserByName() {
		User user = dao.queryUserByName(NAME);
		
		Assert.assertNotNull(user);
		Assert.assertTrue(NAME.equals(user.getName()));
	}
	

	@Test
	public void testQueryUserByEmail() {
		User user = dao.queryUserByEmail(EMAIL);
		
		Assert.assertNotNull(user);
		Assert.assertTrue(NAME.equals(user.getName()));
	}
}
