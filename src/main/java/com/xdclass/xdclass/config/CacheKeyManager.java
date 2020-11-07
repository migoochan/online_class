package com.xdclass.xdclass.config;

public class CacheKeyManager {
    /**
     * 首页轮播图缓存key管理
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";

    /**
     * 首页视频列表缓存key管理
     */
    public static final String INDEX_VIDEO_KEY = "index:video:list";

    /**
     * 视频缓存key, %s是视频id
     */
    public static final String VIDEO_DETAIL = "index:detail:%s";
}
