package com.github.log2c.b1lib1li_tv.ui.clips.toview;

import com.aleyn.mvvm.event.SingleLiveEvent;
import com.github.log2c.b1lib1li_tv.model.ToViewModel;
import com.github.log2c.b1lib1li_tv.network.BackendObserver;
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
                .subscribe(new BackendObserver<ToViewModel>() {
                    @Override
                    public void onSuccess(ToViewModel model) {
                        if (model.getList() != null) {
                            dataEvent.postValue(model.getList());
                        }
                    }

                    @Override
                    public void onFinish() {
                        getDefUI().getDismissDialog().call();
                    }
                });
    }
}
