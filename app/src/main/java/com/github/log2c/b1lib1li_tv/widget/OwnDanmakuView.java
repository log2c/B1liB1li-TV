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

public class OwnDanmakuView extends DanmakuView {
    private BaseDanmakuParser mParser;
    private DanmakuContext mDanmakuContext;
    private long mDanmakuStartSeekPosition = -1;
    private boolean mDanmakuShow = true;
    private File mDanmakuFile;

    public OwnDanmakuView(Context context) {
        this(context, null);
    }

    public OwnDanmakuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OwnDanmakuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDanmuku();
    }


    //    @Override
    public void onVideoPause() {
        danmakuOnPause();
    }

    //    @Override
    public void onVideoResume() {
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
        release();
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
        if (isPrepared()) {
            pause();
        }
    }

    protected void danmakuOnResume() {
        if (isPrepared() && isPaused()) {
            resume();
        }
    }

    public void setDanmaKuStream(File is) {
        mDanmakuFile = is;
        if (!isPrepared()) {
            onPrepareDanmaku();
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

        DanamakuAdapter danamakuAdapter = new DanamakuAdapter(this);
        mDanmakuContext = DanmakuContext.create();
        mDanmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(AppConfigRepository.getInstance().fetchDanmuSize())
                .setCacheStuffer(new SpannedCacheStuffer(), danamakuAdapter) // 图文混排使用SpannedCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        if (mDanmakuFile != null) {
            mParser = createParser(getIsStream(mDanmakuFile));
        }

        setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
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
                start();
                if (getDanmakuStartSeekPosition() != -1) {
                    resolveDanmakuSeek(getDanmakuStartSeekPosition());
                    setDanmakuStartSeekPosition(-1);
                }
                resolveDanmakuShow();

            }
        });
        enableDanmakuDrawingCache(true);
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
                if (!isShown())
                    show();
            } else {
                if (isShown()) {
                    hide();
                }
            }
        });
    }

    public boolean isShowDanmaku() {
        return isShown();
    }

    /**
     * 开始播放弹幕
     */
    private void onPrepareDanmaku() {
        prepare(getParser(), mDanmakuContext);
    }

    /**
     * 弹幕偏移
     */
    private void resolveDanmakuSeek(long time) {
        if (isPrepared()) {
            seekTo(time);
        }
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

    private BaseDanmakuParser getParser() {
        if (mParser == null) {
            if (mDanmakuFile != null) {
                mParser = createParser(getIsStream(mDanmakuFile));
            }
        }
        return mParser;
    }

    public long getDanmakuStartSeekPosition() {
        return mDanmakuStartSeekPosition;
    }

    public void setDanmakuStartSeekPosition(long danmakuStartSeekPosition) {
        this.mDanmakuStartSeekPosition = danmakuStartSeekPosition;
    }

    public void setDanmakuShow(boolean danmakuShow) {
        mDanmakuShow = danmakuShow;
        if (isPrepared()) {
            resolveDanmakuShow();
        }
    }

    public boolean isDanmakuShow() {
        return mDanmakuShow;
    }

}