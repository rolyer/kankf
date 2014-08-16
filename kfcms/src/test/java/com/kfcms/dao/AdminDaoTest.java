package com.kfcms.dao;

import java.util.Date;
import java.util.List;

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
import com.kfcms.model.Admin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml",
		"classpath:test-spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/data/AdminDaoTest.xml")
@ActiveProfiles("hsqldb")
public class AdminDaoTest {

	@Autowired
	private AdminDao dao;

	private static final String account = "testuser";

	@Test
	public void testLogin() {
		Admin admin = dao.queryByAccount(account);

		Assert.assertNotNull(admin);
	}

	@Test
	public void testQueryAll() {
		List<Admin> list = dao.queryAll();

		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}

	private static final String password = "password";

	@Test
	public void testUpdatePassword() {
		int count = dao.updatePassword(account, password);

		Assert.assertEquals(1, count);
	}

	@Test
	public void testDeleteById() {
		int count = dao.deleteById(1);

		Assert.assertEquals(1, count);
	}

	@Test
	public void testInsert() {
		Admin admin = new Admin(account, password, new Date(), new Date());
		int count = dao.insert(admin);

		Assert.assertEquals(1, count);
	}
}
