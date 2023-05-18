package com.github.log2c.b1lib1li_tv.contracts;

import com.github.log2c.b1lib1li_tv.model.PlayUrlModel;

public interface PlayerActivityContract {

    PlayUrlModel getPlayUrlModel();

    void showVideoActionDialog();   // 显示三连Dialog

}
