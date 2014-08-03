package com.kfcms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kfcms.dao.GameDao;
import com.kfcms.model.Game;
import com.kfcms.service.GameService;

@Service("gameService")
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;

	public List<Game> queryList(Integer num, Date startTime) {
		if (num == null) {
			num = 10;
		}
		if (startTime == null) {
			startTime = new Date();
		}
		
		return gameDao.queryList(num, startTime);
	}

	public List<Game> queryListByConditions(Integer page, Integer pageSize,
			String userName) {
		Integer offset = (page-1) * pageSize;
		
		Game game = new Game();
		game.setUserName(userName);
		
		return gameDao.queryListByConditions(offset, pageSize, game);
	}

	public int countListByConditions(String userName) {
		Game game = new Game();
		game.setUserName(userName);
		
		return gameDao.countListByConditions(game);
	}

	@Override
	public Game queryByIdAndUserName(Integer id, String userName) {
		Assert.notNull(id, "The id must not be null");
		Assert.hasLength(userName, "User name must not be empty");
		
		Game game = new Game();
		game.setId(id);
		game.setUserName(userName);
		
		return gameDao.query(game);
	}

	@Override
	public int insert(Game game) {
		Assert.notNull(game, "The game must not be null");
		Assert.hasLength(game.getUserName(), "User name must not be empty");
		
		game.setGmtCreated(new Date());
		game.setGmtModified(new Date());
		
		return gameDao.insert(game);
	}

	@Override
	public int update(Game game) {
		Assert.notNull(game, "The game must be not null");
		Assert.notNull(game.getId(), "The id must be not null");
		Assert.hasLength(game.getUserName(), "User name must not be empty");
		
		game.setGmtModified(new Date());
		
		return gameDao.update(game);
	}

	@Override
	public int deleteByIdAndUserName(Integer id, String userName) {
		Assert.notNull(id, "The id must be not null");
		Assert.hasLength(userName, "User name must not be empty");
		
		Game game = new Game();
		game.setId(id);
		game.setUserName(userName);
		
		return gameDao.delete(game);
	}
}
