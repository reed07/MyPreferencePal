package com.myfitnesspal.framework.mvvm;

public interface ViewModelCache {
    <T extends BaseViewModel> T get(String str);

    void put(BaseViewModel baseViewModel);
}
