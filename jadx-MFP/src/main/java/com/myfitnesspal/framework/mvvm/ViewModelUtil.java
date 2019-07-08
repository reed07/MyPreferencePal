package com.myfitnesspal.framework.mvvm;

import android.databinding.Observable.OnPropertyChangedCallback;

public final class ViewModelUtil {
    public static void updateCallbacks(BaseViewModel baseViewModel, BaseViewModel baseViewModel2, OnPropertyChangedCallback onPropertyChangedCallback) {
        if (onPropertyChangedCallback != null) {
            if (baseViewModel != null) {
                baseViewModel.removeOnPropertyChangedCallback(onPropertyChangedCallback);
            }
            if (baseViewModel2 != null) {
                baseViewModel2.addOnPropertyChangedCallback(onPropertyChangedCallback);
            }
        }
    }
}
