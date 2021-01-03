package com.innomes.main.config;

public class CacheKey {
	public static final int DEFAULT_EXPIRE_SEC = 60; // 1 minutes
    public static final String USER = "user";
    public static final int USER_EXPIRE_SEC = 60 * 5; // 5 minutes
    public static final String BOARD = "board";
    public static final int BOARD_EXPIRE_SEC = 60 * 10; // 10 minutes
    public static final String POST = "post";
    public static final String POSTS = "posts";
    public static final int POST_EXPIRE_SEC = 60 * 5; // 5 minutes
    public static final String ITEM = "item";
    public static final int ITEM_EXPIRE_SEC = 60 * 30; // 30 minutes
    public static final String CODE = "code";
    public static final int CODE_EXPIRE_SEC = 60 * 30; // 30 minutes
}
