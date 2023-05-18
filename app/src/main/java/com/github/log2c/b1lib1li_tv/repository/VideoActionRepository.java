package com.github.log2c.b1lib1li_tv.repository;

import io.reactivex.Observable;

public interface VideoActionRepository {
    Observable<Boolean> isLike(String aid, String bvid);

    Observable<Boolean> isAddCoin(String aid, String bvid);

    Observable<Boolean> isFavoured(String aid, String bvid);

    Observable<Boolean> like(String aid, String bvid, boolean like);

    Observable<Boolean> addCoin(String aid, String bvid, int multiply);

    Observable<Boolean> addFavor(String aid, String bvid);

    Observable<Boolean> triple(String aid, String bvid);
}
