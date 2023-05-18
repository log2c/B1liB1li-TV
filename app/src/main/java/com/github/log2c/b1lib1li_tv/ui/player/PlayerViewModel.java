package com.github.log2c.b1lib1li_tv.ui.player;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoActionRepository;
import com.github.log2c.b1lib1li_tv.repository.VideoRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoActionRepositoryImpl;
import com.github.log2c.b1lib1li_tv.repository.impl.VideoRepositoryImpl;
import com.github.log2c.b1lib1li_tv.utils.MPDUtil;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;
import com.github.log2c.base.utils.Logging;

import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
public class PlayerViewModel extends BaseCoreViewModel {
    private static final String TAG = PlayerViewModel.class.getSimpleName();
    private final VideoRepository videoRepository;
    private final VideoActionRepository videoActionRepository;
    public final SingleLiveEvent<File> concatEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<String[]> playUrlEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<String> historyReportEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<Boolean[]> videoActionEvent = new SingleLiveEvent<>(); // 点赞、投币、收藏状态
    private static final int DASH_MODE = 16; // H.265 ?
    private static final int MP4_MODE = 1;  // 仅 H.264 编码
    private static final int RESOLUTION_4K = 128;   // 需求4K分辨率
    private static final int AV1_CODEC = 2048;   // 请求AV1编码
    public String bvid;
    public String aid;
    public String cid;
    public String danmukuPath;
    private Disposable historyReportSubscribe;
    public PlayUrlModel mPlayUrlModel;

    public PlayerViewModel() {
        videoRepository = new VideoRepositoryImpl();
        videoActionRepository = new VideoActionRepositoryImpl();
        videoActionEvent.setValue(new Boolean[]{false, false, false});
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        cancelHistoryReportTimer();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void prepareAndStart() {
        fetchVideoActionInfo();
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

    @SuppressLint("CheckResult")
    private void fetchVideoActionInfo() {
        Observable.zip(videoActionRepository.isLike(aid, bvid),
                        videoActionRepository.isAddCoin(aid, bvid),
                        videoActionRepository.isFavoured(aid, bvid),
                        (b0, b1, b2) -> new Boolean[]{b0, b1, b2})
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoActionEvent::postValue, Throwable::printStackTrace);
    }

    private void parsePlayUrl() {
        String qn = "120";  // 	4K 超清
        int fnval = (DASH_MODE | RESOLUTION_4K);
        if (AppConfigRepository.getInstance().isUseAV1()) {
            fnval = fnval | AV1_CODEC;
        }
        String fnver = "0"; // 恒定值
        String fourk = "1"; // 允许4K视频
        videoRepository.getPlayUrl(aid, bvid, cid, qn, fnval + "", fnver, fourk)
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new BackendObserver<PlayUrlModel>() {
                    @Override
                    public void onSuccess(PlayUrlModel model) {
//                playUrlModelEvent.postValue(model);
                        playUrlEvent.postValue(new String[0]);
                        mPlayUrlModel = model;
                        loadPlayResource();
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtils.error(e.toString());
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void loadPlayResource() {
        int idx = AppConfigRepository.getInstance().determinedVideoInDashMode(mPlayUrlModel.getDash().getVideo());
        final PlayUrlModel.DashModel.VideoModel videoModel = mPlayUrlModel.getDash().getVideo().get(idx);
        final PlayUrlModel.DashModel.AudioModel audioModel = mPlayUrlModel.getDash().getAudio().get(0);
        final String videoUrl = videoModel.getBaseUrl();
        final String audioUrl = audioModel.getBaseUrl();

        if (AppConfigRepository.getInstance().isUseExoPlayer()) {
            playUrlEvent.postValue(new String[]{videoUrl, audioUrl});
            return;
        }

        String mediaPresentationDuration = Duration.of(mPlayUrlModel.getTimelength(), ChronoUnit.MILLIS).toString();
        String minBufferTime = Duration.of((long) Math.floor(mPlayUrlModel.getDash().getMin_buffer_time() + 0.5), ChronoUnit.SECONDS).toString();
        String video_mimeType = videoModel.getMimeType();
        String video_codecs = videoModel.getCodecs();
        String video_media = StringEscapeUtils.escapeXml10(videoModel.getBaseUrl());
        String audio_media = StringEscapeUtils.escapeXml10(audioUrl);
        String video_duration = mPlayUrlModel.getTimelength() / 1000 + "";
        String video_id = videoModel.getId() + "";
        String video_bandwidth = videoModel.getBandwidth() + "";
        String video_width = videoModel.getWidth() + "";
        String video_height = videoModel.getHeight() + "";
        String video_frameRate = videoModel.getFrameRate() + "";
        String audio_mimeType = audioModel.getMimeType();
        String audio_codecs = audioModel.getCodecs();
        String audio_duration = mPlayUrlModel.getTimelength() / 1000 + "";
        String audio_id = audioModel.getId() + "";
        String audio_bandwidth = audioModel.getBandwidth() + "";

        MPDUtil.genMpdText(bvid,
                        mediaPresentationDuration,
                        minBufferTime,
                        video_codecs,
                        video_media,
                        video_duration,
                        video_mimeType,
                        video_id,
                        video_bandwidth,
                        video_width,
                        video_height,
                        video_frameRate,
                        audio_mimeType,
                        audio_codecs,
                        audio_duration,
                        audio_id,
                        audio_bandwidth,
                        audio_media)
                .observeOn(Schedulers.io())
                .subscribe(s -> playUrlEvent.postValue(new String[]{s}), e -> Logging.e(e, "MPD构建异常."));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void updateHistory(long duration) {
        final long playedTime = TimeUnit.MILLISECONDS.toSeconds(duration);
        videoRepository.historyReport(aid, bvid, cid, String.valueOf(playedTime), AppConfigRepository.getInstance().fetchUserMid())
                .subscribe(s -> Logging.i("播放进度上传完成! \t" + s), Throwable::printStackTrace);
    }

    private void startHistoryReportTimer() {
        historyReportSubscribe = Observable.interval(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> historyReportEvent.postValue(String.valueOf(s)), Throwable::printStackTrace);
    }

    private void cancelHistoryReportTimer() {
        try {
            historyReportSubscribe.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("CheckResult")
    public void actionLike() {
        videoActionRepository.like(aid, bvid, !Objects.requireNonNull(videoActionEvent.getValue())[0])
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> fetchVideoActionInfo(), e -> ToastUtils.error("点赞失败."));
    }

    @SuppressLint("CheckResult")
    public void addCoin(int multiply) {
        videoActionRepository.addCoin(aid, bvid, multiply)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> fetchVideoActionInfo(), e -> ToastUtils.error("投币失败."));
    }

    @SuppressLint("CheckResult")
    public void addFavor() {
        videoActionRepository.addFavor(aid, bvid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> fetchVideoActionInfo(), e -> ToastUtils.error("收藏失败."));
    }

    @SuppressLint("CheckResult")
    public void triple() {
        videoActionRepository.triple(aid, bvid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> fetchVideoActionInfo(), e -> ToastUtils.error("三连失败."));
    }
}
