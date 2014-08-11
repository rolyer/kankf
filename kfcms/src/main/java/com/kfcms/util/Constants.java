package com.kfcms.util;

public class Constants {
	public final static Integer PAGE_SIZE = 20;
	
	public final static String LOGIN_USER = "user";
	public final static String LOGIN_ADMIN = "admin";

	public enum RegisterStatus {
		SUCCESS, DUPLICATE_NAME, DUPLICATE_EMAIL
	}
}
