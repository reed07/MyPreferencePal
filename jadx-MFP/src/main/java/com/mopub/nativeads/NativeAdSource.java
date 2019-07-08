package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import com.myfitnesspal.shared.constants.Constants.Config;
import java.util.ArrayList;
import java.util.List;

class NativeAdSource {
    @VisibleForTesting
    static final int[] RETRY_TIME_ARRAY_MILLISECONDS = {1000, 3000, 5000, 25000, 60000, Config.CACHE_TTL_MILLISECONDS};
    @NonNull
    private final AdRendererRegistry mAdRendererRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public AdSourceListener mAdSourceListener;
    @VisibleForTesting
    int mCurrentRetries;
    /* access modifiers changed from: private */
    @Nullable
    public MoPubNative mMoPubNative;
    @NonNull
    private final MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    /* access modifiers changed from: private */
    @NonNull
    public final List<TimestampWrapper<NativeAd>> mNativeAdCache;
    /* access modifiers changed from: private */
    @NonNull
    public final Handler mReplenishCacheHandler;
    /* access modifiers changed from: private */
    @NonNull
    public final Runnable mReplenishCacheRunnable;
    @VisibleForTesting
    boolean mRequestInFlight;
    @Nullable
    private RequestParameters mRequestParameters;
    @VisibleForTesting
    boolean mRetryInFlight;
    @VisibleForTesting
    int mSequenceNumber;

    interface AdSourceListener {
        void onAdsAvailable();
    }

    NativeAdSource() {
        this(new ArrayList(1), new Handler(), new AdRendererRegistry());
    }

    @VisibleForTesting
    NativeAdSource(@NonNull List<TimestampWrapper<NativeAd>> list, @NonNull Handler handler, @NonNull AdRendererRegistry adRendererRegistry) {
        this.mNativeAdCache = list;
        this.mReplenishCacheHandler = handler;
        this.mReplenishCacheRunnable = new Runnable() {
            public void run() {
                NativeAdSource nativeAdSource = NativeAdSource.this;
                nativeAdSource.mRetryInFlight = false;
                nativeAdSource.replenishCache();
            }
        };
        this.mAdRendererRegistry = adRendererRegistry;
        this.mMoPubNativeNetworkListener = new MoPubNativeNetworkListener() {
            public void onNativeLoad(@NonNull NativeAd nativeAd) {
                if (NativeAdSource.this.mMoPubNative != null) {
                    NativeAdSource nativeAdSource = NativeAdSource.this;
                    nativeAdSource.mRequestInFlight = false;
                    nativeAdSource.mSequenceNumber++;
                    NativeAdSource.this.resetRetryTime();
                    NativeAdSource.this.mNativeAdCache.add(new TimestampWrapper(nativeAd));
                    if (NativeAdSource.this.mNativeAdCache.size() == 1 && NativeAdSource.this.mAdSourceListener != null) {
                        NativeAdSource.this.mAdSourceListener.onAdsAvailable();
                    }
                    NativeAdSource.this.replenishCache();
                }
            }

            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                NativeAdSource nativeAdSource = NativeAdSource.this;
                nativeAdSource.mRequestInFlight = false;
                if (nativeAdSource.mCurrentRetries >= NativeAdSource.RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
                    NativeAdSource.this.resetRetryTime();
                    return;
                }
                NativeAdSource.this.updateRetryTime();
                NativeAdSource nativeAdSource2 = NativeAdSource.this;
                nativeAdSource2.mRetryInFlight = true;
                nativeAdSource2.mReplenishCacheHandler.postDelayed(NativeAdSource.this.mReplenishCacheRunnable, (long) NativeAdSource.this.getRetryTime());
            }
        };
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    /* access modifiers changed from: 0000 */
    public int getAdRendererCount() {
        return this.mAdRendererRegistry.getAdRendererCount();
    }

    public int getViewTypeForAd(@NonNull NativeAd nativeAd) {
        return this.mAdRendererRegistry.getViewTypeForAd(nativeAd);
    }

    /* access modifiers changed from: 0000 */
    public void registerAdRenderer(@NonNull MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
        MoPubNative moPubNative = this.mMoPubNative;
        if (moPubNative != null) {
            moPubNative.registerAdRenderer(moPubAdRenderer);
        }
    }

    @Nullable
    public MoPubAdRenderer getAdRendererForViewType(int i) {
        return this.mAdRendererRegistry.getRendererForViewType(i);
    }

    /* access modifiers changed from: 0000 */
    public void setAdSourceListener(@Nullable AdSourceListener adSourceListener) {
        this.mAdSourceListener = adSourceListener;
    }

    /* access modifiers changed from: 0000 */
    public void loadAds(@NonNull Activity activity, @NonNull String str, RequestParameters requestParameters) {
        loadAds(requestParameters, new MoPubNative(activity, str, this.mMoPubNativeNetworkListener));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void loadAds(RequestParameters requestParameters, MoPubNative moPubNative) {
        clear();
        for (MoPubAdRenderer registerAdRenderer : this.mAdRendererRegistry.getRendererIterable()) {
            moPubNative.registerAdRenderer(registerAdRenderer);
        }
        this.mRequestParameters = requestParameters;
        this.mMoPubNative = moPubNative;
        replenishCache();
    }

    /* access modifiers changed from: 0000 */
    public void clear() {
        MoPubNative moPubNative = this.mMoPubNative;
        if (moPubNative != null) {
            moPubNative.destroy();
            this.mMoPubNative = null;
        }
        this.mRequestParameters = null;
        for (TimestampWrapper timestampWrapper : this.mNativeAdCache) {
            ((NativeAd) timestampWrapper.mInstance).destroy();
        }
        this.mNativeAdCache.clear();
        this.mReplenishCacheHandler.removeMessages(0);
        this.mRequestInFlight = false;
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public NativeAd dequeueAd() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (!this.mRequestInFlight && !this.mRetryInFlight) {
            this.mReplenishCacheHandler.post(this.mReplenishCacheRunnable);
        }
        while (!this.mNativeAdCache.isEmpty()) {
            TimestampWrapper timestampWrapper = (TimestampWrapper) this.mNativeAdCache.remove(0);
            if (uptimeMillis - timestampWrapper.mCreatedTimestamp < 14400000) {
                return (NativeAd) timestampWrapper.mInstance;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void updateRetryTime() {
        int i = this.mCurrentRetries;
        if (i < RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.mCurrentRetries = i + 1;
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void resetRetryTime() {
        this.mCurrentRetries = 0;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public int getRetryTime() {
        int i = this.mCurrentRetries;
        int[] iArr = RETRY_TIME_ARRAY_MILLISECONDS;
        if (i >= iArr.length) {
            this.mCurrentRetries = iArr.length - 1;
        }
        return RETRY_TIME_ARRAY_MILLISECONDS[this.mCurrentRetries];
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void replenishCache() {
        if (!this.mRequestInFlight && this.mMoPubNative != null && this.mNativeAdCache.size() < 1) {
            this.mRequestInFlight = true;
            this.mMoPubNative.makeRequest(this.mRequestParameters, Integer.valueOf(this.mSequenceNumber));
        }
    }
}
