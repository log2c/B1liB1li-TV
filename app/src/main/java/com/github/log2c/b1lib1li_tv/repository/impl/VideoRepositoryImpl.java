package com.github.log2c.b1lib1li_tv.repository.impl;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rxhttp.network.RxHttp;

public class VideoRepositoryImpl implements VideoRepository {
    @Override
    public Observable<String> aidBvidToCid(String aid, String bvid) {
        return RxHttp.get(Urls.AID_BVID_TO_CID)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .toObservableResponse(String.class);
    }

    @Override
    public Observable<PlayUrlModel> getPlayUrl(String aid, String bvid, String cid, String qn, String fnval, String fnver, String fourk) {
        return RxHttp.get(Urls.GET_PLAY_URL)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .addQuery("cid", cid)
                .addQuery("qn", qn)
                .addQuery("fnval", fnval)
                .addQuery("fnver", fnver)
                .addQuery("fourk", fourk)
                .toObservableResponse(PlayUrlModel.class)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<VideoViewModel> videoView(String aid, String bvid) {
        return RxHttp.get(Urls.VIDEO_VIEW)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .toObservableResponse(VideoViewModel.class);
    }

    @Override
    public Observable<String> fetchDanmukuLocalFilePath(String cid) {
        final String xmlFileName = cid + ".xml";
        final String xmlFullPath = PathUtils.join(AppConfigRepository.getInstance().getDanmukuCacheDir(), xmlFileName);
        if (FileUtils.isFileExists(xmlFullPath)) {
//            return Observable.just(xmlFullPath).observeOn(AndroidSchedulers.mainThread());
            FileUtils.delete(xmlFullPath);
        }
        return RxHttp.get(Urls.DANMUKU)
                .addQuery("oid", cid)
                .toObservable(String.class)
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    FileIOUtils.writeFileFromString(xmlFullPath, s);
                    return xmlFullPath;
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<String> historyReport(String aid, String bvid, String cid, String progress) {
        return RxHttp.get(Urls.HISTORY_REPORT)
                .addQuery("aid", aid)
                .addQuery("bvid", bvid)
                .addQuery("cid", cid)
                .addQuery("progress", progress)
                .addQuery("platform", "android")
                .addQuery("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .toObservableResponse(String.class);
    }
}
