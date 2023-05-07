package com.github.log2c.b1lib1li_tv.ui.player;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;
import com.github.log2c.base.utils.Logging;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlayerViewModel extends BaseCoreViewModel {
    private static final String TAG = PlayerViewModel.class.getSimpleName();
    private final VideoRepository videoRepository;
    public final SingleLiveEvent<PlayUrlModel> playUrlModelEvent = new SingleLiveEvent<>();
    private static final int DASH_MODE = 16; // H.265 ?
    private static final int MP4_MODE = 1;  // 仅 H.264 编码
    private static final int RESOLUTION_4K = 128;   // 需求4K分辨率
    public String bvid;
    public String aid;
    public String cid;
    public String danmukuPath;
    private Disposable mHistorySubscribe;

    public PlayerViewModel() {
        videoRepository = new VideoRepositoryImpl();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        try {
            mHistorySubscribe.dispose();
        } catch (Exception ignore) {
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void prepareAndStart() {
        videoRepository.fetchDanmukuLocalFilePath(cid)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    danmukuPath = s;
                    parsePlayUrl();
                }, e -> {
                    ToastUtils.error("弹幕加载失败.");
                    parsePlayUrl();
                });
    }

    private void parsePlayUrl() {
        String qn = "120";  // 	4K 超清
        int fnval = AppConfigRepository.getInstance().isH265() ? DASH_MODE | RESOLUTION_4K : MP4_MODE | RESOLUTION_4K;
        String fnver = "0"; // 恒定值
        String fourk = "1"; // 允许4K视频
        videoRepository.getPlayUrl(aid, bvid, cid, qn, fnval + "", fnver, fourk).subscribe(new BackendObserver<PlayUrlModel>() {
            @Override
            public void onSuccess(PlayUrlModel model) {
                playUrlModelEvent.postValue(model);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    @SuppressWarnings("unchecked")
    public String getPlayUrl(PlayUrlModel playModel) {
        int idx = AppConfigRepository.getInstance().determinedVideo(playModel.getDash().getVideo());
        return playModel.getDash().getVideo().get(idx).getBaseUrl();
    }

    public String getAudioUrl(PlayUrlModel playUrlModel) {
        return playUrlModel.getDash().getAudio().get(0).getBaseUrl();
    }

    public void updateHistory(long duration) {
        final long playedTime = TimeUnit.MILLISECONDS.toSeconds(duration);
        Logging.i("当前播放进度: " + playedTime + " 秒");
        mHistorySubscribe = videoRepository.historyReport(aid, bvid, cid, String.valueOf(playedTime), AppConfigRepository.getInstance().fetchUserMid())
                .subscribe(s -> Logging.i("播放进度上传完成! \t" + s), e -> Logging.e(TAG, e.getMessage()));
    }
}
