package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WebViewCacheService {
    @NonNull
    private static Handler sHandler = new Handler();
    @NonNull
    @VisibleForTesting
    static final TrimCacheRunnable sTrimCacheRunnable = new TrimCacheRunnable();
    @SuppressLint({"UseSparseArrays"})
    @NonNull
    private static final Map<Long, Config> sWebViewConfigs = Collections.synchronizedMap(new HashMap());

    public static class Config {
        @NonNull
        private final ExternalViewabilitySessionManager mViewabilityManager;
        @NonNull
        private final WeakReference<Interstitial> mWeakInterstitial;
        @NonNull
        private final BaseWebView mWebView;

        Config(@NonNull BaseWebView baseWebView, @NonNull Interstitial interstitial, @NonNull ExternalViewabilitySessionManager externalViewabilitySessionManager) {
            this.mWebView = baseWebView;
            this.mWeakInterstitial = new WeakReference<>(interstitial);
            this.mViewabilityManager = externalViewabilitySessionManager;
        }

        @NonNull
        public BaseWebView getWebView() {
            return this.mWebView;
        }

        @NonNull
        public WeakReference<Interstitial> getWeakInterstitial() {
            return this.mWeakInterstitial;
        }

        @NonNull
        public ExternalViewabilitySessionManager getViewabilityManager() {
            return this.mViewabilityManager;
        }
    }

    private static class TrimCacheRunnable implements Runnable {
        private TrimCacheRunnable() {
        }

        public void run() {
            WebViewCacheService.trimCache();
        }
    }

    private WebViewCacheService() {
    }

    @VisibleForTesting
    public static void storeWebViewConfig(@NonNull Long l, @NonNull Interstitial interstitial, @NonNull BaseWebView baseWebView, @NonNull ExternalViewabilitySessionManager externalViewabilitySessionManager) {
        Preconditions.checkNotNull(l);
        Preconditions.checkNotNull(interstitial);
        Preconditions.checkNotNull(baseWebView);
        trimCache();
        if (sWebViewConfigs.size() >= 50) {
            MoPubLog.w("Unable to cache web view. Please destroy some via MoPubInterstitial#destroy() and try again.");
        } else {
            sWebViewConfigs.put(l, new Config(baseWebView, interstitial, externalViewabilitySessionManager));
        }
    }

    @Nullable
    public static Config popWebViewConfig(@NonNull Long l) {
        Preconditions.checkNotNull(l);
        return (Config) sWebViewConfigs.remove(l);
    }

    @VisibleForTesting
    static synchronized void trimCache() {
        synchronized (WebViewCacheService.class) {
            Iterator it = sWebViewConfigs.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (((Config) entry.getValue()).getWeakInterstitial().get() == null) {
                    ((Config) entry.getValue()).getViewabilityManager().endDisplaySession();
                    it.remove();
                }
            }
            if (!sWebViewConfigs.isEmpty()) {
                sHandler.removeCallbacks(sTrimCacheRunnable);
                sHandler.postDelayed(sTrimCacheRunnable, 900000);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAll() {
        sWebViewConfigs.clear();
        sHandler.removeCallbacks(sTrimCacheRunnable);
    }
}
