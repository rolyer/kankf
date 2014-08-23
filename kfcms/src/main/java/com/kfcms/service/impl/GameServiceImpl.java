package com.kfcms.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kfcms.dao.GameDao;
import com.kfcms.model.Game;
import com.kfcms.service.GameService;
import com.kfcms.vo.GameVO;

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
			String account, String keyWord) {
		Integer offset = (page-1) * pageSize;
		
		Game game = new Game();
		game.setAccount(account);
		
		return gameDao.queryListByConditions(offset, pageSize, game, keyWord);
	}

	public int countListByConditions(String account, String keyWord) {
		Game game = new Game();
		game.setAccount(account);
		
		return gameDao.countListByConditions(game, keyWord);
	}

	public Game queryByIdAndAccount(Integer id, String account) {
		Assert.notNull(id, "The id must not be null");
		Assert.hasLength(account, "Account must not be empty");
		
		Game game = new Game();
		game.setId(id);
		game.setAccount(account);
		
		return gameDao.query(game);
	}

	public int insert(Game game) {
		Assert.notNull(game, "The game must not be null");
		Assert.hasLength(game.getAccount(), "Account must not be empty");
		
		game.setGmtCreated(new Date());
		game.setGmtModified(new Date());
		
		return gameDao.insert(game);
	}

	public int update(Game game) {
		Assert.notNull(game, "The game must be not null");
		Assert.notNull(game.getId(), "The id must be not null");
		Assert.hasLength(game.getAccount(), "Account must not be empty");
		
		game.setGmtModified(new Date());
		
		return gameDao.update(game);
	}

	public int deleteByIdAndAccount(Integer id, String account) {
		Assert.notNull(id, "The id must be not null");
		Assert.hasLength(account, "Account must not be empty");
		
		Game game = new Game();
		game.setId(id);
		game.setAccount(account);
		
		return gameDao.delete(game);
	}

	public int save(Game game) {
		Assert.notNull(game, "The game must not be null");
		
		if (game.getId()!=null && game.getId().intValue()>0) {
			return update(game);
		}else {
			return insert(game);
		}
	}

	public List<GameVO> queryListByStartTime(Integer page, Integer pageSize,
			Date startTime) {
		Integer offset = (page-1) * pageSize;
		
		Game game = new Game();
		game.setStartTime(startTime);
		game.setStatus(1);
		
		List<Game> list = gameDao.queryListByConditions(offset, pageSize, game, null);
		
		return transformation(list);
	}
	
	private List<GameVO> transformation(List<Game> games) {
		List<GameVO> list = new ArrayList<GameVO>();
		
		for (Game g : games) {
			GameVO gvo = new GameVO(g.getId(), g.getAccount(), g.getName(),
					g.getStartTime(), g.getServerName(), g.getUrl(),
					g.getCategory(), g.getGiftName(), g.getPlatform(),
					g.getGmtCreated(), g.getGmtModified());
			
			if (g.getStartTime()==null) {
				gvo.setServerStatus(0);
			} else {
				
				Calendar calendar = Calendar.getInstance(); 
				calendar.setTime(new Date());
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				
				if(calendar.getTimeInMillis() >= g.getStartTime().getTime()){
					gvo.setServerStatus(1);
				} else {
					gvo.setServerStatus(2);
				}
			}
			
			list.add(gvo);
		}
		
		return list;
	}

	public int countListByStartTime(Date startTime) {
		Game game = new Game();
		game.setStartTime(startTime);
		
		return gameDao.countListByConditions(game, null);
	}

	public List<GameVO> queryForSearch(Integer page, Integer pageSize, String key) {
		Integer offset = (page-1) * pageSize;
		
		List<Game> list = gameDao.queryListByConditions(offset, pageSize, null, key);
		
		return transformation(list);
	}

	public int countForSearch(String key) {
		return gameDao.countListByConditions(null, key);
	}
}
