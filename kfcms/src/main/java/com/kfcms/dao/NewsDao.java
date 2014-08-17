package com.kfcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kfcms.model.News;

public interface NewsDao {
	public int insert(News news);

	public int deleteById(Integer id);

	public int update(News news);

	public News findTopOneByCategory(@Param("category") Integer category);
	
	public News findOne(Integer id);
	
	public List<News> queryListByConditions(@Param("offset") Integer offset,
			@Param("rowCount") Integer rowCount, @Param("news") News news);

	public int countListByConditions(@Param("news") News news);
}
