package com.github.log2c.b1lib1li_tv.ui.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.contracts.PlayerActivityContract;
import com.github.log2c.b1lib1li_tv.contracts.PlayerFragmentContract;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPlayerBinding;
import com.github.log2c.b1lib1li_tv.leanback.SelectDialogFragment;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.base.base.BaseCoreActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.jessyan.autosize.internal.CancelAdapt;

public class PlayerActivity extends BaseCoreActivity<PlayerViewModel, ActivityPlayerBinding> implements CancelAdapt, SelectDialogFragment.SelectDialogListener, PlayerActivityContract {
    @SuppressWarnings("unused")
    private static final String TAG = PlayerActivity.class.getSimpleName();
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    public static final String INTENT_CID = "cid";


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
        viewModel.historyReportEvent.observe(this, s -> {
            for (Fragment fragment : getVisibleFragments()) {
                if (fragment instanceof PlayerFragmentContract) {
                    viewModel.updateHistory(((PlayerFragmentContract) fragment).playingPosition());
                    break;
                }
            }
        });
    }

    public List<Fragment> getVisibleFragments() {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments.isEmpty()) {
            return Collections.emptyList();
        }

        List<Fragment> visibleFragments = new ArrayList<>();
        for (Fragment fragment : allFragments) {
            if (fragment.isVisible()) {
                visibleFragments.add(fragment);
            }
        }
        return visibleFragments;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void onPlayUrlChange(String[] urls) {
        if (urls.length == 2) {
            for (Fragment fragment : getVisibleFragments()) {
                if (fragment instanceof PlayerFragmentContract) {
                    ((PlayerFragmentContract) fragment).onPlayerDataPrepared(urls[1], urls[0], viewModel.danmukuPath);
                    break;
                }
            }
        } else if (urls.length == 1) {
//            final boolean isCache = !urls[0].endsWith(".mpd");
//            final boolean isCache = false;
        }
    }

    @Override
    public void onSingleClick(SelectDialogFragment fragment, int position) {
        fragment.dismiss();
        SelectDialogFragment.SelectDialogListener.super.onSingleClick(fragment, position);
        PlayUrlModel.DashModel.VideoModel model = viewModel.mPlayUrlModel.getDash().getVideo().get(position);
        AppConfigRepository.getInstance().storeCodecs(model.getId() + "$$" + model.getCodecs());
        for (Fragment f : getVisibleFragments()) {
            if (f instanceof PlayerFragmentContract) {
                viewModel.mPlayUrlModel.setLast_play_time(((PlayerFragmentContract) f).playingPosition());
                break;
            }
        }

        viewModel.loadPlayResource();   // 重新加载
    }

    @Override
    public PlayUrlModel getPlayUrlModel() {
        return viewModel.mPlayUrlModel;
    }
}