package com.github.log2c.b1lib1li_tv.ui.toview;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;
import com.github.log2c.b1lib1li_tv.network.LocalObserver;
import com.github.log2c.b1lib1li_tv.repository.UserRepository;
import com.github.log2c.b1lib1li_tv.repository.impl.UserRepositoryImpl;
import com.github.log2c.base.base.BaseCoreViewModel;

import java.util.List;

public class ToviewViewModel extends BaseCoreViewModel {
    private final UserRepository userRepository;
    public final SingleLiveEvent<List<ToViewModel.ListModel>> dataEvent = new SingleLiveEvent<>();

    public ToviewViewModel() {
        userRepository = new UserRepositoryImpl();
    }

    public void fetchToview() {
        getDefUI().getShowDialog().call();
        userRepository.toView()
                .subscribe(new LocalObserver<ToViewModel>() {
                    @Override
                    public void onSuccess(ToViewModel model) {
                        if (model.getList() != null) {
                            dataEvent.postValue(model.getList());
                        }
                    }

                    @Override
                    public void onException(Throwable e) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        getDefUI().getDismissDialog().call();
                    }
                });
    }
}
