package com.kfcms.service;

import java.util.List;

import com.kfcms.model.News;

public interface NewsService {
	public int insert(News news);

	public int deleteById(Integer id);

	public int update(News news);

	public News findTopOneByCategory(Integer category);

	public News findOne(Integer id);

	public List<News> queryListByConditions(Integer page, Integer pageSize, News news);

	public int countListByConditions(News news);
	
	public int save(News news);
}
