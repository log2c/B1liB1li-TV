package com.github.log2c.b1lib1li_tv.ui.player;

import static com.github.log2c.b1lib1li_tv.common.Constants.VIDEO_PARTITION_SIZE;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
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
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tv.danmaku.ijk.media.exo2.ExoMediaSourceInterceptListener;
import tv.danmaku.ijk.media.exo2.ExoSourceManager;

public class PlayerActivity extends BaseCoreActivity<PlayerViewModel, ActivityPlayerBinding> {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    public static final String INTENT_CID = "cid";
    private StandardGSYVideoPlayer videoView;
    private boolean dashMode;


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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
            case KeyEvent.KEYCODE_UNKNOWN:
                showMenuPopup();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                doForward();
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                doPauseOrStart();
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void doPauseOrStart() {
        if (isPlaying()) {
            mBinding.player.getCurrentPlayer().onVideoPause();
        } else {
            mBinding.player.getCurrentPlayer().onVideoResume();
        }
    }

    public boolean isPlaying() {
        return mBinding.player.getCurrentState() == GSYVideoView.CURRENT_STATE_PLAYING;
    }

    /**
     * 快进
     */
    private void doForward() {
        if (isPlaying()) {
            final GSYVideoViewBridge manager = mBinding.player.getCurrentPlayer().getGSYVideoManager();
            final long seek = 5 * 1000;
            Log.d(TAG, "doForward: current " + manager.getCurrentPosition());
            manager.seekTo(manager.getCurrentPosition() + seek);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
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

        viewModel.playUrlModelEvent.observe(this, playUrlModel -> {
            final String videoUrl = viewModel.getDefaultResolution(playUrlModel);
            if (playUrlModel.getDash() != null && playUrlModel.getDash().getAudio() != null && !playUrlModel.getDash().getAudio().isEmpty()) {
                playVideo(videoUrl, playUrlModel.getDash().getAudio().get(0).getBaseUrl());
            } else playVideo(videoUrl, null);
        });
        viewModel.danmukuLoadedEvent.observe(this, xmlPath -> mBinding.player.setDanmaKuStream(new File(xmlPath)));

        videoView.setGSYStateUiListener(state -> {
            Log.i(TAG, "GSYStateUiListener: " + state);
        });
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
        final String[] menu = new String[]{"视频清晰度", "弹幕开关"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);
        builder.setTitle("选择").setItems(menu, (dialog, which) -> {
            if (which == 0) {
                showResolutionSetting();
            } else {
                showDanmuSetting();
            }
            dialog.dismiss();
        });
        builder.create().show();
    }

    private void showDanmuSetting() {
        final boolean enable = mBinding.player.isShowDanmaku();
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
        for (Integer quality : model.getAccept_quality()) {
            if (Constants.Resolution.ITEMS.containsKey(quality)) {
                items.add(Constants.Resolution.ITEMS.get(quality));
            } else {
                items.add("未适配分辨率");
            }
        }
        String[] menus = new String[items.size()];
        items.toArray(menus);

        dashMode = false;
        List<List<PlayUrlModel.DashModel.VideoModel>> partitions = new ArrayList<>();

        if (model.getDash() != null && model.getDash().getVideo() != null && model.getDash().getVideo().size() > 0) {
            dashMode = true;
            int partitionSize = VIDEO_PARTITION_SIZE;
            final List<PlayUrlModel.DashModel.VideoModel> videoModelList = model.getDash().getVideo();
            for (int i = 0; i < videoModelList.size(); i += partitionSize) {
                partitions.add(videoModelList.subList(i, Math.min(i + partitionSize, videoModelList.size())));
            }
            if (partitions.size() != menus.length) {
                throw new RuntimeException("showResolutionSetting: ");
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.github.log2c.base.R.style.AppTheme_Dialog);
        builder.setTitle("请选择分辨率").setItems(menus, (dialog, which) -> {
            AppConfigRepository.getInstance().storeResolution(model.getAccept_quality().get(which));
            if (dashMode) {
                if (model.getDash() != null && model.getDash().getAudio() != null && !model.getDash().getAudio().isEmpty()) {
                    playVideo(partitions.get(which).get(0).getBaseUrl(), model.getDash().getAudio().get(0).getBaseUrl());
                } else playVideo(partitions.get(which).get(0).getBaseUrl(), null);
            } else {

                if (model.getDash() != null && model.getDash().getAudio() != null && !model.getDash().getAudio().isEmpty()) {
                    playVideo(model.getDurl().get(which).getUrl(), model.getDash().getAudio().get(0).getBaseUrl());
                } else playVideo(model.getDurl().get(which).getUrl(), null);
            }
        });
        builder.create().show();
    }
}