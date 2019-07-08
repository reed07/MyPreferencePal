package com.mopub.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class CompositeSdkInitializationListener implements SdkInitializationListener {
    /* access modifiers changed from: private */
    @Nullable
    public SdkInitializationListener mSdkInitializationListener;
    private int mTimes;

    public CompositeSdkInitializationListener(@NonNull SdkInitializationListener sdkInitializationListener, int i) {
        Preconditions.checkNotNull(sdkInitializationListener);
        this.mSdkInitializationListener = sdkInitializationListener;
        this.mTimes = i;
    }

    public void onInitializationFinished() {
        this.mTimes--;
        if (this.mTimes <= 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (CompositeSdkInitializationListener.this.mSdkInitializationListener != null) {
                        CompositeSdkInitializationListener.this.mSdkInitializationListener.onInitializationFinished();
                        CompositeSdkInitializationListener.this.mSdkInitializationListener = null;
                    }
                }
            });
        }
    }
}
