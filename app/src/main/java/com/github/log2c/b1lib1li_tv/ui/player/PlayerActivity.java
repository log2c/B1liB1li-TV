package com.github.log2c.b1lib1li_tv.ui.player;

import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_PAUSE;
import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_PLAYING;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPlayerBinding;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.ui.fragments.PlayerSettingDialogFragment;
import com.github.log2c.base.base.BaseCoreActivity;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.exo2.ExoMediaSourceInterceptListener;
import tv.danmaku.ijk.media.exo2.ExoSourceManager;

public class PlayerActivity extends BaseCoreActivity<PlayerViewModel, ActivityPlayerBinding> {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    public static final String INTENT_CID = "cid";
    private static final long UPLOAD_HISTORY_TIMER = 15 * 1000;
    private StandardGSYVideoPlayer videoView;
    private Timer timer;
    private boolean danmuLoaded;


    public static void showActivity(Activity context, @Nullable String bvid, @Nullable String aid, @Nullable String cid) {
        final Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(INTENT_BVID, bvid);
        intent.putExtra(INTENT_AID, aid);
        intent.putExtra(INTENT_CID, cid);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    public void initData() {
        viewModel.bvid = getIntent().getStringExtra(INTENT_BVID);
        viewModel.aid = getIntent().getStringExtra(INTENT_AID);
        viewModel.cid = getIntent().getStringExtra(INTENT_CID);
        viewModel.prepareAndStart();

        mBinding.player.setDanmakuShow(AppConfigRepository.getInstance().fetchDanmakuToggle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }


    private int[] preKeyCodes = new int[]{-1, -1};
    protected Dialog mProgressDialog;
    protected TextView mDialogCurrentTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogIcon;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i(TAG, "dispatchKeyEvent: " + event.getKeyCode() + ", Event: " + event.getAction());
        if (!isPadShown() && getCurrentFocus() instanceof RelativeLayout && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                doPauseOrStart();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private boolean isPadShown() {
        return mBinding.player.findViewById(R.id.layout_bottom).getVisibility() == View.VISIBLE;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (!isPadShown()) {
//                    ReflectUtils.reflect(mBinding.player).method("changeUiToPlayingShow");
//                    ReflectUtils.reflect(mBinding.player).method("cancelDismissControlViewTimer");
                    showMenuPopup();
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (isPadShown()) {
                    break;
                }
                if (videoView.getCurrentState() != CURRENT_STATE_PLAYING) {
                    Log.i(TAG, "On key down but not playing.");
                    return true;
                }
                Log.i(TAG, "On key down.");
                if (preKeyCodes[0] == -1) {    // 第一次
                    preKeyCodes[0] = keyCode;
                } else {
                    preKeyCodes[1] = keyCode;   // 无论后续回调多少次
                    showProgressDialog(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT, getNextPosition(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT), mBinding.player.getCurrentPlayer().getDuration());
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (isPadShown()) {
                    break;
                }
                if (videoView.getCurrentState() != CURRENT_STATE_PLAYING) {
                    Log.i(TAG, "onKeyUp but not playing.");
                    return true;
                }
                if (preKeyCodes[1] != -1 && preKeyCodes[0] == preKeyCodes[1]) {// 长按
                    Log.i(TAG, "onKeyUp: 长按");
                    seekByProgressDialog(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT);
                } else {    //短按
                    Log.i(TAG, "onKeyUp: 短按");
                    seekByStep(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT);
                }
                preKeyCodes = new int[]{-1, -1};
                dismissProgressDialog();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void seekByStep(boolean isForward) {
        int STEP = 5 * 1000;
        final long duration = mBinding.player.getCurrentPlayer().getCurrentPositionWhenPlaying();
        long time = isForward ? STEP + duration : duration - STEP;
        mBinding.player.getCurrentPlayer().seekTo(time);
        mBinding.player.seekTo(time);
    }

    private void seekByProgressDialog(boolean isForward) {
        final GSYVideoViewBridge manager = mBinding.player.getCurrentPlayer().getGSYVideoManager();
        final long time = dialogCurrentTimeToTime(isForward);
        manager.seekTo(time);
        dismissProgressDialog();
    }

    private long getNextPosition(boolean isForward) {
        long nowPosition = mBinding.player.getCurrentPlayer().getCurrentPositionWhenPlaying();
        if (mProgressDialog != null && mDialogCurrentTime != null && mProgressDialog.isShowing()) {
            long time = dialogCurrentTimeToTime(isForward);
            Log.i(TAG, "getNextPosition now: " + CommonUtil.stringForTime(nowPosition) + "," + CommonUtil.stringForTime(time));
            if (isForward) {
                time = Math.min(time, mBinding.player.getCurrentPlayer().getDuration());
            } else {
                time = Math.max(time, 0);
            }
            return time;
        }
        return nowPosition;
    }

    private long dialogCurrentTimeToTime(boolean isForward) {
        final int STEP = 5; // 每步10秒
        final String start = "1970-01-01 ";
        final long timeOffset = 28800000; // GMT+8时差
        final String str = mDialogCurrentTime.getText().toString();
        final boolean isHours = str.split(":").length == 3;
        String timeStr = isHours ? start + str : start + "00:" + str;
        final Date date = TimeUtils.string2Date(timeStr, "yyyy-MM-dd HH:mm:ss");
        final Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        instance.set(Calendar.SECOND, instance.get(Calendar.SECOND) + (isForward ? STEP : -STEP));
        return instance.getTime().getTime() + timeOffset;
    }

    protected void showProgressDialog(boolean isForward, long nowPosition, long totalTime) {
        if (mProgressDialog == null) {
            View localView = LayoutInflater.from(this).inflate(R.layout.dialog_progress_video_player, null);
            mDialogCurrentTime = localView.findViewById(R.id.tv_current);
            mDialogTotalTime = localView.findViewById(R.id.tv_duration);
            mDialogIcon = localView.findViewById(R.id.duration_image_tip);
            mProgressDialog = new Dialog(this, com.shuyu.gsyvideoplayer.R.style.video_style_dialog_progress);
            mProgressDialog.setContentView(localView);
            mProgressDialog.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
            mProgressDialog.getWindow().addFlags(32);
            mProgressDialog.getWindow().addFlags(16);
            mProgressDialog.getWindow().setLayout(mBinding.player.getWidth(), mBinding.player.getHeight());
            WindowManager.LayoutParams localLayoutParams = mProgressDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.TOP;
            localLayoutParams.width = mBinding.player.getWidth();
            localLayoutParams.height = mBinding.player.getHeight();
            final int[] location = new int[2];
            mBinding.player.getLocationOnScreen(location);
            localLayoutParams.x = location[0];
            localLayoutParams.y = location[1];
            mProgressDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
        if (mDialogCurrentTime != null) {
            mDialogCurrentTime.setText(CommonUtil.stringForTime(nowPosition));
        }
        if (mDialogTotalTime != null) {
            mDialogTotalTime.setText(CommonUtil.stringForTime(totalTime));
        }
        if (isForward) {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(com.shuyu.gsyvideoplayer.R.drawable.video_forward_icon);
            }
        } else {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(com.shuyu.gsyvideoplayer.R.drawable.video_backward_icon);
            }
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    private void doPauseOrStart() {
        if (isPlaying()) {
            mBinding.player.getCurrentPlayer().onVideoPause();
        } else {
            mBinding.player.getCurrentPlayer().onVideoResume();
        }
    }

    public boolean isPlaying() {
        return mBinding.player.getCurrentState() == CURRENT_STATE_PLAYING;
    }


    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        videoView = mBinding.player;
        viewModel.playUrlModelEvent.observe(this, this::loadVideo);
        videoView.setGSYStateUiListener(state -> {
            Log.i(TAG, "GSYStateUiListener: " + state);
            if (state == CURRENT_STATE_PLAYING) {   // 播放中
                loadDanmuku();
                if (timer != null) {
                    timer.purge();
                    timer.cancel();
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(() -> viewModel.updateHistory(videoView.getCurrentPlayer().getCurrentPositionWhenPlaying()));
                    }
                }, 0, UPLOAD_HISTORY_TIMER);
            } else if (state == CURRENT_STATE_PAUSE) {//暂停
                if (timer != null) {
                    timer.cancel();
                }
            }
        });
    }

    private void loadVideo(PlayUrlModel playUrlModel) {
        final String videoUrl = viewModel.getPlayUrl(playUrlModel);
        playVideo(videoUrl, viewModel.getAudioUrl(playUrlModel));
    }

    private void playVideo(String videoUrl, @Nullable String audioUrl) {
        if (StringUtils.isTrimEmpty(audioUrl)) {
            return;
        }
        setMediaSourceIfExoPlayer(videoUrl, audioUrl);

        mBinding.player.setUp(videoUrl, true, "");
        videoView.startPlayLogic();
    }

    private void setMediaSourceIfExoPlayer(String videoUrl, @NonNull String audioUrl) {
        if (!AppConfigRepository.getInstance().isExoPlayerDefault()) {
            return;
        }
        final DataSource.Factory factory = () -> {
            HttpDataSource dataSource = new DefaultHttpDataSource.Factory().setUserAgent(Constants.DEFAULT_USER_AGENT).createDataSource();
            for (String key : Constants.PLAYER_HEADERS.keySet()) {
                dataSource.setRequestProperty(key, Constants.PLAYER_HEADERS.get(key));
            }
            return dataSource;
        };
        final MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(MediaItem.fromUri(Uri.parse(videoUrl)));
        final MediaSource audioSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(MediaItem.fromUri(Uri.parse(audioUrl)));
        final MediaSource mediaSource = new MergingMediaSource(videoSource, audioSource);

        ExoSourceManager.setExoMediaSourceInterceptListener(new ExoMediaSourceInterceptListener() {
            /**
             * @param dataSource  链接
             * @param preview     是否带上header，默认有header自动设置为true
             * @param cacheEnable 是否需要缓存
             * @param isLooping   是否循环
             * @param cacheDir    自定义缓存目录
             * @return 返回不为空时，使用返回的自定义mediaSource
             */
            @Override
            public MediaSource getMediaSource(String dataSource, boolean preview, boolean cacheEnable, boolean isLooping, File cacheDir) {
                return mediaSource;
            }

            @Override
            public DataSource.Factory getHttpDataSourceFactory(String s, @Nullable TransferListener transferListener, int i, int i1, Map<String, String> map, boolean b) {
                return factory;
            }
        });
    }

    private void showMenuPopup() {
        if (viewModel.playUrlModelEvent.getValue() == null) {
            return;
        }
        final PlayUrlModel model = viewModel.playUrlModelEvent.getValue();
        if (model == null) {
            return;
        }

        final List<PlayUrlModel.DashModel.VideoModel> modelList = model.getDash().getVideo();
        List<String> items = new ArrayList<>();
        for (PlayUrlModel.DashModel.VideoModel m : modelList) {
            items.add(m.getWidth() + "x" + m.getHeight() + " fps: " + m.getFrameRate() + " " + m.getCodecs());
        }


        PlayerSettingDialogFragment dialogFragment = PlayerSettingDialogFragment.newInstance(items.toArray(new String[items.size()]), AppConfigRepository.getInstance().determinedVideo(modelList));
        dialogFragment.setConfigChangeCallback(new PlayerSettingDialogFragment.ConfigChangeCallback() {
            @Override
            public void onDanmuToggleChange() {
                final boolean toToggle = AppConfigRepository.getInstance().fetchDanmakuToggle();
                AppConfigRepository.getInstance().storeDanmakuToggle(toToggle);
                mBinding.player.setDanmakuShow(toToggle);
                if (toToggle) {
//                    viewModel.fetchDanmuku();
                    loadDanmuku();
                }
            }

            @Override
            public void onNeedReloadChange() {
//                loadVideo(viewModel.playUrlModelEvent.getValue());
                viewModel.prepareAndStart();
            }

            @Override
            public void onResolutionSelectChange(int position) {
                PlayUrlModel.DashModel.VideoModel videoModel = modelList.get(position);
                AppConfigRepository.getInstance().storeVideoParams(videoModel.getId(), videoModel.getCodecs());
                viewModel.prepareAndStart();
            }
        });
        dialogFragment.show(getSupportFragmentManager(), PlayerSettingDialogFragment.class.getSimpleName());
    }

    private void loadDanmuku() {
        if (!AppConfigRepository.getInstance().fetchDanmakuToggle()) {
            return;
        }
        if (danmuLoaded) {
            return;
        }
        if (TextUtils.isEmpty(viewModel.danmukuPath)) {
            return;
        }
        mBinding.player.setDanmaKuStream(new File(viewModel.danmukuPath));
        mBinding.player.getDanmakuView().seekTo(mBinding.player.getCurrentPlayer().getCurrentPositionWhenPlaying());
        danmuLoaded = true;
    }

}