package com.kfcms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kfcms.dao.NewsDao;
import com.kfcms.model.News;
import com.kfcms.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	public int insert(News news) {
		return newsDao.insert(news);
	}

	public int deleteById(Integer id) {
		return newsDao.deleteById(id);
	}

	public int update(News news) {
		return newsDao.update(news);
	}

	public News findTopOneByCategory(Integer category) {
		return newsDao.findTopOneByCategory(category);
	}

	public News findOne(Integer id) {
		return newsDao.findOne(id);
	}

	public List<News> queryListByConditions(Integer page, Integer pageSize,
			News news) {

		Integer offset = (page - 1) * pageSize;

		return newsDao.queryListByConditions(offset, pageSize, news);
	}

	public int countListByConditions(News news) {
		return newsDao.countListByConditions(news);
	}

	public int save(News news) {
		if (news.getId()==null) {
			return insert(news);
		} else {
			return update(news);
		}
	}
}
