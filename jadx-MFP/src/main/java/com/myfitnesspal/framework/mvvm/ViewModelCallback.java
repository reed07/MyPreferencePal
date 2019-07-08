package com.myfitnesspal.framework.mvvm;

import android.databinding.Observable;

public interface ViewModelCallback {
    void onViewModelPropertyChanged(Observable observable, int i);
}
