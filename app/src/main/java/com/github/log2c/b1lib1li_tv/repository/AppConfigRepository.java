package com.github.log2c.b1lib1li_tv.repository;

import static com.github.log2c.b1lib1li_tv.common.Constants.SP_NAME_CONFIG;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.base.utils.Logging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cookie.ICookieJar;

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

    public List<Cookie> fetchCookies() {
        ICookieJar iCookieJar = (ICookieJar) RxHttpPlugins.getOkHttpClient().cookieJar();
        HttpUrl httpUrl = HttpUrl.parse(Urls.LOGIN_DOMAIN);
        return iCookieJar.loadCookie(httpUrl);
    }

    public boolean isLogin() {
        return fetchCookies().size() > 0;
    }

    public String getDanmukuCacheDir() {
        final String dir = PathUtils.join(PathUtils.getInternalAppCachePath(), Constants.DANMUKU_CACHE_DIR);
        FileUtils.createOrExistsDir(dir);
        return dir;
    }

    public int getDynamicSpanCount() {
        return SPUtils.getInstance(Constants.SP_NAME_CONFIG).getInt(Constants.SP_DYNAMIC_SPAN_COUNT, Constants.DEFAULT_DYNAMIC_SPAN_COUNT);
    }


    public void storeUserMid(String mid) {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_BILIBILI_MID, mid);
    }

    public String fetchUserMid() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getString(Constants.SP_BILIBILI_MID, "");
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


    public void storeDanmuSize(float size) {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_DANMU_SIZE, size);
    }

    public float fetchDanmuSize() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getFloat(Constants.SP_DANMU_SIZE, Constants.SP_DEFAULT_DANMU_SIZE);
    }

    public void setExoPlayerDefault() {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_MEDIA_PLAYER, Constants.MEDIA_PLAYER_EXOPLAYER);
    }

    public boolean isUseExoPlayer() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getInt(Constants.SP_MEDIA_PLAYER, Constants.DEFAULT_DEFAULT_MEDIA_PLAYER) == Constants.MEDIA_PLAYER_EXOPLAYER;
    }

    public boolean isUseIjkPlayer() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getInt(Constants.SP_MEDIA_PLAYER, Constants.DEFAULT_DEFAULT_MEDIA_PLAYER) != Constants.MEDIA_PLAYER_IJKPLAYER;
    }

    public void setIjkPlayerDefault() {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_MEDIA_PLAYER, Constants.MEDIA_PLAYER_IJKPLAYER);
    }

    public void processCookie() {
        ICookieJar iCookieJar = (ICookieJar) RxHttpPlugins.getOkHttpClient().cookieJar();
        final List<Cookie> cookies = AppConfigRepository.getInstance().fetchCookies();
        for (String domain : Urls.OTHER_DOMAIN_LIST) {
            final HttpUrl httpUrl = HttpUrl.parse(domain);
            iCookieJar.saveCookie(httpUrl, cookies);
        }
        Logging.i("Cookie跨域处理完成");
    }

    public void storeVideoParams(int dashVideoId, String codecs) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DASH_VIDEO_ID, dashVideoId);
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DASH_CODECS, codecs);
    }

    public int fetchVideoId() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getInt(Constants.SP_DASH_VIDEO_ID, 120);
    }

    public int determinedVideoInExoMode(final List<PlayUrlModel.DashModel.VideoModel> modelList) {
        final int id = SPUtils.getInstance(SP_NAME_CONFIG).getInt(Constants.SP_DASH_VIDEO_ID, 9999);
        final String codecs = SPUtils.getInstance(SP_NAME_CONFIG).getString(Constants.SP_DASH_CODECS, "hevc");
        final List<PlayUrlModel.DashModel.VideoModel> filterList = new ArrayList<>(modelList);

        CollectionUtils.filter(filterList, item -> item.getId() == id);

        if (filterList.size() > 0) {
            for (PlayUrlModel.DashModel.VideoModel model : filterList) {
                if (codecs.equalsIgnoreCase(model.getCodecs())) {
                    return modelList.indexOf(model);
                }
            }
            return modelList.indexOf(filterList.get(0));
        }

        filterList.addAll(modelList);
        CollectionUtils.filter(filterList, item -> item.getId() < id);
        return filterList.size() > 0 ? modelList.indexOf(filterList.get(0)) : 0;
    }

    //    public String[] determinedVideoInIjkMode(final List<PlayUrlModel.DUrlModel> modelList) {
//        String[] urls = new String[modelList.size()];
//        for (int i = 0; i < modelList.size(); i++) {
//            urls[i] = modelList.get(i).getUrl();
//        }
//        return urls;
//    }
    public int determinedVideoInIjkMode(PlayUrlModel model) {
        return model.getAccept_quality().indexOf(model.getQuality());
    }

//    public int determinedVideo(final PlayUrlModel model) {
//        if (isUseExoPlayer()) {
//            return determinedVideoInExoMode(model.getDash().getVideo());
//        }
//        return determinedVideoInIjkMode(model.getDurl());
//    }

    public File getIjkCacheFile(){
        final String dir = PathUtils.join(PathUtils.getInternalAppCachePath(), "ijk_video_cache");
        FileUtils.createOrExistsDir(dir);
        return FileUtils.getFileByPath(dir);
    }
}
