package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.allen.library.SuperTextView;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.ui.player.PlayerViewModel;
import com.github.log2c.b1lib1li_tv.widget.IconButton;

public class VideoActionDialogFragment extends DialogFragment implements View.OnClickListener {
    private PlayerViewModel mPlayerViewModel;
    private IconButton mBtnLike;
    private IconButton mBtnAddCoin;
    private IconButton mBtnAddFavour;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        window.setLayout((int) (Math.max(size.x, size.y) * 0.8f), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_action_video, container);
        mBtnLike = rootView.findViewById(R.id.btn_like);
        mBtnLike.setOnClickListener(this);
        mBtnAddCoin = rootView.findViewById(R.id.btn_add_coin);
        mBtnAddCoin.setOnClickListener(this);
        mBtnAddFavour = rootView.findViewById(R.id.btn_add_favour);
        mBtnAddFavour.setOnClickListener(this);
        rootView.findViewById(R.id.btn_add_triple).setOnClickListener(this);

        mPlayerViewModel.videoActionEvent.observe(this, booleans -> initViewData());
        initViewData();
        return rootView;
    }

    private void initViewData() {
        Boolean[] value = mPlayerViewModel.videoActionEvent.getValue();
        if (value == null || value.length != 3) {
            throw new IllegalArgumentException("参数错误.");
        }
        if (value[0]) {
            mBtnLike.setText(getString(R.string.is_liked));
            mBtnLike.setIcon(R.drawable.ic_like_pink);
        } else {
            mBtnLike.setText(getString(R.string.add_like));
            mBtnLike.setIcon(R.drawable.ic_like_white);
        }

        if (value[1]) {
            mBtnAddCoin.setText(getString(R.string.is_add_coin));
            mBtnAddCoin.setIcon(R.drawable.ic_coin_pink);
        } else {
            mBtnAddCoin.setText(getString(R.string.add_coin));
            mBtnAddCoin.setIcon(R.drawable.ic_coin_white);
        }

        if (value[2]) {
            mBtnAddFavour.setText(getString(R.string.is_add_favour));
            mBtnAddFavour.setIcon(R.drawable.ic_favourite_pink);
        } else {
            mBtnAddFavour.setText(getString(R.string.add_favour));
            mBtnAddFavour.setIcon(R.drawable.ic_favourite_white);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_like) {
            mPlayerViewModel.actionLike();
        } else if (view.getId() == R.id.btn_add_coin) {
            mPlayerViewModel.addCoin(2);
        } else if (view.getId() == R.id.btn_add_favour) {
            mPlayerViewModel.addFavor();
        } else if (view.getId() == R.id.btn_add_triple) {
            mPlayerViewModel.triple();
        }
    }
}
