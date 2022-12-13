package com.github.log2c.b1lib1li_tv.ui.player;

import static com.github.log2c.b1lib1li_tv.common.Constants.SP_NAME_CONFIG;
import static com.github.log2c.b1lib1li_tv.common.Constants.VIDEO_PARTITION_SIZE;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends BaseCoreViewModel {
    private final VideoRepository videoRepository;
    public SingleLiveEvent<PlayUrlModel> playUrlModelEvent = new SingleLiveEvent<>();
    public String bvid;
    public String aid;
    public String cid;

    public PlayerViewModel() {
        videoRepository = new VideoRepositoryImpl();
    }

    public void parsePlayUrl() {
        String qn = "120";
        String fnval = "16";
        String fnver = "0";
        String fourk = "1";
        videoRepository.getPlayUrl(aid, bvid, cid, qn, fnval, fnver, fourk).subscribe(new LocalObserver<PlayUrlModel>() {
            @Override
            public void onSuccess(PlayUrlModel model) {
                playUrlModelEvent.postValue(model);
            }

            @Override
            public void onException(Throwable e) {

            }
        });
    }

    public String getDefaultResolution(PlayUrlModel model) {
        int partitionSize = VIDEO_PARTITION_SIZE;
        final List<List<PlayUrlModel.DashModel.VideoModel>> partitions = new ArrayList<>();
        final List<PlayUrlModel.DashModel.VideoModel> videoModelList = model.getDash().getVideo();
        for (int i = 0; i < videoModelList.size(); i += partitionSize) {
            partitions.add(videoModelList.subList(i, Math.min(i + partitionSize, videoModelList.size())));
        }
        final int selectRes = SPUtils.getInstance(SP_NAME_CONFIG).getInt(Constants.SP_DEFAULT_RESOLUTION, Constants.DEFAULT_RESOLUTION);
        if (model.getAccept_quality().contains(selectRes)) {
            return partitions.get(model.getAccept_quality().indexOf(selectRes)).get(0).getBaseUrl();//TODO 根据不同解码器返回对应url
        }
        final ArrayList<Integer> list = new ArrayList<>(model.getAccept_quality());
        CollectionUtils.filter(list, item -> item < selectRes);
        if (!list.isEmpty()) {
            final int fallbackId = list.get(0);
            for (PlayUrlModel.DashModel.VideoModel videoModel : model.getDash().getVideo()) {
                if (videoModel.getId() == fallbackId) {
                    return videoModel.getBaseUrl();
                }
            }
        }
        return model.getDash().getVideo().get(model.getDash().getVideo().size() - 1).getBaseUrl();
    }
}
