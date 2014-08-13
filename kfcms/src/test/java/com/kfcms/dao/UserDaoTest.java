package com.kfcms.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.kfcms.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml", "classpath:test-spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/data/UserDaoTest.xml")
@ActiveProfiles("hsqldb")
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	private final String account = "testuser";
	private final String email = "test@test.com";
	private final String password ="12345678";
	
	@Test
	public void insert(){
		User user = new User("account", "password", "email", "tel", "im", 0, new Date(), new Date());
		dao.insert(user);
		
		Assert.assertEquals(true, user.getId().intValue()>0);
	}
	
	@Test
	public void testQueryUserByName() {
		User user = dao.queryUserByAccount(account);
		
		Assert.assertEquals(account, user.getAccount());
	}
	
	@Test
	public void queryUserByEmail() {
		User user = dao.queryUserByEmail(email);
		
		Assert.assertEquals(account, user.getAccount());
	}
	
	@Test
	public void deleteUserByName(){
		int count = dao.deleteUserByAccount(account);
		
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void loginByName(){
		User user = dao.loginByAccount(account, password);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(email, user.getEmail());
	}
	
	@Test
	public void loginByEmail(){
		User user = dao.loginByEmail(email, password);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(account, user.getAccount());
	}
}
