package com.kfcms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
