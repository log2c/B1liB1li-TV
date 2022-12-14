package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface VideoRepository {

    Observable<String> aidBvidToCid(String aid, String bvid);

    Observable<String> getPlayUrl(String aid, String bvid, String cid, String qn, String fnval, String fnver, String fourk);

    Observable<String> videoView(String aid, String bvid);

    Observable<String> fetchDanmukuLocalFilePath(String cid);

    Observable<String> historyReport(String aid, String bvid, String cid, String progress);

}
