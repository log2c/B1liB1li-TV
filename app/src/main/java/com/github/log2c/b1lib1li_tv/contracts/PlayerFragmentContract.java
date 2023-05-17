package com.github.log2c.b1lib1li_tv.contracts;

public interface PlayerFragmentContract {
    long playingPosition();

    void onPlayerDataPrepared(String audioUrl, String videoUrl, String danmakuFilePath);

}
