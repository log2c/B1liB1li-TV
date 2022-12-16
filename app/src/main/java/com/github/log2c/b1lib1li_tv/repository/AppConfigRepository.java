package com.github.log2c.b1lib1li_tv.repository;

import static com.github.log2c.b1lib1li_tv.common.Constants.SP_NAME_CONFIG;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;

@SuppressWarnings("InstantiationOfUtilityClass")
public class AppConfigRepository {
    private static AppConfigRepository instance;
    private String mSessdata;
    private String mDedeUserId;

    private AppConfigRepository() {
    }

    public static AppConfigRepository getInstance() {
        if (instance == null) {
            instance = new AppConfigRepository();
        }
        return instance;
    }

    public String getDanmukuCacheDir() {
        final String dir = PathUtils.join(PathUtils.getInternalAppCachePath(), Constants.DANMUKU_CACHE_DIR);
        FileUtils.createOrExistsDir(dir);
        return dir;
    }

    public int getDynamicSpanCount() {
        return SPUtils.getInstance(Constants.SP_NAME_CONFIG).getInt(Constants.SP_DYNAMIC_SPAN_COUNT, Constants.DEFAULT_DYNAMIC_SPAN_COUNT);
    }

    public void storeSessdata(String data) {
        mSessdata = data;
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_BILIBILI_API_SESSDATA, data);
    }

    public String fetchSessdata() {
        if (StringUtils.isTrimEmpty(mSessdata)) {
            mSessdata = SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getString(Constants.SP_BILIBILI_API_SESSDATA, "");
        }
        return mSessdata;
    }

    public void storeUserMid(String mid) {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_BILIBILI_MID, mid);
    }

    public String fetchUserMid() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getString(Constants.SP_BILIBILI_MID, "");
    }

    public void storeDedeUserId(String dedeUserId) {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_BILIBILI_API_DEDEUSERID, dedeUserId);
    }

    public String fetchDedeUserId() {
        if (StringUtils.isTrimEmpty(mDedeUserId)) {
            mDedeUserId = SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getString(Constants.SP_BILIBILI_API_DEDEUSERID, "");
        }
        return mDedeUserId;
    }

    public void storeResolution(int quality) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DEFAULT_RESOLUTION, quality);
    }

    public int fetchDefaultResolution() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getInt(Constants.SP_DEFAULT_RESOLUTION, Constants.DEFAULT_RESOLUTION);
    }

    public void storeDanmakuToggle(boolean toggle) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DANMAKU_TOGGLE, toggle);
    }

    public boolean fetchDanmakuToggle() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Constants.SP_DANMAKU_TOGGLE, true);
    }

    private void storeDefaultCodec(String codec) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DEFAULT_CODEC, codec);
    }

    public void setDefaultH265Codec() {
        storeDefaultCodec(Constants.CODEC_H265);
    }

    public void setDefaultH264Codec() {
        storeDefaultCodec(Constants.CODEC_H264);
    }

    public String fetchDefaultCodec() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getString(Constants.SP_DEFAULT_CODEC, Constants.DEFAULT_CODEC);
    }

    public boolean isH265() {
        return Constants.CODEC_H265.equals(fetchDefaultCodec());
    }

    public boolean isH264() {
        return Constants.CODEC_H264.equals(fetchDefaultCodec());
    }
}
