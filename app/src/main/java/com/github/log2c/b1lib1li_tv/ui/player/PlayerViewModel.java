package com.github.log2c.b1lib1li_tv.ui.player;

import android.annotation.SuppressLint;
import android.util.Log;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.StringUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.common.VideoUtils;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.utils.Logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerViewModel extends BaseCoreViewModel {
    private static final String TAG = PlayerViewModel.class.getSimpleName();
    private final VideoRepository videoRepository;
    public final SingleLiveEvent<PlayUrlModel> playUrlModelEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<String> danmukuLoadedEvent = new SingleLiveEvent<>();
    private static final int DASH_MODE = 16; // H.265 ?
    private static final int MP4_MODE = 1;  // 仅 H.264 编码
    private static final int RESOLUTION_4K = 128;   // 需求4K分辨率
    public String bvid;
    public String aid;
    public String cid;

    public PlayerViewModel() {
        videoRepository = new VideoRepositoryImpl();
    }

    public void parsePlayUrl() {
        String qn = "120";  // 	4K 超清
        int fnval = AppConfigRepository.getInstance().isH265() ? DASH_MODE | RESOLUTION_4K : MP4_MODE | RESOLUTION_4K;
        String fnver = "0"; // 恒定值
        String fourk = "1"; // 允许4K视频
        videoRepository.getPlayUrl(aid, bvid, cid, qn, fnval + "", fnver, fourk).subscribe(new LocalObserver<PlayUrlModel>() {
            @Override
            public void onSuccess(PlayUrlModel model) {
                playUrlModelEvent.postValue(model);
                fetchDanmuku();
            }

            @Override
            public void onException(Throwable e) {
                Log.e(TAG, e.getMessage());
            }
        });
    }

    public boolean isDashMode(PlayUrlModel model) {
        return getFinalPlayModel(model) instanceof List;
    }

    public ArrayList<Integer> getCurrentSupportResolution() {
        if (playUrlModelEvent.getValue() == null) {
            return new ArrayList<>();
        }
        final ArrayList<Integer> resolutions = new ArrayList<>();
        final Object model = getFinalPlayModel(playUrlModelEvent.getValue());
        if (model instanceof List) {
            for (PlayUrlModel.DashModel.VideoModel video : playUrlModelEvent.getValue().getDash().getVideo()) {
                resolutions.add(video.getId());
            }
        } else {
            resolutions.add(playUrlModelEvent.getValue().getQuality());
        }
        ArrayList<Integer> list = new ArrayList<>(new HashSet<>(resolutions));
        Collections.reverse(list);
        Collections.sort(list, (o1, o2) -> o2 - o1);
        return list;
    }

    @SuppressWarnings("unchecked")
    public String getPlayUrl(PlayUrlModel playModel) {
        final Object model = getFinalPlayModel(playModel);
        if (model instanceof List) {
            List<PlayUrlModel.DashModel.VideoModel> videos = (List<PlayUrlModel.DashModel.VideoModel>) model;
            Logging.i("最终分辨率: " + Constants.Resolution.ITEMS.get(videos.get(0).getId()) + ", 编码: " + videos.get(0).getCodecs());
            return videos.get(0).getBaseUrl();
        } else {
            PlayUrlModel.DUrlModel video = (PlayUrlModel.DUrlModel) model;
            Logging.i("最终分辨率: " + Constants.Resolution.ITEMS.get(playModel.getQuality()) + ", 编码: ?");
            return video.getUrl();
        }
    }

    public int getPlayResolutionCode() {
        if (playUrlModelEvent.getValue() == null) {
            return -1;
        }
        final Object model = getFinalPlayModel(playUrlModelEvent.getValue());
        if (model instanceof List) {
            List<PlayUrlModel.DashModel.VideoModel> videos = (List<PlayUrlModel.DashModel.VideoModel>) model;
            return videos.get(0).getId();
        } else {
            return playUrlModelEvent.getValue().getQuality();
        }
    }

    /**
     * 返回最终的Model
     *
     * @param model model, instanceof DUrlModel or List<PlayUrlModel.DashModel.VideoModel>
     * @return 返回可能两种情况
     */
    private Object getFinalPlayModel(PlayUrlModel model) {
        if (model.getDurl() != null && model.getDurl().size() > 0) {  // 如果存在，则表示Dash为空
            return model.getDurl().get(0);
        }
        final boolean h265 = AppConfigRepository.getInstance().isH265();
        final int resolution = AppConfigRepository.getInstance().fetchDefaultResolution();
        final List<PlayUrlModel.DashModel.VideoModel> data = determinedModel(model, h265);
        CollectionUtils.filter(data, item -> item.getId() <= resolution);
        if (data.isEmpty()) {
            return Collections.singletonList(model.getDash().getVideo().get(h265 ? 0 : 1));
        }
        return data;
    }

    private List<PlayUrlModel.DashModel.VideoModel> determinedH264Model(PlayUrlModel model) {
        return determinedModel(model, false);
    }

    private static List<PlayUrlModel.DashModel.VideoModel> determinedH265Model(PlayUrlModel model) {
        return determinedModel(model, true);
    }

    private static List<PlayUrlModel.DashModel.VideoModel> determinedModel(PlayUrlModel model, boolean isH265) {
        if (model.getDash() == null || model.getDash().getVideo() == null) {
            return new ArrayList<>();
        }
        final List<PlayUrlModel.DashModel.VideoModel> satisfyVideoList = new ArrayList<>(model.getDash().getVideo());
        CollectionUtils.filter(satisfyVideoList, item -> (isH265 ? VideoUtils.isH265(item.getCodecs()) : VideoUtils.isH264(item.getCodecs())));
        return satisfyVideoList;
    }

    private PlayUrlModel.DashModel.VideoModel getSuggestCodecsUrl(List<PlayUrlModel.DashModel.VideoModel> models) {
        for (PlayUrlModel.DashModel.VideoModel model : models) {
            if (model.getCodecs().contains("hev")) {
                return model;
            }
        }
        return models.get(0);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void fetchDanmuku() {
        if (StringUtils.isTrimEmpty(cid) || !AppConfigRepository.getInstance().fetchDanmakuToggle()) {
            return;
        }
        videoRepository.fetchDanmukuLocalFilePath(cid).subscribe(danmukuLoadedEvent::postValue);
    }

    public String getAudioUrl(PlayUrlModel playUrlModel) {
        return playUrlModel.getDash().getAudio().get(0).getBaseUrl();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void updateHistory(long duration) {
        final long progress = TimeUnit.MILLISECONDS.toSeconds(duration);
        Logging.i("当前播放进度: " + progress + " 秒");
        videoRepository.historyReport(aid, bvid, cid, String.valueOf(progress))
                .subscribe(s -> Logging.i("播放进度上传完成! \t" + s), e -> Log.e(TAG, e.getMessage()));
    }
}
