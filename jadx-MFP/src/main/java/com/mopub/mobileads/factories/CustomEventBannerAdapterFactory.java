package com.mopub.mobileads.factories;

import android.support.annotation.NonNull;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.mobileads.MoPubView;
import java.util.Map;

public class CustomEventBannerAdapterFactory {
    protected static CustomEventBannerAdapterFactory instance = new CustomEventBannerAdapterFactory();

    @Deprecated
    public static void setInstance(CustomEventBannerAdapterFactory customEventBannerAdapterFactory) {
        instance = customEventBannerAdapterFactory;
    }

    public static CustomEventBannerAdapter create(@NonNull MoPubView moPubView, @NonNull String str, @NonNull Map<String, String> map, long j, @NonNull AdReport adReport) {
        return instance.internalCreate(moPubView, str, map, j, adReport);
    }

    /* access modifiers changed from: protected */
    public CustomEventBannerAdapter internalCreate(@NonNull MoPubView moPubView, @NonNull String str, @NonNull Map<String, String> map, long j, @NonNull AdReport adReport) {
        CustomEventBannerAdapter customEventBannerAdapter = new CustomEventBannerAdapter(moPubView, str, map, j, adReport);
        return customEventBannerAdapter;
    }
}
