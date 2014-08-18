package com.kfcms.util;

public enum NewsCategory {
	HOT_GAME(0), NOTICE(1), NEWS(2);

    private final int value;
    
    private NewsCategory(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
