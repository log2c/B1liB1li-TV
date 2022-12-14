package com.github.log2c.b1lib1li_tv.ui.main;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.NavUserInfoModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

public class MainViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    public final SingleLiveEvent<NavUserInfoModel> navUserInfoEvent = new SingleLiveEvent<>();

    public MainViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void fetchUserInfo() {
        userRepository.getNavUserInfo()
                .subscribe(new LocalObserver<NavUserInfoModel>(true) {
                    @Override
                    public void onSuccess(NavUserInfoModel model) {
                        navUserInfoEvent.postValue(model);
                        storeUserInfo(model);
                    }

                    @Override
                    public void onException(Throwable e) {

                    }
                });
    }

    private void storeUserInfo(NavUserInfoModel model) {

    }
}
