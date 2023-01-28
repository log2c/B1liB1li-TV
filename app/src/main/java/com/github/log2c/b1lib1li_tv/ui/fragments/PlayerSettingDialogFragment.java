package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;

import java.util.ArrayList;
import java.util.List;

public class PlayerSettingDialogFragment extends BottomSheetDialogFragment {
    private View mView;
    private static final String KEY_DANMU = "danmu";
    private static final String KEY_SUPPORTED_RESOLUTION = "supported_resolution";
    private static final String KEY_RESOLUTION = "resolution";
    private static final String KEY_CODEC = "codec";
    private int mDanmuToggle;
    private List<Integer> mSupportedResolutions;
    private int mResolution;
    private int mCodec;

    private FlowTagLayout danmuFlowTag;
    private FlowTagLayout resolutionFlowTag;
    private FlowTagLayout codecFlowTag;

    private ConfigChangeCallback mConfigChangeCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        mView = View.inflate(getContext(), R.layout.layout_dialog_setting_player, null);
        danmuFlowTag = mView.findViewById(R.id.flow_danmu);
        resolutionFlowTag = mView.findViewById(R.id.flow_resolution);
        codecFlowTag = mView.findViewById(R.id.flow_codec);
        dialog.setContentView(mView);

        danmuFlowTag.setOnTagSelectListener((parent, position, selectedList) -> {
            AppConfigRepository.getInstance().storeDanmakuToggle(position == 0);
            if (mConfigChangeCallback != null) {
                mConfigChangeCallback.onDanmuToggleChange();
            }
        });
        resolutionFlowTag.setOnTagSelectListener((parent, position, selectedList) -> {
            AppConfigRepository.getInstance().storeResolution(mSupportedResolutions.get(position));
            if (mConfigChangeCallback != null) {
                mConfigChangeCallback.onNeedReloadChange();
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
        mSupportedResolutions = getArguments().getIntegerArrayList(KEY_SUPPORTED_RESOLUTION);
        mResolution = getArguments().getInt(KEY_RESOLUTION);
        mCodec = AppConfigRepository.getInstance().isH265() ? 0 : 1;

        danmuFlowTag.setSelectedPositions(mDanmuToggle);
        codecFlowTag.setSelectedPositions(mCodec);

        final String[] resolutions = new String[mSupportedResolutions.size()];
        int indexResolution = 0;
        for (int i = 0; i < mSupportedResolutions.size(); i++) {
            int code = mSupportedResolutions.get(i);
            String desc = Constants.Resolution.ITEMS.get(code);
            resolutions[i] = desc;
            if (mResolution == code) {
                indexResolution = i;
            }
        }
        resolutionFlowTag.setItems(resolutions);
        resolutionFlowTag.setSelectedPositions(indexResolution);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mConfigChangeCallback = null;
    }

    public static PlayerSettingDialogFragment newInstance(ArrayList<Integer> supportedResolutions, int resolution) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(KEY_SUPPORTED_RESOLUTION, supportedResolutions);
        bundle.putInt(KEY_RESOLUTION, resolution);
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

    public interface ConfigChangeCallback {
        void onDanmuToggleChange();

        void onNeedReloadChange();
    }

}
