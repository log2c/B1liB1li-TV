package com.github.log2c.b1lib1li_tv.ui.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.blankj.utilcode.util.GsonUtils;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPlayerBinding;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.model.ResolutionModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.List;

import me.jessyan.autosize.internal.CancelAdapt;

public class PlayerActivity extends BaseCoreActivity<PlayerViewModel, ActivityPlayerBinding> implements CancelAdapt {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    public static final String PLAYER_DATA_INTENT_FILTER = "player_data_intent_filter";
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    public static final String INTENT_CID = "cid";
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

//        mBinding.player.setDanmakuShow(AppConfigRepository.getInstance().fetchDanmakuToggle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        viewModel.playUrlEvent.observe(this, this::onPlayUrlChange);
//        viewModel.historyReportEvent.observe(this, s -> viewModel.updateHistory(videoView.getCurrentPlayer().getCurrentPositionWhenPlaying()));
    }

    private void onPlayUrlChange(String[] urls) {
        if (urls.length == 2) {
            new Thread(() -> {
                Intent intent = new Intent(PLAYER_DATA_INTENT_FILTER);
                intent.putExtra("video", urls[0]);
                intent.putExtra("audio", urls[1]);
                intent.putExtra("danmu_path", viewModel.danmukuPath);
                intent.putExtra("data", convertToResolutionModel(viewModel.mPlayUrlModel.getDash().getVideo()));
                LocalBroadcastManager.getInstance(PlayerActivity.this).sendBroadcast(intent);
            }).start();
        } else if (urls.length == 1) {
//            final boolean isCache = !urls[0].endsWith(".mpd");
            final boolean isCache = false;
        }
    }

    private Parcelable[] convertToResolutionModel(List<PlayUrlModel.DashModel.VideoModel> videoModelList) {
        return GsonUtils.fromJson(GsonUtils.toJson(videoModelList), ResolutionModel[].class);
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
//        mBinding.player.setDanmaKuStream(new File(viewModel.danmukuPath));
//        mBinding.player.getDanmakuView().seekTo(mBinding.player.getCurrentPlayer().getCurrentPositionWhenPlaying());
        danmuLoaded = true;
    }

}