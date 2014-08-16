package com.kfcms.model;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String account;
	private String password;
	private Date gmtCreated;
	private Date gmtModified;

	public Admin() {
	}

	public Admin(String account, String password, Date gmtCreated,
			Date gmtModified) {
		this.account = account;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
