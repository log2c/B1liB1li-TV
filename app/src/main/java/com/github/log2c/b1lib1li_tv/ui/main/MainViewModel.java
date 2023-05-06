package com.github.log2c.b1lib1li_tv.ui.main;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.R;
import com.github.log2c.b1lib1li_tv.model.NavUserInfoModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
import com.github.log2c.b1lib1li_tv.repository.AppConfigRepository;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;
import com.github.log2c.base.toast.ToastUtils;

public class MainViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    public final SingleLiveEvent<NavUserInfoModel> navUserInfoEvent = new SingleLiveEvent<>();
    public final SingleLiveEvent<Void> unLoginEvent = new SingleLiveEvent<>();

    public MainViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void fetchUserInfo() {
        userRepository.getNavUserInfo()
                .subscribe(new BackendObserver<NavUserInfoModel>() {
                    @Override
                    public void onSuccess(NavUserInfoModel model) {
                        if (!model.isIsLogin()) {
                            doLogout();
                        }
                        navUserInfoEvent.postValue(model);
                        storeUserInfo(model);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    private void doLogout() {
        ToastUtils.warning(R.string.tip_already_logout);
        AppConfigRepository.getInstance().storeSessdata("");
        AppConfigRepository.getInstance().storeDedeUserId("");
    }

    private void storeUserInfo(NavUserInfoModel model) {
        AppConfigRepository.getInstance().storeUserMid(model.getMid() + "");
    }

    public void checkIsLogin() {
        if (!AppConfigRepository.getInstance().isLogin()) {
            unLoginEvent.call();
        }
    }
}
