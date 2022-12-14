package com.github.log2c.b1lib1li_tv.repository.impl;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoRepositoryImpl implements VideoRepository {
    @Override
    public Observable<String> aidBvidToCid(String aid, String bvid) {
        final HashMap<String, String> form = new HashMap<>();
        form.put("aid", aid);
        form.put("bvid", bvid);
        return NetKit.getInstance().doGetWithFormBodyRx(Urls.AID_BVID_TO_CID, form, null, null);
    }

    @Override
    public Observable<String> getPlayUrl(String aid, String bvid, String cid, String qn, String fnval, String fnver, String fourk) {
        final HashMap<String, String> form = new HashMap<>();
        form.put("aid", aid);
        form.put("bvid", bvid);
        form.put("cid", cid);
        form.put("qn", qn);
        form.put("fnval", fnval);
        form.put("fnver", fnver);
        form.put("fourk", fourk);
        return NetKit.getInstance().doGetWithFormBodyRx(Urls.GET_PLAY_URL, form, null, null);
    }

    @Override
    public Observable<String> videoView(String aid, String bvid) {
        final HashMap<String, String> form = new HashMap<>();
        form.put("aid", aid);
        form.put("bvid", bvid);
        return NetKit.getInstance().doGetWithFormBodyRx(Urls.VIDEO_VIEW, form, null, null);
    }

    @Override
    public Observable<String> fetchDanmukuLocalFilePath(String cid) {
        final String xmlFileName = cid + ".xml";
        final String xmlFullPath = PathUtils.join(AppConfigRepository.getInstance().getDanmukuCacheDir(), xmlFileName);
        if (FileUtils.isFileExists(xmlFullPath)) {
//            return Observable.just(xmlFullPath).observeOn(AndroidSchedulers.mainThread());
            FileUtils.delete(xmlFullPath);
        }
        final HashMap<String, String> form = new HashMap<>();
        form.put("oid", cid);
        return NetKit.getInstance().doGetWithFormBodyRx(Urls.DANMUKU, form, null, null).map(s -> {
            FileIOUtils.writeFileFromString(xmlFullPath, s);
            return xmlFullPath;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> historyReport(String aid, String bvid, String cid, String progress) {
        final HashMap<String, String> form = new HashMap<>();
        form.put("aid", aid);
        form.put("bvid", bvid);
        form.put("cid", cid);
        form.put("progress", progress); // ???
        form.put("platform", "android");
        form.put("csrf", AppConfigRepository.getInstance().fetchCsrf());
        return NetKit.getInstance().doPostRx(Urls.HISTORY_REPORT, null, null, form);
    }
}
