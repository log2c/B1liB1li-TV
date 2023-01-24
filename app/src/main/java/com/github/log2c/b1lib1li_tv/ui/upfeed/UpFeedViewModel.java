package com.github.log2c.b1lib1li_tv.ui.upfeed;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.blankj.utilcode.util.CollectionUtils;
import com.github.log2c.b1lib1li_tv.common.Constants;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

public class UpFeedViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    private int page = 1;
    private String offset = "";
    public String hostMid;
    final public SingleLiveEvent<UpFeedModel> feedModelEvent = new SingleLiveEvent<>();
    final public SingleLiveEvent<String> refreshEvent = new SingleLiveEvent<>();

    public UpFeedViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void loadFeedInfo() {
        getDefUI().getShowDialog().call();
        userRepository.getUpFeed(hostMid, page, offset).subscribe(new LocalObserver<UpFeedModel>() {
            @Override
            public void onSuccess(UpFeedModel model) {
                page++;
                offset = model.getOffset();
                CollectionUtils.filter(model.getItems(), item -> Constants.DYNAMIC_TYPE_AV.equals(item.getType()));
                feedModelEvent.postValue(model);
            }

            @Override
            public void onException(Throwable e) {
                showErrorToast(e.getMessage());
                feedModelEvent.postValue(null);
            }

            @Override
            public void onFinish() {
                getDefUI().getDismissDialog().call();
            }
        });
    }
}
