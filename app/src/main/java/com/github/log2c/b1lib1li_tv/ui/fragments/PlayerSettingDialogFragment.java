package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.slider.Slider;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlayerSettingDialogFragment extends BottomSheetDialogFragment {
    private static final String TAG = PlayerSettingDialogFragment.class.getSimpleName();
    private View mView;
    private static final String KEY_DANMU = "danmu";
    private static final String KEY_RESOLUTION_LIST = "resolution_info_list";
    private static final String KEY_SELECT_RESOLUTION = "select_resolution";
    private static final String KEY_CODEC = "codec";
    private int mDanmuToggle;
    private int mCodec;
    private FlowTagLayout danmuFlowTag;
    private FlowTagLayout resolutionFlowTag;
    private FlowTagLayout codecFlowTag;
    private FlowTagLayout mediaPlayerTag;

    private Slider danmuSlider;

    private ConfigChangeCallback mConfigChangeCallback;
    private LifeCallback mLifeCallback;
    private String[] mResolutionList;
    private int mSelectResolutionIndex;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        if (mLifeCallback != null) {
            mLifeCallback.onCreateDialog();
        }

        mView = View.inflate(getContext(), R.layout.layout_dialog_setting_player, null);
        danmuFlowTag = mView.findViewById(R.id.flow_danmu);
        resolutionFlowTag = mView.findViewById(R.id.flow_resolution);
        codecFlowTag = mView.findViewById(R.id.flow_codec);
        danmuSlider = mView.findViewById(R.id.slider_danmu);
        mediaPlayerTag = mView.findViewById(R.id.flow_media_player);
        dialog.setContentView(mView);

        danmuSlider.setValue(AppConfigRepository.getInstance().fetchDanmuSize());
        danmuSlider.addOnChangeListener((slider, value, fromUser) -> {
            BigDecimal b = new BigDecimal(value);
            float size = b.setScale(1, RoundingMode.HALF_UP).floatValue();
            Log.i(TAG, "onCreateDialog: danmu size: " + size);
            AppConfigRepository.getInstance().storeDanmuSize(size);
        });

        danmuFlowTag.setOnTagSelectListener((parent, position, selectedList) -> {
            AppConfigRepository.getInstance().storeDanmakuToggle(position == 0);
            if (mConfigChangeCallback != null) {
                mConfigChangeCallback.onDanmuToggleChange();
            }
        });
        resolutionFlowTag.setOnTagSelectListener((parent, position, selectedList) -> {
//            Log.i(TAG, "onCreateDialog: 选择的清晰度 code: " + code + ", desc: " + Constants.Resolution.ITEMS.get(code));
//            AppConfigRepository.getInstance().storeResolution(code);
            if (mConfigChangeCallback != null) {
                mConfigChangeCallback.onResolutionSelectChange(position);
            }
        });
        codecFlowTag.setOnTagSelectListener((parent, position, selectedList) -> {
            if (position == 0) {
                AppConfigRepository.getInstance().setDefaultH265Codec();
            } else
                AppConfigRepository.getInstance().setDefaultH265Codec();
            if (mConfigChangeCallback != null) {
                mConfigChangeCallback.onNeedReloadChange();
            }
        });
        mediaPlayerTag.setOnTagSelectListener((parent, position, selectedList) -> {
            if (position == 0) {
                AppConfigRepository.getInstance().setExoPlayerDefault();
            } else
                AppConfigRepository.getInstance().setAndroidMediaCodecDefault();
        });
        return dialog;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

            View view = mView;
            view.post(() -> {
                View parent = (View) view.getParent();
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
                CoordinatorLayout.Behavior behavior = params.getBehavior();
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
                if (bottomSheetBehavior != null) {
                    bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
                }
                ((View) bottomSheet.getParent()).setBackgroundColor(Color.TRANSPARENT);
            });
        }

        fillData();
    }

    private void fillData() {
        if (getArguments() == null) {
            return;
        }
        mDanmuToggle = AppConfigRepository.getInstance().fetchDanmakuToggle() ? 0 : 1;
        mResolutionList = getArguments().getStringArray(KEY_RESOLUTION_LIST);
        mSelectResolutionIndex = getArguments().getInt(KEY_SELECT_RESOLUTION);
        mCodec = AppConfigRepository.getInstance().isH265() ? 0 : 1;

        danmuFlowTag.setSelectedPositions(mDanmuToggle);
        codecFlowTag.setSelectedPositions(mCodec);

        mediaPlayerTag.setSelectedPositions(AppConfigRepository.getInstance().isExoPlayerDefault() ? 0 : 1);

        resolutionFlowTag.setItems(mResolutionList);
        resolutionFlowTag.setSelectedPositions(mSelectResolutionIndex);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mConfigChangeCallback = null;
        if (mLifeCallback != null) {
            mLifeCallback.onDismiss();
        }
        mLifeCallback = null;
    }

    public static PlayerSettingDialogFragment newInstance(String[] resolutionArrays, int selectedIndex) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(KEY_RESOLUTION_LIST, resolutionArrays);
        bundle.putInt(KEY_SELECT_RESOLUTION, selectedIndex);
        PlayerSettingDialogFragment fragment = new PlayerSettingDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public ConfigChangeCallback getConfigChangeCallback() {
        return mConfigChangeCallback;
    }

    public void setConfigChangeCallback(ConfigChangeCallback configChangeCallback) {
        this.mConfigChangeCallback = configChangeCallback;
    }

    public LifeCallback getLifeCallback() {
        return mLifeCallback;
    }

    public void setLifeCallback(LifeCallback lifeCallback) {
        this.mLifeCallback = lifeCallback;
    }

    public interface ConfigChangeCallback {
        void onDanmuToggleChange();

        void onNeedReloadChange();

        void onResolutionSelectChange(int position);
    }

    public interface LifeCallback {
        void onCreateDialog();

        void onDismiss();
    }

}
