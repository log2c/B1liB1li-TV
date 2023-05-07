package com.github.log2c.b1lib1li_tv.repository;

import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;

import java.util.List;

import io.reactivex.Observable;

public interface VideoRepository {

    Observable<String> aidBvidToCid(String aid, String bvid);

    Observable<PlayUrlModel> getPlayUrl(String aid, String bvid, String cid, String qn, String fnval, String fnver, String fourk);

    Observable<VideoViewModel> videoView(String aid, String bvid);

    Observable<String> fetchDanmukuLocalFilePath(String cid);

    Observable<String> historyReport(String aid, String bvid, String cid, String played_time, String mid);

    Observable<List<FeedModel.ItemsBean>> related(String bvid, String aid);

}
