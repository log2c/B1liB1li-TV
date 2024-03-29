package com.github.log2c.b1lib1li_tv.repository.impl;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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
    public Observable<String> historyReport(String aid, String bvid, String cid, String played_time, String mid) {
        return RxHttp.postForm(Urls.HISTORY_REPORT)
                .add("aid", aid)
                .add("bvid", bvid)
                .add("refer_url", Constants.REFERER)
                .add("cid", cid)
                .add("played_time", played_time)
                .add("mid", mid)
                .add("type", 3) // 3：投稿视频
                .add("play_type", 0)    // 0：播放中 1：开始播放 2：暂停 3：继续播放
//                .add("platform", "android")
//                .add("csrf", AppConfigRepository.getInstance().fetchCsrf())
                .toObservableResponse(String.class);
    }

    @Override
    public Observable<List<FeedModel.ItemsBean>> related(String bvid, String aid) {
        return RxHttp.get(Urls.RELATED)
                .addQuery("bvid", bvid)
                .addQuery("aid", aid)
                .toObservableResponse(FeedModel.ItemsBean[].class)
                .map((Function<FeedModel.ItemsBean[], List<FeedModel.ItemsBean>>) Arrays::asList)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
