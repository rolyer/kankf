package com.kfcms.dao;

import java.util.List;

import com.kfcms.model.Game;

public interface GameDao {
	public List<Game> queryList(Integer num);
}
