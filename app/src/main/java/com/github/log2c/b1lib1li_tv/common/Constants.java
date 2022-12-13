package com.github.log2c.b1lib1li_tv.common;

public class Constants {
    public static final String SP_NAME_BILIBILI_API = "bilibili_api_data";
    public static final String SP_BILIBILI_API_SESSDATA = "sessdata";
    public static final String SP_BILIBILI_API_DEDEUSERID = "DedeUserID";
    public static final int RESPONSE_CODE_OK = 0;

    public static final String[] COOKIE_ALLOWED_LIST = new String[]{"api.bilibili.com", "account.bilibili.com"};    // 需要自动加入Cookie的域名

    public static class Login {
        public static final int UN_SCAN = 86101;    //当密钥正确时但未扫描时
        public static final int SCANNED_NOT_CONFIRM = 86090;    //扫描成功但手机端未确认时
        public static final int QRCODE_EXPIRED = 86038;    //二维码失效
        public static final int PASS = 0;    //成功
    }
}
