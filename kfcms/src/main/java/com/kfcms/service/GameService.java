package com.kfcms.service;

import java.util.Date;
import java.util.List;

import com.kfcms.model.Game;
import com.kfcms.vo.GameVO;

public interface GameService {
	public List<Game> queryList(Integer num, Date startTime);

	public List<Game> queryListByConditions(Integer page, Integer pageSize,
			String userName);

	public int countListByConditions(String account);

	public Game queryByIdAndAccount(Integer id, String account);

	public int insert(Game game);

	public int update(Game game);

	public int deleteByIdAndAccount(Integer id, String account);
	
	public int save(Game game);
	
	public List<GameVO> queryListByStartTime(Integer page, Integer pageSize, Date startTime);

	public int countListByStartTime(Date startTime);
}
