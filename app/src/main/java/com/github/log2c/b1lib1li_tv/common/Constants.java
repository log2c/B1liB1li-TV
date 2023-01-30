package com.github.log2c.b1lib1li_tv.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1 Safari/605.1.15";
    public static final String SP_NAME_BILIBILI_API = "bilibili_api_data";
    public static final String SP_NAME_CONFIG = "app_config";
    public static final String SP_BILIBILI_API_SESSDATA = "sessdata";
    public static final String SP_BILIBILI_MID = "mid";
    public static final String SP_BILIBILI_API_DEDEUSERID = "DedeUserID";
    public static final String SP_BILIBILI_CSRF = "csrf";
    public static final String SP_DEFAULT_RESOLUTION = "default_resolution";
    public static final String SP_DANMAKU_TOGGLE = "danmaku";
    public static final String SP_DEFAULT_CODEC = "default_codec";
    public static final float SP_DEFAULT_DANMU_SIZE = 1f;
    public static final String SP_DANMU_SIZE = "danmu_size";
    public static final int DEFAULT_RESOLUTION = 80;    // 1080P高清
    public static final int DEFAULT_DYNAMIC_SPAN_COUNT = 3;
    public static final String REFERER = "https://www.bilibili.com";
    public static final Map<String, String> PLAYER_HEADERS = new HashMap<>();
    public static final int VIDEO_PARTITION_SIZE = 2;  // 每2个一组
    public static final int RESPONSE_CODE_OK = 0;
    public static final int RESPONSE_CODE_UN_LOGIN = -101;  // 账号未登录
    public static final String SP_DYNAMIC_SPAN_COUNT = "dynamic_span_count";
    public static final String DANMUKU_CACHE_DIR = "danmuku";   // 弹幕文件缓存文件夹
    public static final String DYNAMIC_TYPE_AV = "DYNAMIC_TYPE_AV";   // 类型

    public static final int NETWORK_REQUEST_RETRY_COUNT = 3;

    /**
     * 视频编码
     */
    public static final String CODEC_H264 = "H.264";
    public static final String CODEC_H265 = "H.265";
    public static final String DEFAULT_CODEC = CODEC_H265;

    static {
        PLAYER_HEADERS.put("referer", REFERER);
        PLAYER_HEADERS.put("User-Agent", DEFAULT_USER_AGENT);
    }

    public static final String[] COOKIE_ALLOWED_LIST = new String[]{"api.bilibili.com", "account.bilibili.com"};    // 需要自动加入Cookie的域名

    public static class Login {
        public static final int UN_SCAN = 86101;    //当密钥正确时但未扫描时
        public static final int SCANNED_NOT_CONFIRM = 86090;    //扫描成功但手机端未确认时
        public static final int QRCODE_EXPIRED = 86038;    //二维码失效
        public static final int PASS = 0;    //成功
    }

    public static class Resolution {
        public static final Map<Integer, String> ITEMS = new HashMap<>();

        static {
            ITEMS.put(6, "240P 极速");
            ITEMS.put(16, "360P 流畅");
            ITEMS.put(32, "480P 清晰");
            ITEMS.put(64, "720P 高清");
            ITEMS.put(74, "720P 60fps 高帧率");
            ITEMS.put(80, "1080P 高清");
            ITEMS.put(112, "1080P+ 高码率");
            ITEMS.put(116, "1080P 60fps 高帧率");
            ITEMS.put(120, "4K 超清");
            ITEMS.put(125, "HDR");
            ITEMS.put(126, "杜比视界");
            ITEMS.put(127, "8K 超高清");
        }
    }
}
