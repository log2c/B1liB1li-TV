package com.github.log2c.b1lib1li_tv.ui.clips.dynamic;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.FeedModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

public class DynamicViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    private static final String FEED_TYPE = "video";
    private int page = 1;
    private String offset = "";
    final public SingleLiveEvent<FeedModel> feedModelEvent = new SingleLiveEvent<>();
    final public SingleLiveEvent<String> refreshEvent = new SingleLiveEvent<>();

    public DynamicViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void loadFeedInfo() {
        getDefUI().getShowDialog().call();
        userRepository.getFeed(FEED_TYPE, page, offset)
                .subscribe(new BackendObserver<FeedModel>() {
                    @Override
                    public void onSuccess(FeedModel model) {
                        page++;
                        offset = model.getOffset();
                        feedModelEvent.postValue(model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        feedModelEvent.postValue(null);
                    }

                    @Override
                    public void onFinish() {
                        getDefUI().getDismissDialog().call();
                    }
                });
    }
}
