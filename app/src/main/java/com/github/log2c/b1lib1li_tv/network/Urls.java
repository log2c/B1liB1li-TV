package com.github.log2c.b1lib1li_tv.network;

public class Urls {
    public static final String LOGIN_DOMAIN = "https://passport.bilibili.com";
    public static final String[] OTHER_DOMAIN_LIST = new String[]{
            "https://api.bilibili.com",
            "https://account.bilibili.com"};
    public static final String GENERATE_QRCODE = "https://passport.bilibili.com/x/passport-login/web/qrcode/generate";
    public static final String LOGIN = "https://passport.bilibili.com/x/passport-login/web/qrcode/poll";
    public static final String NAV_STAT = "https://api.bilibili.com/x/web-interface/nav/stat";
    public static final String NAV_USER_INFO = "https://api.bilibili.com/x/web-interface/nav";   // 导航栏用户信息
    public static final String GET_COIN = "https://account.bilibili.com/site/getCoin";
    public static final String FEED_ALL = "https://api.bilibili.com/x/polymer/web-dynamic/v1/feed/all";
    public static final String FEED_VIDEO = "https://api.bilibili.com/x/polymer/web-dynamic/desktop/v1/feed/video";
    public static final String AID_BVID_TO_CID = "https://api.bilibili.com/x/player/pagelist";
    public static final String GET_PLAY_URL = "https://api.bilibili.com/x/player/playurl";// 获取视频流URL（web端）
    public static final String TO_VIEW = "https://api.bilibili.com/x/v2/history/toview";
    public static final String HISTORY = "https://api.bilibili.com/x/web-interface/history/cursor";
    public static final String VIDEO_VIEW = "https://api.bilibili.com/x/web-interface/view"; // 获取视频详细信息(web端)
    public static final String DANMUKU = "https://api.bilibili.com/x/v1/dm/list.so"; // https://api.bilibili.com/x/v1/dm/list.so
    public static final String HISTORY_REPORT = "https://api.bilibili.com/x/click-interface/web/heartbeat"; // 播放记录上传
    public static final String SPACE_SEARCH_VIDEOS = "https://api.bilibili.com/x/space/arc/list"; // 查询用户投稿视频明细

    public static final String RELATED = "https://api.bilibili.com/x/web-interface/archive/related"; // 推荐

    public static final String HAS_LIKE = "https://api.bilibili.com/x/web-interface/archive/has/like";
    public static final String HAS_ADD_COIN = "https://api.bilibili.com/x/web-interface/archive/coins";
    public static final String HAS_FAVOUR = "https://api.bilibili.com/x/v2/fav/video/favoured";
    public static final String ADD_LIKE = "https://api.bilibili.com/x/web-interface/archive/like";
    public static final String ADD_COIN = "https://api.bilibili.com/x/web-interface/coin/add";
    public static final String ADD_FAVOUR = "https://api.bilibili.com/x/v3/fav/resource/deal";
    public static final String ADD_TRIPLE = "https://api.bilibili.com/x/web-interface/archive/like/triple";
    public static final String CREATED_FAVOUR_LIST = "https://api.bilibili.com/x/v3/fav/folder/created/list-all";
    public static final String FAVOUR_ALL_BY_ID = "https://api.bilibili.com/x/v3/fav/resource/list";
    public static final String RELATION_TAGS = "https://api.bilibili.com/x/relation/tags";
    public static final String RELATION_TAG_DETAIL = "https://api.bilibili.com/x/relation/tag";
}
