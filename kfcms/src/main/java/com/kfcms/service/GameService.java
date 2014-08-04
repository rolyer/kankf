package com.kfcms.service;

import java.util.Date;
import java.util.List;

import com.kfcms.model.Game;

public interface GameService {
	public List<Game> queryList(Integer num, Date startTime);

	public List<Game> queryListByConditions(Integer page, Integer pageSize,
			String userName);

	public int countListByConditions(String userName);

	public Game queryByIdAndUserName(Integer id, String userName);

	public int insert(Game game);

	public int update(Game game);

	public int deleteByIdAndUserName(Integer id, String userName);
}
