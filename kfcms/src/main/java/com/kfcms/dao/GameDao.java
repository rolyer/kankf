package com.kfcms.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.Game;

public interface GameDao {
	public List<Game> queryList(@Param("num") Integer num,
			@Param("startTime") Date startTime);

	public List<Game> queryListByConditions(@Param("offset") Integer offset,
			@Param("rowCount") Integer rowCount, @Param("game") Game game, @Param("keyWord") String keyWord);

	public int countListByConditions(@Param("game") Game game, @Param("keyWord") String keyWord);

	public Game query(Game game);

	public int insert(Game game);

	public int update(Game game);

	public int delete(Game game);
}
