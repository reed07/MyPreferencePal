package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.WeakHashMap;

class NativeAdViewHelper {
    private static final WeakHashMap<View, NativeAd> sNativeAdMap = new WeakHashMap<>();

    @VisibleForTesting
    enum ViewType {
        EMPTY,
        AD
    }

    private NativeAdViewHelper() {
    }

    @NonNull
    static View getAdView(@Nullable View view, @Nullable ViewGroup viewGroup, @NonNull Context context, @Nullable NativeAd nativeAd) {
        if (view != null) {
            clearNativeAd(view);
        }
        if (nativeAd == null || nativeAd.isDestroyed()) {
            MoPubLog.d("NativeAd null or invalid. Returning empty view");
            if (view != null && ViewType.EMPTY.equals(view.getTag())) {
                return view;
            }
            View view2 = new View(context);
            view2.setTag(ViewType.EMPTY);
            view2.setVisibility(8);
            return view2;
        }
        if (view == null || !ViewType.AD.equals(view.getTag())) {
            view = nativeAd.createAdView(context, viewGroup);
            view.setTag(ViewType.AD);
        }
        prepareNativeAd(view, nativeAd);
        nativeAd.renderAdView(view);
        return view;
    }

    private static void clearNativeAd(@NonNull View view) {
        NativeAd nativeAd = (NativeAd) sNativeAdMap.get(view);
        if (nativeAd != null) {
            nativeAd.clear(view);
        }
    }

    private static void prepareNativeAd(@NonNull View view, @NonNull NativeAd nativeAd) {
        sNativeAdMap.put(view, nativeAd);
        nativeAd.prepare(view);
    }
}
