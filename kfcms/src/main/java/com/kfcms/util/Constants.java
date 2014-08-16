package com.kfcms.util;

public class Constants {
	public static final Integer PAGE_SIZE = 20;
	public static final Integer MD5_LENGTH = 16;
	
	public static final String LOGIN_USER = "LOGIN_USER";
	public static final String LOGIN_ADMIN = "LOGIN_ADMIN";
	
	//messages
	public static final String MSG_USER_NO_LOGIN = "对不起，您还没有登录或已超时，请重新登录！";
	
	public static final String MSG_NO_NEW_PASSWORD = "新密码不能为空！";
	public static final String MSG_NO_SECOND_PASSWORD = "原密码不能为空！";
	public static final String MSG_PASSWORD_NOT_MATCH = "您输入的密码不正确！";

	public enum RegisterStatus {
		SUCCESS, DUPLICATE_NAME, DUPLICATE_EMAIL
	}
}
