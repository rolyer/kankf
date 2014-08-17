package com.kfcms.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

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
import com.kfcms.model.News;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml", "classpath:test-spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/data/NewsDaoTest.xml")
@ActiveProfiles("hsqldb")
public class NewsDaoTest {
	
	@Autowired
	private NewsDao dao;

	@Test
	public void testInsert() {
		News news = new News("account", 1, "title", "content", 1, new Date(), new Date());
		int actual = dao.insert(news);
		
		Assert.assertEquals(1, actual);
	}

	@Test
	public void testDeleteById() {
		int actual = dao.deleteById(1);
		Assert.assertEquals(1, actual);
	}

	@Test
	public void testUpdate() {
		News news = new News("account3", 2, "title3", "content3", 9, new Date(), new Date());
		news.setId(1);
		int actual = dao.update(news);
		
		Assert.assertEquals(1, actual);
	}

	@Test
	public void testFindTopOneByCategory() {
		News news = dao.findTopOneByCategory(1);
		
		Assert.assertNotNull(news);
		Assert.assertEquals("testuser3", news.getAccount());
	}

	@Test
	public void testFindOne() {
		News news = dao.findOne(1);
		
		Assert.assertNotNull(news);
		Assert.assertEquals("testuser", news.getAccount());
	}

	@Test
	public void testQueryListByConditions() {
		Integer offset = 0;
		Integer rowCount = 20;
		
		News news = new News();
		news.setCategory(1);
		
		List<News> list = dao.queryListByConditions(offset, rowCount, news);
		
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testCountListByConditions() {
		News news = new News();
		news.setCategory(1);
		
		int count = dao.countListByConditions(news);
		
		Assert.assertEquals(2, count);
	}

}
