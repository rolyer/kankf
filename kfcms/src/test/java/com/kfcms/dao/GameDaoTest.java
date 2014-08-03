package com.kfcms.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kfcms.model.Game;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-spring-web.xml",
		"classpath:test-spring-mybatis.xml" })
public class GameDaoTest {

	@Autowired
	private GameDao dao;

	private final String username = "testuser";

	@Test
	public void testInsert() {
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
		game.setUserName(username);

		int count = dao.insert(game);

		Assert.assertEquals(1, count);

	}

	@Test
	public void testList() {
		List<Game> list = dao.queryList(10, new Date());

		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() >= 1);
	}

	@Test
	public void testQueryListByConditions() {
		Game game = new Game();
		game.setUserName(username);
		List<Game> list = dao.queryListByConditions(0, 10, game);

		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() >= 1);
	}

	@Test
	public void testCountListByConditions() {
		Game game = new Game();
		game.setUserName(username);
		int count = dao.countListByConditions(game);

		Assert.assertTrue(count >= 1);
	}
	
	@Test
	public void testQueryById() {
		Game game = new Game();
		game.setId(1);
		game.setUserName(username);
		
		Game g = dao.query(game);

		Assert.assertNotNull(g);
	}

	// @Test
	// public void testDelete() {
	// Game game = new Game();
	// game.setId(1);
	// game.setUserName(username);
	//
	// int count = dao.delete(game);
	//
	// Assert.assertEquals(1, count);
	// }

}
