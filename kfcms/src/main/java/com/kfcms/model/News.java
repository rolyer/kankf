package com.kfcms.model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String account;
	/**
	 * 类型： 0 热门游戏 1 公告 2 新闻
	 */
	private Integer category;
	private String title;
	private String content;
	/**
	 * 状态： 0 未发布 1 已发布
	 */
	private Integer status;
	private Date gmtCreated;
	private Date gmtModified;

	public News() {
	}

	public News(String account, Integer category, String title,
			String content, Integer status, Date gmtCreated, Date gmtModified) {
		this.account = account;
		this.category = category;
		this.title = title;
		this.content = content;
		this.status = status;
		this.gmtCreated = gmtCreated;
		this.gmtModified = gmtModified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
