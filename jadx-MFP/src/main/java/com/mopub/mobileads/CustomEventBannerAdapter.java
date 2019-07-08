package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ReflectionTarget;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.CustomEventBannerFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventBannerAdapter implements CustomEventBannerListener {
    public static final int DEFAULT_BANNER_TIMEOUT_DELAY = 10000;
    private Context mContext;
    /* access modifiers changed from: private */
    @Nullable
    public CustomEventBanner mCustomEventBanner;
    private final Handler mHandler;
    private int mImpressionMinVisibleDips = Integer.MIN_VALUE;
    private int mImpressionMinVisibleMs = Integer.MIN_VALUE;
    private boolean mInvalidated;
    private boolean mIsVisibilityImpressionTrackingEnabled = false;
    private Map<String, Object> mLocalExtras;
    /* access modifiers changed from: private */
    public MoPubView mMoPubView;
    private Map<String, String> mServerExtras;
    private final Runnable mTimeout;
    @Nullable
    private BannerVisibilityTracker mVisibilityTracker;

    public CustomEventBannerAdapter(@NonNull MoPubView moPubView, @NonNull String str, @NonNull Map<String, String> map, long j, @Nullable AdReport adReport) {
        Preconditions.checkNotNull(map);
        this.mHandler = new Handler();
        this.mMoPubView = moPubView;
        this.mContext = moPubView.getContext();
        this.mTimeout = new Runnable() {
            public void run() {
                MoPubLog.d("Third-party network timed out.");
                CustomEventBannerAdapter.this.onBannerFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventBannerAdapter.this.invalidate();
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to invoke custom event: ");
        sb.append(str);
        MoPubLog.d(sb.toString());
        try {
            this.mCustomEventBanner = CustomEventBannerFactory.create(str);
            this.mServerExtras = new TreeMap(map);
            parseBannerImpressionTrackingHeaders();
            this.mLocalExtras = this.mMoPubView.getLocalExtras();
            if (this.mMoPubView.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubView.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(j));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
            this.mLocalExtras.put(DataKeys.AD_WIDTH, Integer.valueOf(this.mMoPubView.getAdWidth()));
            this.mLocalExtras.put(DataKeys.AD_HEIGHT, Integer.valueOf(this.mMoPubView.getAdHeight()));
            this.mLocalExtras.put(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED, Boolean.valueOf(this.mIsVisibilityImpressionTrackingEnabled));
        } catch (Exception unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Couldn't locate or instantiate custom event: ");
            sb2.append(str);
            sb2.append(".");
            MoPubLog.d(sb2.toString());
            this.mMoPubView.loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    /* access modifiers changed from: 0000 */
    @ReflectionTarget
    public void loadAd() {
        if (!isInvalidated() && this.mCustomEventBanner != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventBanner.loadBanner(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception e) {
                MoPubLog.d("Loading a custom event banner threw an exception.", e);
                onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @ReflectionTarget
    public void invalidate() {
        CustomEventBanner customEventBanner = this.mCustomEventBanner;
        if (customEventBanner != null) {
            try {
                customEventBanner.onInvalidate();
            } catch (Exception e) {
                MoPubLog.d("Invalidating a custom event banner threw an exception", e);
            }
        }
        BannerVisibilityTracker bannerVisibilityTracker = this.mVisibilityTracker;
        if (bannerVisibilityTracker != null) {
            try {
                bannerVisibilityTracker.destroy();
            } catch (Exception e2) {
                MoPubLog.d("Destroying a banner visibility tracker threw an exception", e2);
            }
            this.mVisibilityTracker = null;
        }
        this.mContext = null;
        this.mCustomEventBanner = null;
        this.mLocalExtras = null;
        this.mServerExtras = null;
        this.mInvalidated = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isInvalidated() {
        return this.mInvalidated;
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        MoPubView moPubView = this.mMoPubView;
        if (moPubView == null) {
            return 10000;
        }
        return moPubView.getAdTimeoutDelay(10000).intValue();
    }

    private void parseBannerImpressionTrackingHeaders() {
        String str = (String) this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS);
        String str2 = (String) this.mServerExtras.get(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                this.mImpressionMinVisibleDips = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                MoPubLog.d("Cannot parse integer from header banner-impression-min-pixels");
            }
            try {
                this.mImpressionMinVisibleMs = Integer.parseInt(str2);
            } catch (NumberFormatException unused2) {
                MoPubLog.d("Cannot parse integer from header banner-impression-min-ms");
            }
            if (this.mImpressionMinVisibleDips > 0 && this.mImpressionMinVisibleMs >= 0) {
                this.mIsVisibilityImpressionTrackingEnabled = true;
            }
        }
    }

    public void onBannerLoaded(View view) {
        if (!isInvalidated()) {
            cancelTimeout();
            MoPubView moPubView = this.mMoPubView;
            if (moPubView != null) {
                moPubView.creativeDownloaded();
                if (this.mIsVisibilityImpressionTrackingEnabled) {
                    CustomEventBanner customEventBanner = this.mCustomEventBanner;
                    if (customEventBanner != null && customEventBanner.isAutomaticImpressionAndClickTrackingEnabled()) {
                        this.mMoPubView.pauseAutorefresh();
                        BannerVisibilityTracker bannerVisibilityTracker = new BannerVisibilityTracker(this.mContext, this.mMoPubView, view, this.mImpressionMinVisibleDips, this.mImpressionMinVisibleMs);
                        this.mVisibilityTracker = bannerVisibilityTracker;
                        this.mVisibilityTracker.setBannerVisibilityTrackerListener(new BannerVisibilityTrackerListener() {
                            public void onVisibilityChanged() {
                                CustomEventBannerAdapter.this.mMoPubView.trackNativeImpression();
                                if (CustomEventBannerAdapter.this.mCustomEventBanner != null) {
                                    CustomEventBannerAdapter.this.mCustomEventBanner.trackMpxAndThirdPartyImpressions();
                                }
                                CustomEventBannerAdapter.this.mMoPubView.resumeAutorefresh();
                            }
                        });
                    }
                }
                this.mMoPubView.setAdContentView(view);
                if (!this.mIsVisibilityImpressionTrackingEnabled) {
                    CustomEventBanner customEventBanner2 = this.mCustomEventBanner;
                    if (customEventBanner2 != null && customEventBanner2.isAutomaticImpressionAndClickTrackingEnabled() && !(view instanceof HtmlBannerWebView)) {
                        this.mMoPubView.trackNativeImpression();
                    }
                }
            }
        }
    }

    public void onBannerFailed(MoPubErrorCode moPubErrorCode) {
        if (!isInvalidated()) {
            cancelTimeout();
            if (this.mMoPubView != null) {
                if (moPubErrorCode == null) {
                    moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
                }
                this.mMoPubView.loadFailUrl(moPubErrorCode);
            }
        }
    }

    public void onBannerExpanded() {
        if (!isInvalidated()) {
            this.mMoPubView.expand();
            this.mMoPubView.adPresentedOverlay();
        }
    }

    public void onBannerCollapsed() {
        if (!isInvalidated()) {
            this.mMoPubView.collapse();
            this.mMoPubView.adClosed();
        }
    }

    public void onBannerClicked() {
        if (!isInvalidated()) {
            MoPubView moPubView = this.mMoPubView;
            if (moPubView != null) {
                moPubView.registerClick();
            }
        }
    }

    public void onBannerImpression() {
        if (!isInvalidated() && this.mMoPubView != null) {
            CustomEventBanner customEventBanner = this.mCustomEventBanner;
            if (customEventBanner != null && !customEventBanner.isAutomaticImpressionAndClickTrackingEnabled()) {
                this.mMoPubView.trackNativeImpression();
                if (this.mIsVisibilityImpressionTrackingEnabled) {
                    this.mCustomEventBanner.trackMpxAndThirdPartyImpressions();
                }
            }
        }
    }

    public void onLeaveApplication() {
        onBannerClicked();
    }
}
