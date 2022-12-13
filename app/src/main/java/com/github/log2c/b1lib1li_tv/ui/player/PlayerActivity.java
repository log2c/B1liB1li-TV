package com.github.log2c.b1lib1li_tv.ui.player;

import static com.github.log2c.b1lib1li_tv.common.Constants.SP_NAME_CONFIG;
import static com.github.log2c.b1lib1li_tv.common.Constants.VIDEO_PARTITION_SIZE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.SPUtils;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.databinding.ActivityPlayerBinding;
import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;
import com.github.log2c.base.base.BaseCoreActivity;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends BaseCoreActivity<PlayerViewModel, ActivityPlayerBinding> {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    public static final String INTENT_BVID = "bvid";
    public static final String INTENT_AID = "aid";
    public static final String INTENT_CID = "cid";
    private VideoView videoView;
    private XUISimplePopup mMenuPopup;


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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.release();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
//        if (!videoView.onBackPressed()) {
//        }
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        videoView = mBinding.player;
        videoView.startFullScreen();
        viewModel.playUrlModelEvent.observe(this, playUrlModel -> {
            final String url = viewModel.getDefaultResolution(playUrlModel);
            playVideo(url);
        });
    }

    private void playVideo(String url) {
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent("标题", false);
        videoView.setVideoController(controller); //设置控制器
        videoView.setUrl(url, Constants.PLAYER_HEADERS);
        videoView.start();
    }

    private void showMenuPopup() {
        final String[] menu = new String[]{"设置"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择")
                .setItems(menu, (dialog, which) -> showResolutionSetting());
        builder.create().show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            showMenuPopup();
            return true;
        }
        return super.dispatchKeyEvent(event);
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

        int partitionSize = VIDEO_PARTITION_SIZE;
        List<List<PlayUrlModel.DashModel.VideoModel>> partitions = new ArrayList<>();
        final List<PlayUrlModel.DashModel.VideoModel> videoModelList = model.getDash().getVideo();
        for (int i = 0; i < videoModelList.size(); i += partitionSize) {
            partitions.add(videoModelList.subList(i, Math.min(i + partitionSize, videoModelList.size())));
        }

        if (partitions.size() != menus.length) {
            throw new RuntimeException("showResolutionSetting: ");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择分辨率")
                .setItems(menus, (dialog, which) -> {
                    storeResolution(model.getAccept_quality().get(which));
                    playVideo(partitions.get(which).get(0).getBaseUrl());
                });
        builder.create().show();
    }

    private void storeResolution(int quality) {
        SPUtils.getInstance(SP_NAME_CONFIG).put(Constants.SP_DEFAULT_RESOLUTION, quality);
    }
}