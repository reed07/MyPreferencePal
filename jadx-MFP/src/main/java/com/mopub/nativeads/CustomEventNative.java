package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.Map;

public abstract class CustomEventNative {

    public interface CustomEventNativeListener {
        void onNativeAdFailed(NativeErrorCode nativeErrorCode);

        void onNativeAdLoaded(BaseNativeAd baseNativeAd);
    }

    /* access modifiers changed from: protected */
    public abstract void loadNativeAd(@NonNull Context context, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2);

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }
}
