package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface VideoRepository {

    Observable<String> aidBvidToCid(String aid, String bvid);

    Observable<String> getPlayUrl(String aid, String bvid, String cid, String qn, String fnval, String fnver, String fourk);
}
