package com.airbnb.lottie.model;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.LruCache;
import com.airbnb.lottie.LottieComposition;
import com.mopub.common.Constants;

@RestrictTo
public class LottieCompositionCache {
    private static final LottieCompositionCache INSTANCE = new LottieCompositionCache();
    private final LruCache<String, LottieComposition> cache = new LruCache<>(Constants.TEN_MB);

    public static LottieCompositionCache getInstance() {
        return INSTANCE;
    }

    @VisibleForTesting
    LottieCompositionCache() {
    }

    @Nullable
    public LottieComposition get(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return (LottieComposition) this.cache.get(str);
    }

    public void put(@Nullable String str, LottieComposition lottieComposition) {
        if (str != null) {
            this.cache.put(str, lottieComposition);
        }
    }
}
