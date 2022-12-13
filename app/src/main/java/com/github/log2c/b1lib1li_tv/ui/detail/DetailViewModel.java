package com.github.log2c.b1lib1li_tv.ui.detail;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.model.VideoViewModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

public class DetailViewModel extends BaseCoreViewModel {
    private final VideoRepository videoRepository;
    public final SingleLiveEvent<VideoViewModel> viewModelLiveEvent = new SingleLiveEvent<>();
    public String bvid;
    public String aid;

    public DetailViewModel() {
        videoRepository = new VideoRepositoryImpl();
    }

    public void getVideoView() {
        if (StringUtils.isTrimEmpty(bvid) && StringUtils.isTrimEmpty(aid)) {
            showErrorToast("Aid 与 Bvid 不能同时为空！");
            return;
        }
        videoRepository.videoView(aid, bvid)
                .subscribe(new LocalObserver<VideoViewModel>() {
                    @Override
                    public void onSuccess(VideoViewModel model) {
                        viewModelLiveEvent.postValue(model);
                    }

                    @Override
                    public void onException(Throwable e) {

                    }
                });
    }
}
