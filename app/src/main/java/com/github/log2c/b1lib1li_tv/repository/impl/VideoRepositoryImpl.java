package com.github.log2c.b1lib1li_tv.repository.impl;

import com.github.log2c.b1lib1li_tv.network.NetKit;
import com.github.log2c.b1lib1li_tv.network.Urls;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;

import java.util.HashMap;

import io.reactivex.Observable;

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
}
