package com.github.log2c.b1lib1li_tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.github.log2c.b1lib1li_tv.adapter.DanamakuAdapter;
import com.github.log2c.b1lib1li_tv.common.BiliDanmukuParser;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.ui.widget.DanmakuView;

public class DanmakuVideoPlayer extends DanmakuView {
    private BaseDanmakuParser mParser;
    private IDanmakuView mDanmakuView;
    private DanmakuContext mDanmakuContext;
    private long mDanmakuStartSeekPosition = -1;
    private boolean mDanmakuShow = true;
    private File mDanmakuFile;

    public DanmakuVideoPlayer(Context context) {
        this(context, null);
    }

    public DanmakuVideoPlayer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmakuVideoPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        mDanmakuView = this;
        initDanmuku();
    }

    //    @Override
    public void onPrepared() {
        onPrepareDanmaku(this);
    }

    //    @Override
    public void onVideoPause() {
        danmakuOnPause();
    }

    //    @Override
    public void onVideoResume(boolean isResume) {
        danmakuOnResume();
    }

    //    @Override
    protected void clickStartIcon() {
//        if (mCurrentState == CURRENT_STATE_PLAYING) {
//            danmakuOnResume();
//        } else if (mCurrentState == CURRENT_STATE_PAUSE) {
//            danmakuOnPause();
//        }
    }

    //    @Override
    public void onCompletion() {
        releaseDanmaku(this);
    }


    //    @Override
    public void onSeekComplete() {
//        long time = mProgressBar.getProgress() * getDuration() / 100;
//        //如果已经初始化过的，直接seek到对于位置
//        if (mHadPlay && getDanmakuView() != null && getDanmakuView().isPrepared()) {
//            resolveDanmakuSeek(this, time);
//        } else if (mHadPlay && getDanmakuView() != null && !getDanmakuView().isPrepared()) {
//            //如果没有初始化过的，记录位置等待
//            setDanmakuStartSeekPosition(time);
//        }
    }

    //    @Override
    public void onClick(View v) {
    }

    protected void danmakuOnPause() {
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    protected void danmakuOnResume() {
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    public void setDanmaKuStream(File is) {
        mDanmakuFile = is;
        if (getDanmakuView() != null && !getDanmakuView().isPrepared()) {
//            onPrepareDanmaku((DanmakuVideoPlayer) getCurrentPlayer());
        }
    }


    public void initDanmuku() {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        DanamakuAdapter danamakuAdapter = new DanamakuAdapter(mDanmakuView);
        mDanmakuContext = DanmakuContext.create();
        mDanmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(AppConfigRepository.getInstance().fetchDanmuSize())
                .setCacheStuffer(new SpannedCacheStuffer(), danamakuAdapter) // 图文混排使用SpannedCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        if (mDanmakuView != null) {
            if (mDanmakuFile != null) {
                mParser = createParser(getIsStream(mDanmakuFile));
            }

            mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
                }

                @Override
                public void prepared() {
                    if (getDanmakuView() != null) {
                        getDanmakuView().start();
                        if (getDanmakuStartSeekPosition() != -1) {
                            resolveDanmakuSeek(DanmakuVideoPlayer.this, getDanmakuStartSeekPosition());
                            setDanmakuStartSeekPosition(-1);
                        }
                        resolveDanmakuShow();
                    }
                }
            });
            mDanmakuView.enableDanmakuDrawingCache(true);
        }
    }

    private InputStream getIsStream(File file) {
        return ConvertUtils.bytes2InputStream(FileIOUtils.readFile2BytesByStream(file));
    }

    /**
     * 弹幕的显示与关闭
     */
    private void resolveDanmakuShow() {
        post(() -> {
            if (mDanmakuShow) {
                if (!getDanmakuView().isShown())
                    getDanmakuView().show();
            } else {
                if (getDanmakuView().isShown()) {
                    getDanmakuView().hide();
                }
            }
        });
    }

    public boolean isShowDanmaku() {
        return getDanmakuView().isShown();
    }

    /**
     * 开始播放弹幕
     */
    private void onPrepareDanmaku(DanmakuVideoPlayer gsyVideoPlayer) {
        if (gsyVideoPlayer.getDanmakuView() != null && !gsyVideoPlayer.getDanmakuView().isPrepared() && gsyVideoPlayer.getParser() != null) {
            gsyVideoPlayer.getDanmakuView().prepare(gsyVideoPlayer.getParser(),
                    gsyVideoPlayer.getDanmakuContext());
        }
    }

    /**
     * 弹幕偏移
     */
    private void resolveDanmakuSeek(DanmakuVideoPlayer gsyVideoPlayer, long time) {
//        if (mHadPlay && gsyVideoPlayer.getDanmakuView() != null && gsyVideoPlayer.getDanmakuView().isPrepared()) {
//            gsyVideoPlayer.getDanmakuView().seekTo(time);
//        }
    }

    private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

    /**
     * 释放弹幕控件
     */
    private void releaseDanmaku(DanmakuVideoPlayer danmakuVideoPlayer) {
        if (danmakuVideoPlayer != null && danmakuVideoPlayer.getDanmakuView() != null) {
//            Debuger.printfError("release Danmaku!");
            danmakuVideoPlayer.getDanmakuView().release();
        }
    }

    public BaseDanmakuParser getParser() {
        if (mParser == null) {
            if (mDanmakuFile != null) {
                mParser = createParser(getIsStream(mDanmakuFile));
            }
        }
        return mParser;
    }

    public DanmakuContext getDanmakuContext() {
        return mDanmakuContext;
    }

    public IDanmakuView getDanmakuView() {
        return mDanmakuView;
    }

    public long getDanmakuStartSeekPosition() {
        return mDanmakuStartSeekPosition;
    }

    public void setDanmakuStartSeekPosition(long danmakuStartSeekPosition) {
        this.mDanmakuStartSeekPosition = danmakuStartSeekPosition;
    }

    public void setDanmakuShow(boolean danmakuShow) {
        mDanmakuShow = danmakuShow;
        if (getDanmakuView() != null && getDanmakuView().isPrepared()) {
            resolveDanmakuShow();
        }
    }

    public boolean isDanmakuShow() {
        return mDanmakuShow;
    }

    public void seekTo(long ms) {
        if (mDanmakuView != null) {
            mDanmakuView.seekTo(ms);
        }
    }
}