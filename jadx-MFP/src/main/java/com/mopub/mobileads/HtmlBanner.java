package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.HtmlBannerWebViewFactory;
import java.lang.ref.WeakReference;
import java.util.Map;

public class HtmlBanner extends CustomEventBanner {
    private boolean mBannerImpressionPixelCountEnabled = false;
    @Nullable
    private ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    @Nullable
    private HtmlBannerWebView mHtmlBannerWebView;
    @Nullable
    private WeakReference<Activity> mWeakActivity;

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        Object obj = map.get(DataKeys.BANNER_IMPRESSION_PIXEL_COUNT_ENABLED);
        if (obj instanceof Boolean) {
            this.mBannerImpressionPixelCountEnabled = ((Boolean) obj).booleanValue();
        }
        if (extrasAreValid(map2)) {
            String str = (String) map2.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            try {
                this.mHtmlBannerWebView = HtmlBannerWebViewFactory.create(context, (AdReport) map.get(DataKeys.AD_REPORT_KEY), customEventBannerListener, (String) map2.get(DataKeys.CLICKTHROUGH_URL_KEY));
                AdViewController.setShouldHonorServerDimensions(this.mHtmlBannerWebView);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    this.mWeakActivity = new WeakReference<>(activity);
                    this.mExternalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
                    this.mExternalViewabilitySessionManager.createDisplaySession(activity, this.mHtmlBannerWebView, this.mBannerImpressionPixelCountEnabled);
                } else {
                    MoPubLog.d("Unable to start viewability session for HTML banner: Context provided was not an Activity.");
                }
                this.mHtmlBannerWebView.loadHtmlResponse(str);
            } catch (ClassCastException unused) {
                MoPubLog.e("LocalExtras contained an incorrect type.");
                customEventBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        } else {
            customEventBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        ExternalViewabilitySessionManager externalViewabilitySessionManager = this.mExternalViewabilitySessionManager;
        if (externalViewabilitySessionManager != null) {
            externalViewabilitySessionManager.endDisplaySession();
            this.mExternalViewabilitySessionManager = null;
        }
        HtmlBannerWebView htmlBannerWebView = this.mHtmlBannerWebView;
        if (htmlBannerWebView != null) {
            htmlBannerWebView.destroy();
            this.mHtmlBannerWebView = null;
        }
    }

    /* access modifiers changed from: protected */
    public void trackMpxAndThirdPartyImpressions() {
        HtmlBannerWebView htmlBannerWebView = this.mHtmlBannerWebView;
        if (htmlBannerWebView != null) {
            htmlBannerWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
            if (this.mBannerImpressionPixelCountEnabled && this.mExternalViewabilitySessionManager != null) {
                WeakReference<Activity> weakReference = this.mWeakActivity;
                if (weakReference != null) {
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        this.mExternalViewabilitySessionManager.startDeferredDisplaySession(activity);
                    } else {
                        MoPubLog.d("Lost the activity for deferred Viewability tracking. Dropping session.");
                    }
                }
            }
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
