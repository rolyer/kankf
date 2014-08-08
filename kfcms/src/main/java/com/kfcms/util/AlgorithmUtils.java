package com.kfcms.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AlgorithmUtils {
	/**
	 * MD5加密
	 * @param s 
	 * @param length
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String MD5(String s, int length)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		StringBuffer hexValue = new StringBuffer();
		byte[] md5Bytes = md5.digest(s.getBytes("utf-8"));
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		if (length == 32) {
			return hexValue.toString();
		} else {
			return hexValue.toString().substring(8, 24);
		}
	}
}
