package com.github.log2c.b1lib1li_tv.ui.player;

import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_PAUSE;
import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_PLAYING;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPlayerBinding;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
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
        viewModel.parsePlayUrl();

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
            case KeyEvent.KEYCODE_UNKNOWN:
                showMenuPopup();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Log.i(TAG, "On key down.");
                if (preKeyCodes[0] == -1) {    // 第一次
                    preKeyCodes[0] = keyCode;
                } else {
                    preKeyCodes[1] = keyCode;   // 无论后续回调多少次
                    showProgressDialog(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT, getNextPosition(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT), mBinding.player.getCurrentPlayer().getDuration());
                }
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                doPauseOrStart();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            case KeyEvent.KEYCODE_DPAD_LEFT:
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
        viewModel.danmukuLoadedEvent.observe(this, xmlPath -> mBinding.player.setDanmaKuStream(new File(xmlPath)));

        videoView.setGSYStateUiListener(state -> {
            Log.i(TAG, "GSYStateUiListener: " + state);
            if (state == CURRENT_STATE_PLAYING) {   // 播放中
                if (timer != null) {
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
        if (viewModel.isDashMode(playUrlModel)) {
            playVideo(videoUrl, viewModel.getAudioUrl(playUrlModel));
        } else playVideo(videoUrl, null);
    }

    private void playVideo(String videoUrl, @Nullable String audioUrl) {
        if (StringUtils.isTrimEmpty(audioUrl)) {
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

        mBinding.player.setUp(videoUrl, true, "");
        videoView.startPlayLogic();
    }

    private void showMenuPopup() {
        final String[] menu = new String[]{"视频清晰度", "弹幕开关", "视频编码"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);
        builder.setTitle("选择").setItems(menu, (dialog, which) -> {
            if (which == 0) {
                showResolutionSetting();
            } else if (which == 1) {
                showDanmuSetting();
            } else if (which == 2) {
                showCodecSetting();
            }
            dialog.dismiss();
        });
        builder.create().show();
    }

    private void showCodecSetting() {
        final boolean isH265 = AppConfigRepository.getInstance().isH265();
        String str = isH265 ? "H.265" : "H.264";
        String btnStr = isH265 ? "H.264" : "H.265";
        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("当前编码: \t").append(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(com.github.log2c.base.R.color.colorDanger));

        spannableString.setSpan(colorSpan, spannableString.length() - str.length(), spannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(ConvertUtils.sp2px(28)), spannableString.length() - str.length(), spannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        builder.setTitle("编码选择").setMessage(spannableString).setPositiveButton("取消", (dialog, which) -> dialog.dismiss()).setNegativeButton(btnStr, (dialog, which) -> {
            if (isH265) {
                AppConfigRepository.getInstance().setDefaultH264Codec();
            } else {
                AppConfigRepository.getInstance().setDefaultH265Codec();
            }
            loadVideo(viewModel.playUrlModelEvent.getValue());
        }).create().show();
    }

    private void showDanmuSetting() {
        final boolean enable = AppConfigRepository.getInstance().fetchDanmakuToggle();
        String str = enable ? "开" : "关";
        String btnStr = enable ? "关" : "开";
        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("当前弹幕状态: \t").append(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(com.github.log2c.base.R.color.colorDanger));

        spannableString.setSpan(colorSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(ConvertUtils.sp2px(28)), spannableString.length() - 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        builder.setTitle("弹幕开关").setMessage(spannableString).setPositiveButton("取消", (dialog, which) -> dialog.dismiss()).setNegativeButton(btnStr, (dialog, which) -> {
            final boolean toToggle = !enable;
            AppConfigRepository.getInstance().storeDanmakuToggle(toToggle);
            mBinding.player.setDanmakuShow(toToggle);
            if (toToggle) {
                viewModel.fetchDanmuku();
            }
        }).create().show();
    }

    private void showResolutionSetting() {
        final PlayUrlModel model = viewModel.playUrlModelEvent.getValue();
        if (model == null) {
            return;
        }
        final List<String> items = new ArrayList<>();
        List<Integer> supported = viewModel.getCurrentSupportResolution();
        final List<Integer> resolutions = new ArrayList<>(Constants.Resolution.ITEMS.keySet());
        for (Integer quality : resolutions) {
            String str = "";
            if (!supported.contains(quality)) {
                str = "  (当前视频不支持)";
            }
            items.add(Constants.Resolution.ITEMS.get(quality) + str);
        }
        String[] menus = new String[items.size()];
        items.toArray(menus);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);
        builder.setTitle("请选择分辨率").setItems(menus, (dialog, which) -> {
            AppConfigRepository.getInstance().storeResolution(resolutions.get(which));
            loadVideo(viewModel.playUrlModelEvent.getValue());
        });
        builder.create().show();
    }
}