package com.github.log2c.b1lib1li_tv.ui.upfeed;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.UpFeedModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

public class UpFeedViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    public static final int PAGE_SIZE = 30;
    private int page = 1;
    public String hostMid;
    final public SingleLiveEvent<UpFeedModel> feedModelEvent = new SingleLiveEvent<>();
    final public SingleLiveEvent<String> refreshEvent = new SingleLiveEvent<>();

    public UpFeedViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void loadFeedInfo() {
        getDefUI().getShowDialog().call();
        userRepository.getUpFeed(hostMid, page, PAGE_SIZE).subscribe(new BackendObserver<UpFeedModel>() {
            @Override
            public void onSuccess(UpFeedModel model) {
                page++;
                feedModelEvent.postValue(model);
            }

            @Override
            public void onError(Throwable e) {
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
