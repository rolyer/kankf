package com.kfcms.service;

import java.util.List;

import com.kfcms.model.Game;

public interface GameService {
	public List<Game> queryList(Integer num);
}