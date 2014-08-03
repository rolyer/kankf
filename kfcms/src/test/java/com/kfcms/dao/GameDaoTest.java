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
@ContextConfiguration(locations = { "classpath:test-spring-web.xml", "classpath:test-spring-mybatis.xml" })
public class GameDaoTest {


	@Autowired
	private GameDao dao;
	
	@Test
	public void testList() {
		List<Game> list = dao.queryList(10, new Date());
		
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testList2() {
		Game game = new Game();
		game.setUserName("test");
		List<Game> list = dao.queryListByGame(0, 10, game);
		
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}

}
