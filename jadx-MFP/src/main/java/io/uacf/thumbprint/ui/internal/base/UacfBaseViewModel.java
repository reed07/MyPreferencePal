package io.uacf.thumbprint.ui.internal.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public abstract class UacfBaseViewModel extends AndroidViewModel {
    public UacfBaseViewModel(@NonNull Application application) {
        super(application);
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return getApplication();
    }

    /* access modifiers changed from: protected */
    public String getString(@StringRes int i) {
        return getContext().getResources().getString(i);
    }

    /* access modifiers changed from: protected */
    public boolean isInternetConnectionAvailible() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
