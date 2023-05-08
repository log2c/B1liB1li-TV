package com.github.log2c.b1lib1li_tv.ui.player;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;
import com.github.log2c.base.utils.Logging;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PlayerViewModel extends BaseCoreViewModel {
    private static final String TAG = PlayerViewModel.class.getSimpleName();
    private final VideoRepository videoRepository;
    public final SingleLiveEvent<PlayUrlModel> playUrlModelEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<File> concatEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<String> historyReportEvent = new SingleLiveEvent<>();
    private static final int DASH_MODE = 16; // H.265 ?
    private static final int MP4_MODE = 1;  // 仅 H.264 编码
    private static final int RESOLUTION_4K = 128;   // 需求4K分辨率
    public String bvid;
    public String aid;
    public String cid;
    public String danmukuPath;
    public int playerState = GSYVideoView.CURRENT_STATE_PREPAREING; // 播放器状态
    private Disposable historyReportSubscribe;

    public PlayerViewModel() {
        videoRepository = new VideoRepositoryImpl();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        cancelHistoryReportTimer();
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
                    startHistoryReportTimer();
                }, e -> {
                    ToastUtils.error("弹幕加载失败.");
                    parsePlayUrl();
                });
    }

    private void parsePlayUrl() {
        String qn = "120";  // 	4K 超清
        if (AppConfigRepository.getInstance().isUseIjkPlayer()) {   // IJK 不支持dash,Exo 忽略qn
            qn = String.valueOf(AppConfigRepository.getInstance().fetchVideoId());
        }
        String fnver = "0"; // 恒定值
        String fourk = "1"; // 允许4K视频
        videoRepository.getPlayUrl(aid, bvid, cid, qn, getFnVal() + "", fnver, fourk).subscribe(new BackendObserver<PlayUrlModel>() {
            @Override
            public void onSuccess(PlayUrlModel model) {
                playUrlModelEvent.postValue(model);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private int getFnVal() {
        if (AppConfigRepository.getInstance().isUseExoPlayer()) {
            return DASH_MODE | RESOLUTION_4K;
        }
        return MP4_MODE | RESOLUTION_4K;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void updateHistory(long duration) {
        final long playedTime = TimeUnit.MILLISECONDS.toSeconds(duration);
        videoRepository.historyReport(aid, bvid, cid, String.valueOf(playedTime), AppConfigRepository.getInstance().fetchUserMid())
                .subscribe(s -> Logging.i("播放进度上传完成! \t" + s), Throwable::printStackTrace);
    }

    /**
     * 处理生成IJK的多端Concat文件
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void processConcatVideo() {
        List<PlayUrlModel.DUrlModel> durl = playUrlModelEvent.getValue().getDurl();
        final String LINE_TEMPLATE = "file '%1$s'\nduration %2$s\n";
        final String HEADER = "ffconcat version 1.0\n";

        final StringBuilder sb = new StringBuilder(HEADER);
        for (PlayUrlModel.DUrlModel model : durl) {
            float seconds = ((float) model.getLength()) / 1000f;
            sb.append(String.format(LINE_TEMPLATE, model.getUrl(), seconds + ""));
        }
        Observable.just(sb.toString())
                .map((Function<String, File>) text -> {
                    final String concatCacheDir = PathUtils.join(PathUtils.getInternalAppCachePath(), "concat");
                    FileUtils.createOrExistsDir(concatCacheDir);
                    String path = PathUtils.join(concatCacheDir, bvid + ".concat");
                    FileIOUtils.writeFileFromString(path, text);
                    return FileUtils.getFileByPath(path);
                }).subscribeOn(Schedulers.io())
                .subscribe(concatEvent::postValue, Throwable::printStackTrace);
    }

    private void startHistoryReportTimer() {
        historyReportSubscribe = Observable.interval(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .filter(aLong -> playerState == GSYVideoView.CURRENT_STATE_PLAYING)
                .subscribe(s -> historyReportEvent.postValue(String.valueOf(s)), Throwable::printStackTrace);
    }

    private void cancelHistoryReportTimer() {
        try {
            historyReportSubscribe.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
