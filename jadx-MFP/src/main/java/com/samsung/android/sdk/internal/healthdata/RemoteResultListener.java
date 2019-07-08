package com.samsung.android.sdk.internal.healthdata;

import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;

public interface RemoteResultListener<T extends BaseResult> {
    void onReceiveHealthResult(int i, T t);
}
