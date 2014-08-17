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
import com.kfcms.model.Game;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml",
		"classpath:test-spring-mybatis.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/data/GameDaoTest.xml")
@ActiveProfiles("hsqldb")
public class GameDaoTest {

	@Autowired
	private GameDao dao;

	private final String account = "testuser";
	private final String gamename = "testgame";

	@Test
	public void queryListByConditions() {
		Integer offset = 0;
		Integer rowCount = 20;
		List<Game> list = dao.queryListByConditions(offset, rowCount, null);

		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		Assert.assertEquals(gamename, list.get(0).getName());

		// test row count
		list = dao.queryListByConditions(offset, 1, null);

		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(gamename, list.get(0).getName());

		// query with user name
		Game game = new Game();
		game.setAccount(account);
		list = dao.queryListByConditions(offset, rowCount, game);

		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void countListByConditions() {
		Game game = new Game();
		// query without user name
		Integer count = dao.countListByConditions(game);

		Assert.assertEquals(5, count.intValue());

		// query with user name
		game.setAccount(account);
		count = dao.countListByConditions(game);

		Assert.assertEquals(3, count.intValue());
	}

	@Test
	public void query() {
		Game game = new Game();
		game.setId(2);
		game.setAccount(account);

		Game g = dao.query(game);

		Assert.assertNotNull(g);
		Assert.assertEquals(2, g.getId().intValue());
	}

	@Test
	public void insert() {
		Game game = new Game();
		game.setCategory("2D");
		game.setGiftName("giftName");
		game.setGmtCreated(new Date());
		game.setGmtModified(new Date());
		game.setName("name");
		game.setPlatform("platform");
		game.setServerName("serverName");
		game.setStartTime(new Date());
		game.setUrl("url");
		game.setAccount(account);

		Integer count = dao.insert(game);

		Assert.assertEquals(1, count.intValue());
	}

	@Test
	public void update() {
		Game game = new Game();
		game.setId(2);
		game.setCategory("2D");
		game.setGiftName("giftName");
		game.setGmtCreated(new Date());
		game.setGmtModified(new Date());
		game.setName("name");
		game.setPlatform("platform");
		game.setServerName("serverName");
		game.setStartTime(new Date());
		game.setUrl("url");
		game.setAccount(account);
		
		Integer count = dao.update(game);
		
		Assert.assertEquals(1, count.intValue());
		
		//update by incorrect user
		game.setAccount(account+"#^@#%^@");
		
		count = dao.update(game);
		
		Assert.assertEquals(0, count.intValue());
	}

	@Test
	public void delete() {
		Game game = new Game();
		game.setId(3);
		game.setAccount(account);
		
		Integer count = dao.delete(game);
		
		Assert.assertEquals(1, count.intValue());
		
		//delete by incorrect user
		game.setAccount(account+"#^@#%^@");
		
		count = dao.delete(game);
		
		Assert.assertEquals(0, count.intValue());
	}
	
	@Test
	public void queryListWithoutStartDateDate(){
		List<Game> list = dao.queryList(10, null);
		
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}
}
