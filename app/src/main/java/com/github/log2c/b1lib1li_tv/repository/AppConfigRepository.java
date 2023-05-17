package com.github.log2c.b1lib1li_tv.repository;

import static com.github.log2c.b1lib1li_tv.common.Constants.SP_NAME_CONFIG;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.github.log2c.b1lib1li_tv.R;
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
        return SPUtils.getInstance(Constants.SP_NAME_CONFIG).getInt(Utils.getApp().getString(R.string.pre_key_dynamic_span_count), Constants.DEFAULT_DYNAMIC_SPAN_COUNT);
    }


    public void storeUserMid(String mid) {
        SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).put(Constants.SP_BILIBILI_MID, mid);
    }

    public String fetchUserMid() {
        return SPUtils.getInstance(Constants.SP_NAME_BILIBILI_API).getString(Constants.SP_BILIBILI_MID, "");
    }

    public void storeDanmakuToggle(boolean toggle) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Utils.getApp().getString(R.string.pre_key_danmu_toggle), toggle);
    }

    public boolean fetchDanmakuToggle() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Utils.getApp().getString(R.string.pre_key_danmu_toggle), true);
    }

    public String fetchDefaultCodec() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getString(
                Utils.getApp().getString(R.string.pre_key_codecs), Constants.DEFAULT_CODEC);
    }

    public float fetchDanmuSize() {
        return Float.parseFloat(SPUtils.getInstance(Constants.SP_NAME_CONFIG).getString(Utils.getApp().getString(R.string.pre_key_danmu_size), Constants.DEFAULT_DANMU_SIZE + ""));
    }

    public boolean isUseExoPlayer() {
        return SPUtils.getInstance(Constants.SP_NAME_CONFIG)
                .getString(
                        Utils.getApp().getString(R.string.pre_key_media_player),
                        Utils.getApp().getString(R.string.pre_value_exo_player))
                .equals(Utils.getApp().getString(R.string.pre_value_exo_player));
    }

    public boolean isHardwareDecoding() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Utils.getApp().getString(R.string.pre_key_hardware_decoding), true);
    }

    public boolean isUseAV1() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Utils.getApp().getString(R.string.pre_key_use_av1), false);
    }

    public boolean isEnableDebugView() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Utils.getApp().getString(R.string.pre_key_enable_debug_view), false);
    }

    public boolean isAutoCheckUpdate() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getBoolean(Utils.getApp().getString(R.string.pre_key_auto_check_update), false);
    }

    public boolean isUseIjkPlayer() {
        return !isUseExoPlayer();
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

//    public void storeVideoParams(int dashVideoId, String codecs) {
//        SPUtils.getInstance(SP_NAME_CONFIG).put(Utils.getApp().getString(R.string.pre_key_video_id), dashVideoId);
//        SPUtils.getInstance(SP_NAME_CONFIG).put(Utils.getApp().getString(R.string.pre_key_codecs), codecs);
//    }

    public void storeCodecs(String codecs) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Utils.getApp().getString(R.string.pre_key_codecs), codecs);
    }

    public int fetchVideoId() {
        return SPUtils.getInstance(SP_NAME_CONFIG).getInt(Utils.getApp().getString(R.string.pre_key_video_id), 120);
    }

    public int determinedVideoInDashMode(final List<PlayUrlModel.DashModel.VideoModel> modelList) {
        String[] split = SPUtils.getInstance(SP_NAME_CONFIG).getString(Utils.getApp().getString(R.string.pre_key_codecs), "hevc").split("$$");
        final int id = split.length == 1 ? 9999 : Integer.parseInt(split[0]);
        String codecs;
        if (split.length == 1) {
            codecs = split[0];
        } else {
            codecs = split[1];
        }
//        final int id = SPUtils.getInstance(SP_NAME_CONFIG).getInt(Utils.getApp().getString(R.string.pre_key_video_id), 9999);
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

    public File getIjkCacheFile() {
        final String dir = PathUtils.join(PathUtils.getInternalAppCachePath(), "ijk_video_cache");
        FileUtils.createOrExistsDir(dir);
        return FileUtils.getFileByPath(dir);
    }
}
