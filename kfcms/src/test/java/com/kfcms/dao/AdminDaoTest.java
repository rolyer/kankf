package com.kfcms.dao;

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
@ContextConfiguration(locations = { "classpath:test-spring-web.xml", "classpath:test-spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/data/AdminDaoTest.xml")
@ActiveProfiles("hsqldb")
public class AdminDaoTest {

	@Autowired
	private AdminDao dao;
	
	@Test
	public void testLogin() {
		Admin admin = dao.queryByAccount("testuser");
		
		Assert.assertNotNull(admin);
	}

}
