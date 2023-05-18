package com.github.log2c.b1lib1li_tv.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.ui.player.PlayerViewModel;
import com.google.android.material.button.MaterialButton;

public class VideoActionDialogFragment extends DialogFragment implements View.OnClickListener {
    private PlayerViewModel mPlayerViewModel;
    private MaterialButton mBtnLike;
    private MaterialButton mBtnAddCoin;
    private MaterialButton mBtnAddFavour;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayerViewModel = new ViewModelProvider(requireActivity()).get(PlayerViewModel.class);
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
            mBtnLike.setText(R.string.is_liked);
            mBtnLike.setIcon(requireContext().getDrawable(R.drawable.ic_like_pink));
        } else {
            mBtnLike.setText(R.string.add_like);
            mBtnLike.setIcon(requireContext().getDrawable(R.drawable.ic_like_white));
        }

        if (value[1]) {
            mBtnAddCoin.setText(R.string.is_add_coin);
            mBtnAddCoin.setIcon(requireContext().getDrawable(R.drawable.ic_coin_pink));
        } else {
            mBtnAddCoin.setText(R.string.add_coin);
            mBtnAddCoin.setIcon(requireContext().getDrawable(R.drawable.ic_coin_white));
        }

        if (value[2]) {
            mBtnAddFavour.setText(R.string.is_add_favour);
            mBtnAddFavour.setIcon(requireContext().getDrawable(R.drawable.ic_favourite_pink));
        } else {
            mBtnAddFavour.setText(R.string.add_favour);
            mBtnAddFavour.setIcon(requireContext().getDrawable(R.drawable.ic_favourite_white));
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
