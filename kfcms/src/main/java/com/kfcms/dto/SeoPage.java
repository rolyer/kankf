package com.kfcms.dto;

public class SeoPage {
	private String title;
	private String keywords;
	private String description;
	private String currentPage;
	
	public SeoPage(String title, String keywords, String description,
			String currentPage) {
		this.title = title;
		this.keywords = keywords;
		this.description = description;
		this.currentPage = currentPage;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
}
