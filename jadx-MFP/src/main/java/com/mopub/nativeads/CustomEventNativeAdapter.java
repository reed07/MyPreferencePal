package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.factories.CustomEventNativeFactory;
import com.mopub.network.AdResponse;
import java.util.Map;

final class CustomEventNativeAdapter {
    @Nullable
    private CustomEventNative customEventNative;
    /* access modifiers changed from: private */
    public volatile boolean mCompleted = false;
    /* access modifiers changed from: private */
    @NonNull
    public CustomEventNativeListener mExternalListener;
    @NonNull
    private final Handler mHandler = new Handler();
    @NonNull
    private final Runnable mTimeout = new Runnable() {
        public void run() {
            if (!CustomEventNativeAdapter.this.mCompleted) {
                StringBuilder sb = new StringBuilder();
                sb.append("Timeout loading native ad content. ");
                sb.append(CustomEventNativeAdapter.this.toString());
                MoPubLog.d(sb.toString());
                CustomEventNativeAdapter.this.stopLoading();
                CustomEventNativeAdapter.this.mExternalListener.onNativeAdFailed(NativeErrorCode.NETWORK_TIMEOUT);
            }
        }
    };

    CustomEventNativeAdapter(@NonNull CustomEventNativeListener customEventNativeListener) {
        Preconditions.checkNotNull(customEventNativeListener);
        this.mExternalListener = customEventNativeListener;
    }

    public void loadNativeAd(@NonNull Context context, @NonNull Map<String, Object> map, @NonNull AdResponse adResponse) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(adResponse);
        String customEventClassName = adResponse.getCustomEventClassName();
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to invoke custom event: ");
        sb.append(customEventClassName);
        MoPubLog.d(sb.toString());
        try {
            this.customEventNative = CustomEventNativeFactory.create(customEventClassName);
            if (adResponse.hasJson()) {
                map.put(DataKeys.JSON_BODY_KEY, adResponse.getJsonBody());
            }
            map.put(DataKeys.CLICK_TRACKING_URL_KEY, adResponse.getClickTrackingUrl());
            try {
                this.customEventNative.loadNativeAd(context, createListener(), map, adResponse.getServerExtras());
                this.mHandler.postDelayed(this.mTimeout, (long) adResponse.getAdTimeoutMillis(30000).intValue());
            } catch (Exception e) {
                MoPubLog.w("Loading custom event native threw an error.", e);
                this.mExternalListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
            }
        } catch (Exception unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to load Custom Event Native class: ");
            sb2.append(customEventClassName);
            MoPubLog.e(sb2.toString());
            this.mExternalListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
        }
    }

    @NonNull
    private CustomEventNativeListener createListener() {
        return new CustomEventNativeListener() {
            public void onNativeAdLoaded(BaseNativeAd baseNativeAd) {
                if (!CustomEventNativeAdapter.this.mCompleted) {
                    CustomEventNativeAdapter.this.invalidate();
                    CustomEventNativeAdapter.this.mExternalListener.onNativeAdLoaded(baseNativeAd);
                }
            }

            public void onNativeAdFailed(NativeErrorCode nativeErrorCode) {
                if (!CustomEventNativeAdapter.this.mCompleted) {
                    CustomEventNativeAdapter.this.invalidate();
                    CustomEventNativeAdapter.this.mExternalListener.onNativeAdFailed(nativeErrorCode);
                }
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void stopLoading() {
        try {
            if (this.customEventNative != null) {
                this.customEventNative.onInvalidate();
            }
        } catch (Exception e) {
            MoPubLog.e(e.toString());
        }
        invalidate();
    }

    /* access modifiers changed from: private */
    public synchronized void invalidate() {
        if (!this.mCompleted) {
            this.mCompleted = true;
            this.mHandler.removeCallbacks(this.mTimeout);
            this.customEventNative = null;
        }
    }
}
