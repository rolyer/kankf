package com.kfcms.util;

public enum UserStatus {
	UNCKECKED(0), CHECKED(1), LOCKED(2);

	private final Integer value;

	private UserStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
