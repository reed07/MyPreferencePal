package com.myfitnesspal.framework.mvvm;

import com.uacf.taskrunner.Runner;

public interface ViewModelComponent {
    Runner getRunner();

    <T extends BaseViewModel> T getViewModel();

    ViewModelCallback getViewModelCallback();

    void onViewModelChanged(BaseViewModel baseViewModel, BaseViewModel baseViewModel2);

    void onViewModelReset();

    void onViewModelRestored(BaseViewModel baseViewModel);

    void rebindView();

    <T extends BaseViewModel> T setViewModel(T t);
}
