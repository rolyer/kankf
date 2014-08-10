package com.kfcms.vo;

import java.io.Serializable;
import java.util.Date;

public class GameVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String name;
	private Date startTime;
	private String serverName;
	private String url;
	private String category;
	private String giftName;
	private String platform;
	private Date gmtCreated;
	private Date gmtModified;
	private int serverStatus;
	
	public GameVO(Integer id, String userName, String name, Date startTime,
			String serverName, String url, String category, String giftName,
			String platform, Date gmtCreated, Date gmtModified) {
		
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.startTime = startTime;
		this.serverName = serverName;
		this.url = url;
		this.category = category;
		this.giftName = giftName;
		this.platform = platform;
		this.gmtCreated = gmtCreated;
		this.gmtModified = gmtModified;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
	
	public int getServerStatus() {
		return serverStatus;
	}
	
	public void setServerStatus(int serverStatus) {
		this.serverStatus = serverStatus;
	}
}
